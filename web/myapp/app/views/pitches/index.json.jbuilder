json.array!(@pitches) do |pitch|
  json.extract! pitch, :id, :name, :description, :user_id
  json.url pitch_url(pitch, format: :json)
end
