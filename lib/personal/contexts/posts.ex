defmodule Personal.Context.Post do
  import Ecto.Query

  def all() do
    query = from p in Personal.Blog.Posts
    Personal.Repo.all(query)
  end
end
