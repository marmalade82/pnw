(ns admin.components.go-back
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [component-lib.buttons :as b]
   [admin.direction :as dir]
   )
  )


(defn go-back []
  (if (< 1 (.-length js/window.history))
    [b/go-back {:on-click #(dir/go-back!), :class "My-GoBack"}]
    nil
    )
  )

