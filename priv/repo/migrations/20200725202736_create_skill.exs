defmodule Personal.Repo.Migrations.CreateSkill do
  use Ecto.Migration

  def change do
    create table(:skill) do
      add :name, :string
      add :abbr, :string
      add :color, :string

      timestamps()
    end

  end
end
