(ns pitch.inbound.participant
  (:require [pitch.db :refer :all]
            [pitch.inbound.sms :refer :all]))

(defn handle-registration
  [sms]
  (let [name (determine-value sms)
        phone-number (:From sms)
        event (current-active-event)]
    (register-user value
                   phone-number
                   (:id event))))
