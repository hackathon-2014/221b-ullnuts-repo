(ns pitch.inbound.participant
  (:require [pitch.db :refer :all]
            [pitch.broadcast :refer :all]
            [pitch.inbound.sms :refer :all]))

(defn welcome-user-to-event
  [name phone-number event]
  (send-message phone-number
                (format "Welcome, %s, to %s"
                        name 
                        (:name event))))

(defn handle-registration
  [sms]
  (let [name (determine-value sms)
        phone-number (:From sms)
        event (current-active-event)]
    (register-user name
                   phone-number
                   (:id event))
    (welcome-user-to-event name
                           phone-number
                           event)))

(defn notify-user-of-pitch-id
  [phone-number pitch-id]
  (send-message phone-number
                (format "Your pitch number is %s. Thanks for participating. You're a peach."
                        pitch-id)))

(defn handle-pitch
  [sms]
  (let [description (determine-value sms)
        phone-number (:From sms)
        user (user-by-way-of-phone-number phone-number)
        event (current-active-event)
        pitch-id (pitch (:id user) 
                        description)]
    (notify-user-of-pitch-id phone-number
                             pitch-id)))

(defn notify-user-of-vote
  [phone-number pitch-id]
  (send-message phone-number
                (format "Thanks for taking part in the second opiate of the masses. That pitch now has %s votes."
                        (number-of-votes pitch-id))))

(defn handle-vote
  [sms]
  (let [pitch-id (determine-value sms)
        phone-number (:From sms)
        user (user-by-way-of-phone-number phone-number)
        event (current-active-event)]
    (vote (:id user) pitch-id)
    (notify-user-of-vote phone-number
                         pitch-id)))

(defn notify-user-of-average-rating
  [phone-number pitch-id]
  (send-message phone-number
                (format "Thanks for taking part in the second opiate of the masses. That pitch now has %s votes."
                        (average-rating-for-pitch pitch-id))))

(defn handle-rate
  [sms]
  (let [phone-number (:From sms)
        stars (determine-value sms)
        num-of-stars (get (frequencies stars) \*)
        user (user-by-way-of-phone-number phone-number)
        event (current-active-event)]
    (vote (:id user) pitch-id)
    (notify-user-of-vote phone-number
                         pitch-id)))


