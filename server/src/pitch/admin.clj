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

(messaging/listen "topic.commands" handle-start :selector "command='start'")
(messaging/listen "topic.commands" handle-end :selector "command='end'")
(messaging/listen "topic.commands" handle-notify :selector "command='notify'")
(messaging/listen "topic.commands" handle-veto :selector "command='veto'")
