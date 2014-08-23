class CreateUsers < ActiveRecord::Migration
  def change
    create_table :users do |t|
      t.string :name
      t.string :phone
      t.string :twitter_handle
      t.integer :event_id
      t.boolean :is_mc

      t.timestamps
    end
  end
end
