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

