defmodule PersonalWeb.API.PostsView do

  def render_post(post) do
    post
    |> Map.take([:id, :title, :subtitle, :content])
  end

  def render("index.json", %{data: data}) do
    Enum.map(data, &render_post/1)
  end

  def render("show.json", %{data: data}) do
    render_post(data)
  end
end
