(ns admin.components.spinner-page
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   )
  )

(defn spinner-page []
  [:div {:class "LoadingSpinner"}
   [:div {:class "LoadingSpinner-Rotate"}]
   ]
  )
