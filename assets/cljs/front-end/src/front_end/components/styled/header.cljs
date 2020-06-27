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
  [:span {:class "Header-MenuItem"}
    item
   ]
  )

(defn header []
  (let [menu-items (map render-menu-item menu-items)]
    (into [:header {:class "Header-Container"}] menu-items)
    )
  )
