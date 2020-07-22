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
   [react-transition-group :as t]
   )
  )

(defonce direction (r/atom :forward))

(defn get-to-class! []
  (case @direction
    :forward "PageEnterForward"
    "PageEnterBackward"
    )
  )

(defn get-from-class! []
  (case @direction
    :forward "PageEnterForward"
    "PageEnterBackward"
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


(defn transition [child class]
  [:> t/SwitchTransition {:mode "out-in"}
   [:> t/CSSTransition {:in true, :appear true,
                        :key (str child)
                        :timeout 1500, :classNames class}
    child
    ]
   ]
  )

(defn get-hiccup [id class]
        (case id
          "root" [home-page]
          "edit" (transition [edit-page {:class class}] class)
          "new" (transition [new-page {:class class}] class)
          "timeline" (transition [timeline-page {:class class}] class)
          "project-edit" (transition [project-edit-page {:class class}] class)
          "project-timeline" (transition [project-timeline-page {:class class}] class)
          "project-new" (transition [project-new-page {:class class}] class)
          "skills" (transition [skills-page {:class class}] class)
          (do
            (go
              (retry-route-or nil)
              )
            [:div]
            )
          )
  )

(defn page-controller []
  (let [f (get-hiccup @from (get-from-class!))
        t (get-hiccup @to (get-to-class!))
        ]
    (or t f)
    )
  )
