# This file is responsible for configuring your application
# and its dependencies with the aid of the Mix.Config module.
#
# This configuration file is loaded before any dependency and
# is restricted to this project.

# General application configuration
use Mix.Config

config :personal,
  ecto_repos: [Personal.Repo]

# Configures the endpoint
config :personal, PersonalWeb.Endpoint,
  url: [host: "localhost"],
  secret_key_base: "uVoHgvqkG8qc0bMWtMa8GXvrzZEgh7IXfv4OZISoFzjJt1hzxhHrLHWuVFzfk+Gm",
  render_errors: [view: PersonalWeb.ErrorView, accepts: ~w(html json)],
  pubsub: [name: Personal.PubSub, adapter: Phoenix.PubSub.PG2]

# Configures Elixir's Logger
config :logger, :console,
  format: "$time $metadata[$level] $message\n",
  metadata: [:request_id]

# Use Jason for JSON parsing in Phoenix
config :phoenix, :json_library, Jason

# Import environment specific config. This must remain at the bottom
# of this file so it overrides the configuration defined above.
import_config "#{Mix.env()}.exs"
