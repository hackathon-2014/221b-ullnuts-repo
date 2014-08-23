(ns pitch.inbound.sms)

(defn determine-command
  [sms]
  (-> sms 
      :Body 
      clojure.string/trim
      (.split "\\s+")
      first
      clojure.string/lower-case))
