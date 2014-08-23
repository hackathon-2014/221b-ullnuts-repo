(ns immutant.init
  (:use pitch.core)
  (:require [immutant.web :as web]))

(web/start ring-handler)