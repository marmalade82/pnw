defmodule Personal.Skill do
  use Ecto.Schema
  import Ecto.Changeset

  schema "skill" do
    field :abbr, :string
    field :color, :string
    field :name, :string

    timestamps()
  end

  @doc false
  def changeset(skill, attrs) do
    skill
    |> cast(attrs, [:name, :abbr, :color])
    |> validate_required([:name, :abbr, :color])
  end
end
