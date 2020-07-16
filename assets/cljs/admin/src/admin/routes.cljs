(ns admin.routes
  (:require
   [component-lib.routing :refer-macros [routing]]
   )
  )

(routing
  [root-path "/" "root"]
  [edit-path "/edit" "edit"]
  [timeline-path "/timeline" "timeline"]
  [project-edit-path "/project-edit" "project-edit"]
  [project-timeline-path "/project-timeline" "project-timeline"]
  [admin-dashboard-path "/dashboard" "dashboard"]
  [skills-path "/skills" "skills"]
  )

