(ns pitch.db
  (:require [clojure.java.jdbc :as jdbc]))

;; HARCODE ALL THE THINGS!
(def db {:classname "com.mysql.jdbc.Driver"
         :subprotocol "mysql"
         :subname "//localhost:3306/myapp_development"
         :user "root"
         :password "pitch"})

(defn query-and-return-first-record
  [q]
  (-> (jdbc/query db q)
      first))

(defn current-active-event
  []
  (query-and-return-first-record ["SELECT * FROM events WHERE is_open = 1 ORDER BY ID DESC LIMIT 1;"]))

(defn user-by-way-of-phone-number
  [phone-number]
  (query-and-return-first-record 
   ["SELECT * FROM users WHERE phone = ? ORDER BY ID DESC LIMIT 1;"
    phone-number]))

(defn insert-and-return-generated-id
  [table columns]
  (-> (jdbc/insert! db table columns)
      first
      :generated_key))

(defn register-user
  [name phone-number event-id]
  (insert-and-return-generated-id :users 
                                  {:name name 
                                   :phone phone-number 
                                   :event_id event-id}))

(defn add-pitch
  [user-id description]
  (insert-and-return-generated-id :pitches
                                  {:user_id user-id :description description}))

(defn vote
  [user-id pitch-id]
  (insert-and-return-generated-id :votes
                                  {:user_id user-id :pitch_id pitch-id}))
