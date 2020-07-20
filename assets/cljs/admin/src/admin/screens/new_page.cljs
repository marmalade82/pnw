(ns admin.screens.new-page
  (:require 
   [reagent.core :as r]
   [reagent.dom :as d]
   [component-lib.core :as c]
   [admin.components.header :refer [header]]
   [admin.forms.edit-form :refer [render-new-form]]
   ))


(defn new-page []
  [c/page
   [header]
   [c/body
    [c/surface {:class "AddPage-Surface"}
     [c/text {:type 1} "New Post"
      ]
     [render-new-form]
     ]
    ]
   ]
  )

