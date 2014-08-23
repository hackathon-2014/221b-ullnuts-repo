class AddIsOpenToEvents < ActiveRecord::Migration
  def change
    add_column :events, :is_open, :boolean
  end
end
