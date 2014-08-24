(ns pitch.handlers
  (:require [compojure.core :refer [defroutes routes GET]]
            [hiccup.middleware :refer [wrap-base-url]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [immutant.messaging :as messaging]
            [pitch.inbound.sms :refer [determine-command]]
            [pitch.admin]
            [clojure.data.json :as json]
            [pitch.db :as db])
  (:import [com.twilio.sdk.verbs TwiMLResponse]
           [com.twilio.sdk.verbs Message]))

(defn json-filter-record
  [record]
  (filter (fn[k v] (not (or (instance? v java.sql.Timestamp)
                            (instance? v java.sql.Time)
                            (instance? v java.sql.Date)
                            (instance? v java.util.Date)))) record))

(defn json-filter-records
  [records]
  (map json-filter-record record))

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
  
  (GET "/api/event" []
       (json/write-str (json-filter-record (db/current-active-event))))
  
  (route/not-found "Not Found"))

(def app
  (-> (routes app-routes)
      (handler/site)
      (wrap-base-url)))
