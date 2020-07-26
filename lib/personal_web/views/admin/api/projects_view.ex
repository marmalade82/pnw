
defmodule PersonalWeb.Admin.API.ProjectsView do

  def render_admin_project(project) do
    project
    |> Map.take([:id, :title, :subtitle, :description,
                 :reflection])
  end

  def render("index.json", %{data: data}) do
    Enum.map(data, &render_admin_project/1)
  end

  def render("show.json", %{data: data}) do
    render_admin_project(data)
  end
end
