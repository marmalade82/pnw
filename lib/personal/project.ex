defmodule Personal.Project do
  use Ecto.Schema
  import Ecto.Changeset

  schema "project" do
    field :description, :string
    field :reflection, :string
    field :subtitle, :string
    field :title, :string
    field :status, :string

    timestamps()
  end

  @doc false
  def changeset(project, attrs) do
    project
    |> cast(attrs, [:title, :subtitle, :description, :reflection,
                    :status
                    ])
    |> validate_required([:title, :subtitle, :description, :reflection,
                          :status
                        ])
  end
end
