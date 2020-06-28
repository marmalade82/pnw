(ns front-end.components.styled.header
  (:require [reagent.core :as r]
            [reagent.dom :as d]
            ))

(def ^:private menu-items
  ["Home"
   "Blog"
   "Contact"
   ]
  )

; A simple component for menu items
(defn- render-menu-item [item]
  [:span {:class "Header-UnselectedMenuItem"}
    item
   ]
  )

(defn header []
  (let [menu-items (map render-menu-item menu-items)]
    [:header {:class "Header-Container"}
     (into [ :div {:class "Header-MenuItemsContainer"}] menu-items)
     ]
    )
  )
