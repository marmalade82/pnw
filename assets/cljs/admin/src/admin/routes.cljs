(ns admin.routes
  (:require
   [component-lib.routing :refer-macros [routing]]
   )
  )

(routing
  [root-path "/" "root"]
  [edit-path "/edit" "edit"]
  [add-post-path "/posts/new" "new"]
  [timeline-path "/timeline" "timeline"]
  [project-edit-path "/projects/edit" "project-edit"]
  [project-add-path "/projects/new" "project-new"]
  [project-timeline-path "/project-timeline" "project-timeline"]
  [admin-dashboard-path "/dashboard" "dashboard"]
  [skills-path "/skills" "skills"]
  )

