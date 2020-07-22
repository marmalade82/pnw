(ns admin.screens.new-page
  (:require 
   [reagent.core :as r]
   [reagent.dom :as d]
   [component-lib.core :as c]
   [admin.components.header :refer [header]]
   [admin.components.go-back :refer [go-back]]
   [admin.forms.edit-form :refer [render-new-form]]
   ))


(defn new-page [{:keys [class] :or {class ""}}]
  [c/surface {:class (str "AddPage-Surface" " " class)}
     [c/surface-nav-header {:left-hiccup
                            [:<>
                             [go-back]
                             [c/text {:type 1} "New Post"
                              ]
                             ]
                            }]
     [c/surface-body
      [render-new-form]
      ]
     ]
  )

