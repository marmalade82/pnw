(ns front-end.components.styled.header
  (:require [reagent.core :as r]
            [reagent.dom :as d]
            [front-end.routes :refer [root-path blog-path contact-path] ]
            ))

(defn menu-items []
  [{:label "Home", :href (root-path)}
   {:label "Blog", :href (blog-path)}
   {:label "Contact", :href (contact-path)}
   ]
  )

; A simple component for menu items
(defn- render-menu-item [item]
  [:a {:href (:href item)}
   [:span {:class "Header-UnselectedMenuItem"}
    (:label item)
    ]
   ]
  )

(defn header []
  (let [menu-items (map render-menu-item (menu-items))]
    [:header {:class "Header-Container"}
     (into [ :div {:class "Header-MenuItemsContainer"}] menu-items)
     ]
    )
  )
