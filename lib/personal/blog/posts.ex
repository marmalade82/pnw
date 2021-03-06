defmodule Personal.Blog.Posts do
  use Ecto.Schema
  import Ecto.Changeset

  schema "blog_posts" do
    field :content, :string
    field :subtitle, :string
    field :title, :string

    timestamps()
  end

  @doc false
  def changeset(posts, attrs) do
    posts
    |> cast(attrs, [:title, :subtitle, :content])
    |> validate_required([:title, :subtitle, :content])
  end
end
