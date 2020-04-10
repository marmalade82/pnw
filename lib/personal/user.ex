defmodule Personal.User do
  use Ecto.Schema
  import Ecto.Changeset

  schema "users" do
    field :email, :string
    field :first, :string
    field :last, :string
    field :password, :string

    timestamps()
  end

  @doc false
  def changeset(user, attrs) do
    user
    |> cast(attrs, [:first, :last, :email, :password])
    |> validate_required([:first, :last, :email, :password])
    |> validate_length(:password, min: 4)
    |> validate_length(:password, max: 20)
    |> validate_format(:email, ~r/@/)
  end
end
