class Pitch < ActiveRecord::Base
	belongs_to :user
	has_many :votes
end
