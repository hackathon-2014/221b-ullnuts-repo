(ns pitch.config )

(def system-sid (System/getProperty "twilio.sid"))
(def system-token (System/getProperty "twilio.token"))
(def system-from (System/getProperty "twilio.from"))

(defn sid [] system-sid)

(defn token [] system-token)

(defn from [] system-from)