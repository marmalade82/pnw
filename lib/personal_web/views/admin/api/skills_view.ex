defmodule PersonalWeb.Admin.API.SkillsView do

  def render_admin_skill(skill) do
    skill
    |> Map.take([:id, :name, :abbr, :color])
  end

  def render("index.json", %{data: data}) do
    Enum.map(data, &render_admin_skill/1)
  end

  def render("show.json", %{data: data}) do
    render_admin_skill(data)
  end
end
