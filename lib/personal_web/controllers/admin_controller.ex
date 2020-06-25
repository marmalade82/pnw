defmodule PersonalWeb.AdminController do
  use PersonalWeb, :controller

  def index(conn, _params) do
    conn
    |> put_layout({ PersonalWeb.AdminView, "admin.html"})
    |> render("index.html")
  end

  def show(conn, %{"message" => message}) do
    conn
    |> put_layout({ PersonalWeb.AdminView, "admin.html"})
    |> render("show.html", message: message)
  end

  def login(conn, _params) do
    conn
    |> put_layout({ PersonalWeb.AdminView, "admin.html" })
    |> render("login.html")
  end
end
