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
   [admin.direction :refer [forward! is-forward?]]
   [cljs.core.async :refer [go]]
   [react-transition-group :as t]
   )
  )


; responsible for coordinating page changes across client routing
(defonce from (r/atom nil))

(defonce to (r/atom nil))

(defn update-page! [id]
  (do
    (reset! from @to)
    (reset! to id)
    )
  )

(def forward-class "Forward")
(def backward-class "Backward")

(defn set-dir-class! [node]
  (.remove (.-classList node) forward-class)
  (.remove (.-classList node) backward-class)
  (if (is-forward?)
      (.add (.-classList node) forward-class)
      (.add (.-classList node) backward-class)
    )
  )

(defn transition [child class]
  [:> t/SwitchTransition {:mode "out-in"}
   [:> t/CSSTransition {:in true, :appear true,
                        :onEnter #(set-dir-class! %)
                        :onEntered #(forward!)
                        :onExit #(set-dir-class! %)
                        :key (str child)
                        :timeout 900, :classNames class}
    child
    ]
   ]
  )

(defn get-hiccup [id class]
    (case id
      "root" [home-page]
      "edit" (transition [edit-page ] class)
      "new" (transition [new-page ] class)
      "timeline" (transition [timeline-page ] class)
      "project-edit" (transition [project-edit-page ] class)
      "project-timeline" (transition [project-timeline-page ] class)
      "project-new" (transition [project-new-page ] class)
      "skills" (transition [skills-page ] class)
      (do
        (go
          (retry-route-or nil)
          )
        [:div]
        )
      )
  )

(defn page-controller []
  (let [
        f (get-hiccup @from "PageEnter")
        t (get-hiccup @to "PageEnter")
        ]
    (or t f)
    )
  )
