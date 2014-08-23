(ns immutant.init
  (:require [immutant.messaging :as messaging]
            [immutant.web :as web]
            [immutant.jobs :as jobs]
            [immutant.repl :as repl]
            [pitch.handlers :refer :all]))

(web/start app)
(messaging/start "topic.commands")

(messaging/listen "topic.commands" println :selector "command='start'")
