(ns pitch.admin
  (:require [immutant.messaging :as messaging]))

(defn handle-start
  [message]
  (println "START: " message))


(defn handle-end
  [message]
  (println "END: " message))


(defn handle-notify
  [message]
  (println "NOTIFY: " message))


(defn handle-veto
  [message]
  (println "VETO: " message))

(messaging/listen "topic.commands" :selector {:command "start"} handle-start)
(messaging/listen "topic.commands" :selector {:command "end"} handle-end)
(messaging/listen "topic.commands" :selector {:command "notify"} handle-notify)
(messaging/listen "topic.commands" :selector {:command "veto"} handle-veto)
