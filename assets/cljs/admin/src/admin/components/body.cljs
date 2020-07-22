(ns admin.components.body
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [component-lib.core :as c]
   )
  )

(defn body [{:keys [class] :or {class ""}}]
  (into [c/body {:class class}
         #_(into [:div {:class "Patterns-Triangles"}]
               (for [i (range 0 30)] [:div {:class ["Patterns-Triangles-Triangle" (str "Patterns-Triangles-Triangle-" i)]}])
               )
        ] (r/children (r/current-component)))
  )
