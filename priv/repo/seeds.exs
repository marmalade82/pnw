# Script for populating the database. You can run it as:
#
#     mix run priv/repo/seeds.exs
#
# Inside the script, you can read and write to any of your
# repositories directly:
#
#     Personal.Repo.insert!(%Personal.SomeSchema{})
#
# We recommend using the bang functions (`insert!`, `update!`
# and so on) as they will fail if something goes wrong.


for n <- 1..5 do
   Personal.Repo.insert!(%Personal.Blog.Posts{title: "test #{n}", subtitle: "subtitle", content: "content"})
   Personal.Repo.insert!(%Personal.Skill{name: "skill #{n}", abbr: "ab", color: "pink"})
end
