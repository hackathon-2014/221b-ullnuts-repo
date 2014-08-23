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

(defn close-active-events
  []
  (let [active (current-active-event)]
    (jdbc/update! db :events {:is_open 0} ["is_open = ?" 1])
    active))

(defn set-active-event
  [name]
  (close-active-events)
  (jdbc/insert! db :events {:name name :is_open 1 :created_at (new java.sql.Timestamp (System/currentTimeMillis))})
  (current-active-event))


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

(defn user-by-id
  [id]
  (query-and-return-first-record 
   ["SELECT * FROM users WHERE id = ? ORDER BY ID DESC LIMIT 1;"
    id]))


(defn user-by-event
  [event]
  (jdbc/query db ["SELECT * FROM users WHERE event_id = ? ORDER BY ID DESC;"
                  (:id event)]))

;; Pitch Functions
(defn pitch
  [user-id description]
  (insert-and-return-generated-id :pitches
                                  {:user_id user-id :description description}))

(defn drop-pitch
  [pitch]
  (jdbc/delete! db :pitches ["id = ?" (:id pitch)])
  pitch)

(defn pitch-by-id
  [id]
  (query-and-return-first-record 
   ["SELECT * FROM pitches WHERE id = ? ORDER BY ID DESC LIMIT 1;"
    id]))

;; Vote Functions
(defn vote
  [user-id pitch-id]
  (insert-and-return-generated-id :votes
                                  {:user_id user-id :pitch_id pitch-id}))

(defn number-of-votes
  [pitch-id]
  (:v (query-and-return-first-record ["SELECT count(*) as v FROM votes WHERE pitch_id =?" pitch-id])) )

(defn rate
  [user-id pitch-id rating]
  (insert-and-return-generated-id :ratings
                                  {:user_id user-id :pitch_id pitch-id :rating_value rating}))

(defn average-rating-for-pitch
  [pitch-id]
  (-> (query-and-return-first-record ["SELECT AVG(rating_value) as a FROM ratings WHERE pitch_id =?" pitch-id])
      :a
      int))
