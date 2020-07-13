defmodule PersonalWeb.Admin.API.PostsView do

  def render_admin_post(post) do
    post
    |> Map.take([:id, :title, :subtitle, :content])
  end

  def render("index.json", %{data: data}) do
    Enum.map(data, &render_admin_post/1)
  end

  def render("show.json", %{data: data}) do
    render_admin_post(data)
  end
end
