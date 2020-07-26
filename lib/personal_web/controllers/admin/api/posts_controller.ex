defmodule PersonalWeb.Admin.API.PostsController do
  use PersonalWeb, :controller

  alias Personal.Repo
  alias Personal.Blog.Posts

  def index(conn, _params) do
    render(conn, "index.json", data: Repo.all(Posts))
  end

  def show(conn, %{"id" => id}) do
    render(conn, "show.json", data: Repo.get!(Posts, id))
  end

  def create(conn, map) do
    post = Repo.insert!(Posts.changeset(%Posts{}, map))
    conn
    |> put_status(:created)
    |> render("show.json", data: post)
  end

  def update(conn, %{"id" => id} = map) do
    post = Repo.get!(Posts, id);
    post = Repo.update!(Posts.changeset(post, map));
    conn
    |> put_status(:ok)
    |> render("show.json", data: post)
  end

  def delete(conn, %{"id" => id}) do
    post = Repo.get!(Posts, id)
    Repo.delete!(post)
    conn
    |> send_resp(:no_content, "")
  end
end
