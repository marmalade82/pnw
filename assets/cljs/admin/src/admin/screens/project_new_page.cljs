(ns admin.screens.project-new-page
  (:require 
   [reagent.core :as r]
   [reagent.dom :as d]
   [component-lib.core :as c]
   [component-lib.buttons :as b]
   [admin.components.header :refer [header]]
   [admin.components.go-back :refer [go-back]]
   [admin.forms.project-edit-form :refer [render-new-form]]
   ))



(defn project-new-page [{:keys [class] :or {class ""}}]
  [c/surface {:class (str "ProjectAddPage-Surface" " " class)}
     [c/surface-nav-header {:left-hiccup
                            [:<>
                             [go-back]
                             [c/text {:type 1} "New Project"]
                             ]
                            }
      ]
     [c/surface-body
      [render-new-form]
      ]
     ]
  )
