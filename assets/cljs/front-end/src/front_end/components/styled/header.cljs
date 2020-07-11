(ns front-end.components.styled.header
  (:require [reagent.core :as r]
            [reagent.dom :as d]
            [front-end.components.styled.header.menu-items :refer [menu-items]]
            ))


(defn- render-submenu-item [item]
  [:a {:href (:href item)
       :class "Header-SubmenuItemLink"
       }
   [:span {:class ["Header-SubmenuItem"]}
    (:label item)
    ]
   ]
  )

; A simple component for menu items
(defn- render-menu-item [{:keys [label item]}]
  [:div {:class "Header-MenuItemContainer"}
   [:a {:href (:href item)
        :class "Header-MenuItemLink"}
    [:span {:class [(if (= label (:label item)) "Header-SelectedMenuItem" "Header-UnselectedMenuItem")]}
     (:label item)
     ]
    ]
   (into [:div {:class "Header-SubmenuContainer"}]
         (map render-submenu-item (:children item))
         )
   ]
  )

(defn header [{:keys [label]}]
  (let [menu-items (map #(render-menu-item {:item %, :label label}) (menu-items))]
    [:header {:class "Header-Container"}
     (into [ :div {:class "Header-MenuItemsContainer"}] menu-items)
     ]
    )
  )
