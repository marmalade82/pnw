(ns admin.page-controller
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [admin.screens.home-page :refer [home-page]]
   [admin.screens.edit-page :refer [edit-page]]
   [admin.screens.timeline-page :refer [timeline-page]]
   [admin.screens.project-edit-page :refer [project-edit-page]]
   [admin.screens.project-timeline-page :refer [project-timeline-page]]
   [admin.screens.new-page :refer [new-page]]
   [admin.screens.project-new-page :refer [project-new-page]]
   [admin.screens.skills-page :refer [skills-page]]
   [admin.routes :refer [retry-route-or]]
   [cljs.core.async :refer [go]]
   )
  )

; responsible for coordinating page changes across client routing
(defonce page (r/atom nil))

(defn update-page! [id]
  (reset! page id)
  )

#_(match target
       "root" (render home-page)
       "edit" (render edit-page)
       "new" (render new-page)
       "timeline" (render timeline-page)
       "project-new" (render project-new-page)
       "project-edit" (render project-edit-page)
       "project-timeline" (render project-timeline-page)
       "skills" (render skills-page)
       :else (retry-route-or (fn [] (render home-page)))
       )

(defn page-controller []
  (case @page
    "root" [home-page]
    "edit" [edit-page]
    "new" [new-page]
    "timeline" [timeline-page]
    "project-edit" [project-edit-page]
    "project-timeline" [project-timeline-page]
    "project-new" [project-new-page]
    "skills" [skills-page]
    (do
      (go
        (retry-route-or nil)
        )
      nil
      )
    )
  )
