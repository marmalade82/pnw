defmodule PersonalWeb.Admin.API.ProjectsController do
  use PersonalWeb, :controller
  alias Personal.Repo
  alias Personal.Project

  def index(conn, _params) do
    render(conn, "index.json", data: Repo.all(Project))
  end

  def show(conn, %{"id" => id}) do
    render(conn, "show.json", data: Repo.get!(Project, id))
  end

  def create(conn, map) do
    project = Repo.insert!(Project.changeset(%Project{}, map))
    conn
    |> put_status(:created)
    |> render("show.json", data: project)
  end

  def update(conn, %{"id" => id} = map) do
    project = Repo.get!(Project, id);
    project = Repo.update!(Project.changeset(project, map));
    conn
    |> put_status(:ok)
    |> render("show.json", data: project)
  end

  def delete(conn, %{"id" => id}) do
    project = Repo.get!(Project, id)
    Repo.delete!(project)
    conn
    |> send_resp(:no_content, "")
  end
end
