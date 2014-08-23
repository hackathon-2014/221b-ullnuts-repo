(ns pitch.inbound.sms)

(defn poor-man-wordize
  [sms]
  (-> sms 
      :Body 
      clojure.string/trim
      (.split "\\s+")))

(defn determine-value
  [sms]
  (->> sms 
       poor-man-wordize
       rest
       (clojure.string/join " ")))

(defn determine-command
  [sms]
  (-> sms 
      poor-man-wordize
      first
      clojure.string/lower-case))
