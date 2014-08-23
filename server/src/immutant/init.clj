(ns immutant.init
  (:require [immutant.messaging :as messaging]
            [immutant.web :as web]
            [immutant.jobs :as jobs]
            [immutant.repl :as repl]
            [pitch.handlers :refer :all]
;;            [pitch.admin :refer :all]
            [pitch.inbound.participant :refer :all]))

(web/start app)

(messaging/start "topic.commands")

(messaging/listen "topic.commands" println)
;; (messaging/listen "topic.commands" handle-start :selector "command='start'")
;; (messaging/listen "topic.commands" handle-end :selector "command='end'")
;; (messaging/listen "topic.commands" handle-notify :selector "command='notify'")
;; (messaging/listen "topic.commands" handle-veto :selector "command='veto'")
;; (messaging/listen "topic.commands" handle-start :selector "command='start'")

(messaging/listen "topic.commands" handle-registration :selector "command='register'")
(messaging/listen "topic.commands" handle-pitch :selector "command='pitch'")
(messaging/listen "topic.commands" handle-vote :selector "command='vote'")
(messaging/listen "topic.commands" handle-rating :selector "command='rate'")


