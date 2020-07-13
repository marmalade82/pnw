defmodule PersonalWeb.API.PostsController do
  use PersonalWeb, :controller

  def index(conn, _params) do
    render(conn, "index.json", data: Personal.Repo.all(Personal.Blog.Posts))
  end

  def show(conn, %{"id" => id}) do
    render(conn, "show.json", data: Personal.Repo.get!(Personal.Blog.Posts, id))
  end
end
