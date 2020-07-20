(ns admin.screens.project-edit-page
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [component-lib.core :as c]
   [admin.components.header :refer [header]]
   [admin.forms.project-edit-form :refer [render-project-form]]
   )
  )


(defn project-edit-page []
  [c/page {:class "ProjectEditPage"}
   [header]
   [c/body {:class "ProjectEditPage-Body"}
    [c/surface {:class "ProjectEditPage-Surface"}
      [c/text {:type 2, :class "ProjectEditPage-Header"}
        "Edit Project"
        ]
      [render-project-form]
     ]
    ]
   ]
  )

