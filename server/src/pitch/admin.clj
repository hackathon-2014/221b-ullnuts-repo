(ns pitch.admin
  (:require [immutant.messaging :as messaging]
            [pitch.broadcast :as broadcast]
            [pitch.inbound.sms :as sms]
            [pitch.db :as db]))

(def url "http://54.164.169.20:3000/​")

(defn printit
  [x]
  (println "PRINTING: " x)
  x)

(defn event-subscribers
  [_]
  (->> (db/current-active-event)
    db/user-by-event
    (map :phone)))

(defn close-event
  []
  (db/close-active-events))

(defn open-event
  [message]
  (let [event (db/set-active-event (sms/determine-value message))]
    (assoc event :hbic (db/user-by-id (db/register-user "HBIC" (:From message) (:id event))))))

(defn veto-pitch
  [pitch-id]
  (-> pitch-id
    db/pitch-by-id
    db/drop-pitch))

(defn handle-start
  [message]
  (-> message
    open-event
    :hbic
    :phone
    (broadcast/send-message (str "Event '" (sms/determine-value message) "' Has Started: " url))))


(defn handle-end
  [message]
  (-> (close-event)
    event-subscribers
    (broadcast/send-message (str "Event Has Closed: " url))))


(defn handle-notify
  [message]
  (-> (db/current-active-event)
    event-subscribers
    (broadcast/send-message (sms/determine-value message))))


(defn handle-veto
  [message]
  (let [pitch (-> message
                sms/determine-value
                veto-pitch)
        user (db/user-by-id (:user_id pitch))]
    (broadcast/send-message (:phone user) (str "Your pitch '" (:title pitch) "' has been vetoed.  Deal with it."))))


(defn handle-url
  [message]
  url)
