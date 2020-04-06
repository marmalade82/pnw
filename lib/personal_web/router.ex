defmodule PersonalWeb.Router do
  use PersonalWeb, :router

  pipeline :browser do
    plug :accepts, ["html"]
    plug :fetch_session
    plug :fetch_flash
    plug :protect_from_forgery
    plug :put_secure_browser_headers
  end

  pipeline :api do
    plug :accepts, ["json"]
  end

  scope "/", PersonalWeb do
    pipe_through :browser

    resources "/posts", PostsController
    get "/", PageController, :index

  end

  scope "/admin", PersonalWeb do
    pipe_through :browser
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
