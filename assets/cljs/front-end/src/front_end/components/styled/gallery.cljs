(ns front-end.components.styled.gallery
  (:require [reagent.core :as r]
            [reagent.dom :as d]))


(defn render-item [item]
  [:div {:class ["gallery-item"]}
   [:img {:class ["gallery-item-image"]
          :src (:image item)
          :alt (str "Logo for " (:title item))} ]
   [:div {:class ["gallery-item-title"]} (:title item)]
   [:div {:class ["gallery-item-body"]} (:body item)]
   [:div {:class ["gallery-item-link"]} (:link item)]
   ])

(defn render-items [items]
  (map render-item items))


(defn gallery [items]
  (into [:div {:class ["gallery-container"]}] (render-items items))
  )

