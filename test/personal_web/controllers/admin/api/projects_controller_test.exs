defmodule PersonalWeb.Admin.API.ProjectsControllerTest do
  use PersonalWeb.ConnCase
  alias Personal.Repo
  alias Personal.Project

  setup do
    for n <- 1..2 do
      Repo.insert!(Project.changeset(
                    %Project{title: "title #{n}",
                          subtitle: "subtitle #{n}",
                          description: "description #{n}",
                          reflection: "reflection #{n}",
                          },
                    %{}
                    ))
      end
    :ok
  end

  @root "/admin/api/projects"
  def endpoint(extension) do
    "#{@root}#{extension}"
  end

  def do_post(conn, data \\ %{"title" => "title",
                            "subtitle" => "subtitle",
                            "description" => "description",
                            "reflection" => "reflection"}
      ) do
    conn = post(conn, endpoint("/"), data)
    json_response(conn, :created)
  end

  def get_first_id(conn) do
    conn = get(conn, endpoint("/"))
    [%{"id" => id} | _rest] = json_response(conn, :ok)
    id
  end

  test "GET /", %{conn: conn} do
    conn = get(conn, endpoint("/"))
    body = json_response(conn, :ok)
    assert length(body) == 2
  end

  test "GET /:id", %{conn: conn} do
    conn = get(conn, endpoint("/"))
    id = get_first_id(conn)
    conn = get(conn, endpoint("/#{id}"))
    %{"id" => id} = json_response(conn, :ok)
    assert id == id
  end

  test "POST /", %{conn: conn} do
    data = %{"title" => "title",
             "subtitle" => "subtitle",
             "description" => "description",
             "reflection" => "reflection",
            }
    body = do_post(conn)
    assert body == Map.merge(body, data)
  end

  test "PATCH /:id", %{conn: conn} do
    data = %{"title" => "patch",
            }
    id = get_first_id(conn)
    conn = patch(conn, endpoint("/#{id}"), data)
    %{"title" => title} = json_response(conn, :ok)
    assert title == "patch"
  end

  test "DELETE /:id", %{conn: conn} do
    %{"id" => id} = do_post(conn)
    conn = delete(conn, endpoint("/#{id}"))
    response(conn, :no_content)
  end
end
