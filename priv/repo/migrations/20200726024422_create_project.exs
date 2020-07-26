defmodule Personal.Repo.Migrations.CreateProject do
  use Ecto.Migration

  def change do
    create table(:project) do
      add :title, :string
      add :subtitle, :string
      add :description, :string
      add :reflection, :string

      timestamps()
    end

  end
end
