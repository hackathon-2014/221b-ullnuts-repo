json.array!(@users) do |user|
  json.extract! user, :id, :name, :phone, :twitter_handle, :event_id, :is_mc
  json.url user_url(user, format: :json)
end
