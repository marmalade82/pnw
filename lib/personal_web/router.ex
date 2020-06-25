defmodule PersonalWeb.Router do
  use PersonalWeb, :router

  pipeline :browser do
    plug :accepts, ["html"]
    plug :fetch_session
    plug :fetch_flash
    plug :protect_from_forgery
    plug :put_secure_browser_headers
  end

  pipeline :authenticate_admin do
    # plug :check_auth
    # plug :redirect_auth
  end

  defp check_auth(_conn, _opts) do
    # Expects to receive email and password,
    # and checks them against database of users.
    # If found, populates session.
    _conn
  end

  defp redirect_auth(_conn, _opts) do

  end

  pipeline :api do
    plug :accepts, ["json"]
  end

  scope "/", PersonalWeb do
    pipe_through :browser

    resources "/posts", PostsController
    get "/", PageController, :index
    get "/projects", PageController, :projects

  end

  scope "/admin", PersonalWeb do
    pipe_through :browser
    pipe_through :authenticate_admin
    resources "/posts", AdminPostsController
    get "/", AdminController, :index
    get "/login", AdminController, :login
    get "/:message", AdminController, :show

  end

  # Other scopes may use custom stacks.
  # scope "/api", PersonalWeb do
  #   pipe_through :api
  # end
end
