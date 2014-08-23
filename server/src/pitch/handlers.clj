(ns pitch.handlers
  (:require [compojure.core :refer [defroutes routes GET]]
            [hiccup.middleware :refer [wrap-base-url]]
            [compojure.handler :as handler]
            [compojure.route :as route])
  (:import [com.twilio.sdk.verbs TwiMLResponse]
           [com.twilio.sdk.verbs Message]))

(defroutes app-routes
  (GET "/sms" [From]
       (println From)
       (let [r (TwiMLResponse.)
             m (Message. From)]
         (.append r m)
         (.toXML r)))
  (GET "/test-message" []
       "<html><a href='sms:8436089719?body=helloworld'>Test</a></html>")
  (route/not-found "Not Found"))

(def app
  (-> (routes app-routes)
      (handler/site)
      (wrap-base-url)))
