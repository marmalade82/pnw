defmodule PersonalWeb.PageController do
  use PersonalWeb, :controller

  def index(conn, _params) do
    conn
    #|> put_layout(false)
    #|> put_flash(:info, "Welcome to Phoenix, from flash info!")
    #|> put_flash(:error, "Let's pretend we have an error.")
    |> render("index.html")
  end

  def projects(conn, _params) do
    conn
    |> render("projects.html")
  end
end
