(ns pitch.broadcast
  (:require [twilio.core :as twilio]
            [pitch.config :as config]
            [clojure.core.async :refer :all]))

(defn recipients
  [to]
  (if (coll? to)
    to
    [to]))


(def message-channel (chan 10))

(defn send-message
  [to message]
  (go 
    (doseq [t (recipients to)]
      (>! message-channel (twilio/sms (config/from) t message)))))

(go 
  (while true
    (let [message (<! message-channel)]
      (println "Attepting Send" message)
      (twilio/with-auth (config/sid) (config/token)
        (twilio/send message))
      (println "Sent" message))))