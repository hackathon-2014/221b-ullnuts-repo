Event.create!([
  {name: "Barcamp6"}
])
User.create!([
  {name: "Lisa", phone: "843-555-1212", twitter_handle: "lisa_berry", event_id: 1, is_mc: true}
])
Event.create!([
  {name: "Barcamp6"}
])
Pitch.create!([
  {name: "Clojure for Dummies", description: "How to nest your parenthesis like a pro. ", user_id: 2},
  {name: "Messaging Queues in Real Life", description: "This is a great way to get the message. ", user_id: 3},
  {name: "Learn Rails in a Day and win a Hackathon", description: "Seriously.  You could be a winner.  But you can't win if you don't play.", user_id: 4}
])
Rating.create!([
  {user_id: 2, pitch_id: 1, rating_value: 5},
  {user_id: 3, pitch_id: 2, rating_value: 5},
  {user_id: 4, pitch_id: 3, rating_value: 5},
  {user_id: 1, pitch_id: 1, rating_value: 3},
  {user_id: 1, pitch_id: 2, rating_value: 3}
])
User.create!([
  {name: "Lisa Berry", phone: "843-555-1212", twitter_handle: "lisaannberry", event_id: 1, is_mc: true},
  {name: "Mark Gunnels", phone: "803-738-5811", twitter_handle: "markgunnels", event_id: 1, is_mc: false},
  {name: "Dan Rugg", phone: "843-670-0979", twitter_handle: "", event_id: 1, is_mc: false},
  {name: "Vic Boudolf", phone: "843-813-1813", twitter_handle: "vab3", event_id: 1, is_mc: false}
])
Vote.create!([
  {pitch_id: 1, user_id: 1},
  {pitch_id: 1, user_id: 2},
  {pitch_id: 2, user_id: 2},
  {pitch_id: 3, user_id: 4}
])
