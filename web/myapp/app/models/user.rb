class User < ActiveRecord::Base
	has_many :pitches
	has_many :votes
end
