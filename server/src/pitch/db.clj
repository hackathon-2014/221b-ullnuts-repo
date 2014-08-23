(ns pitch.db
  (:require [clojure.java.jdbc :as jdbc]))

;; HARCODE ALL THE THINGS!
(def db {:classname "com.mysql.jdbc.Driver"
         :subprotocol "mysql"
         :subname "//localhost:3306/myapp_development"
         :user "root"
         :password "pitch"})

(defn current-active-event
  []
  (-> (jdbc/query db
                  ["SELECT * FROM events WHERE is_open = 1 ORDER BY ID DESC LIMIT 1;"])
      first))

(defn user-by-way-of-phone-number
  [phone-number]
  (-> (jdbc/query db
                  ["SELECT * FROM users WHERE phone = ? ORDER BY ID DESC LIMIT 1;"
                   phone-number])
      first))

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
