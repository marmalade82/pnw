(ns admin.routes
  (:require
   [component-lib.routing :refer-macros [routing]]
   )
  )

(routing
  [root-path "/" "root"]
  [edit-path "/edit" "edit"]
  [timeline-path "/timeline" "timeline"]
  )

