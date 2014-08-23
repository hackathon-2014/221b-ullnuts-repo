(ns pitch.admin
  (:require [immutant.messaging :as messaging]
            [pitch.broadcast :as broadcast]
            [pitch.inbound.sms :as sms]
            [pitch.db :as db]))

(def url "http://54.164.169.20:3000/​")


(defn event-subscribers
  [event]
  ["+18436700979" "+18037385811"])

(defn close-event
  []
  (db/close-active-events))

(defn open-event
  [message]
  (let [event (db/set-active-event (sms/determine-value message))]
    (assoc event :hbic (db/register-or-reconcile-user "HBIC" (:From message) (:event)))))

(defn veto-pitch
  [pitch-id]
  )

(defn handle-start
  [message]
  (-> message
    open-event
    (get-in [:hbic :phone])
    (broadcast/send-message (str "Event '" (sms/determine-value message) "' Has Started: " url))))


(defn handle-end
  [message]
  (-> (close-event)
    event-subscribers
    (broadcast/send-message ("Event Has Closed: " url))))


(defn handle-notify
  [message]
  (-> (db/current-active-event)
    event-subscribers
    (broadcast/send-message (sms/determine-value message))))


(defn handle-veto
  [message]
  (-> message
    sms/determine-value
    veto-pitch))

