json.array!(@votes) do |vote|
  json.extract! vote, :id, :pitch_id, :user_id
  json.url vote_url(vote, format: :json)
end
