defmodule Personal.Repo.Migrations.AddProjectStatus do
  use Ecto.Migration

  def change do
    alter table("project") do
      add :status, :string
    end
  end
end
