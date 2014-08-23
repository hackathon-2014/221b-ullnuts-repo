class CreateRatings < ActiveRecord::Migration
  def change
    create_table :ratings do |t|
      t.integer :user_id
      t.integer :pitch_id
      t.integer :rating_value

      t.timestamps
    end
  end
end
