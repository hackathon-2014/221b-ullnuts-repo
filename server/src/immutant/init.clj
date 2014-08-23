(ns immutant.init
  (:require [immutant.messaging :as messaging]
            [immutant.web :as web]
            [immutant.jobs :as jobs]
            [immutant.repl :as repl]
            [pitch.handlers :refer :all]
            [pitch.admin :refer :all]))

(web/start app)
(messaging/start "topic.commands")

(messaging/listen "topic.commands" println)
(messaging/listen "topic.commands" handle-start :selector "command='start'")
(messaging/listen "topic.commands" handle-end :selector "command='end'")
(messaging/listen "topic.commands" handle-notify :selector "command='notify'")
(messaging/listen "topic.commands" handle-veto :selector "command='veto'")
