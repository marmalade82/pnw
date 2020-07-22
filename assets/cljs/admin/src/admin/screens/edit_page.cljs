(ns admin.screens.edit-page
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [component-lib.core :as c]
   [fork.reagent :as f]
   [clojure.string :as str]
   [admin.components.header :refer [header]]
   [admin.forms.edit-form :refer [render-blog-form]]
   [admin.components.go-back :refer [go-back]]
   [mde]
   )
  )



(defn edit-page [{:keys [class] :or {class ""}}]
    [c/surface {:class (str "EditPage-Surface" " " class)}
     [c/surface-nav-header {:left-hiccup
                            [:<>
                             [go-back]
                             [c/text {:type 1} "Edit Post"]
                             ]
                            }]
     [c/surface-body
      [render-blog-form]
      ]
     ]
  )
