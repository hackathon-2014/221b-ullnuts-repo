json.array!(@events) do |event|
  json.extract! event, :id, :name, :is_open
  json.url event_url(event, format: :json)
end
