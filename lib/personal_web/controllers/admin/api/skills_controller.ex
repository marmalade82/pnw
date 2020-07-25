
defmodule PersonalWeb.Admin.API.SkillsController do
  use PersonalWeb, :controller
  alias Personal.Repo
  alias Personal.Skill

  def index(conn, _params) do
    render(conn, "index.json", data: Repo.all(Skill))
  end

  def show(conn, %{"id" => id}) do
    render(conn, "show.json", data: Repo.get!(Skill, id))
  end

  def create(conn, map) do
    skill = Repo.insert!(Skill.changeset(%Skill{}, map))
    conn
    |> put_status(:created)
    |> render("show.json", data: skill)
  end

  def update(conn, %{"id" => id} = map) do
    skill = Repo.get!(Skill, id);
    skill = Repo.update!(Skill.changeset(skill, map));
    conn
    |> put_status(:ok)
    |> render("show.json", data: skill)
  end

  def delete(conn, %{"id" => id}) do
    skill = Repo.get!(Skill, id)
    Repo.delete!(skill)
    conn
    |> send_resp(:no_content, "")
  end
end
