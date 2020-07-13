defmodule Mix.Tasks.Build do
  use Mix.Task

  @impl Mix.Task
  def run(_args) do
    IO.puts("Building cljs reagent file...")
    System.cmd("lein", ["cljsbuild", "once", "lib"], cd: "./assets/cljs/front-end")
    IO.puts("...Done building cljs reagent file")
    Mix.Task.run("phx.server")
  end
end
