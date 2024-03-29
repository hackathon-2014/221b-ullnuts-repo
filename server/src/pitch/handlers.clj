(ns pitch.handlers
  (:require [compojure.core :refer [defroutes routes GET]]
            [hiccup.middleware :refer [wrap-base-url]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [immutant.messaging :as messaging]
            [pitch.inbound.sms :refer [determine-command]]
            [pitch.admin]
            [pitch.db :as db])
  (:import [com.twilio.sdk.verbs TwiMLResponse]
           [com.twilio.sdk.verbs Message]))


(defroutes app-routes
  (GET "/sms" {sms :params}
       (println sms)
       (println "COMMAND: " (determine-command sms))
       (messaging/publish "topic.commands" 
                          sms 
                          :properties {:command (determine-command sms)})
       "")
  (GET "/test-message" []
       "<html><a href='sms:8436089719?body=helloworld'>Test</a></html>")
  
  (route/not-found "Not Found"))

(def app
  (-> (routes app-routes)
      (handler/site)
      (wrap-base-url)))
