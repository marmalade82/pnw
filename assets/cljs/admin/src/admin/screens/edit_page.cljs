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



(defn edit-page []
  [c/page {:class "EditPage"}
   [header]
   [c/body {:class "EditPage-Body"}
    [c/surface {:class "EditPage-Surface"}
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
    ]
   ]
  )
