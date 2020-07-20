(ns admin.screens.project-new-page
  (:require 
   [reagent.core :as r]
   [reagent.dom :as d]
   [component-lib.core :as c]
   [admin.components.header :refer [header]]
   [admin.forms.project-edit-form :refer [render-new-form]]
   ))


(defn project-new-page []
  [c/page
   [header]
   [c/body
    [c/surface {:class "ProjectAddPage-Surface"}
     [c/text {:type 1} "New Project"]
     [render-new-form]
     ]
    ]
   ]
  )
