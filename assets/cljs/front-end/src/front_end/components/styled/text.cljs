(ns front-end.components.styled.text
  (:require [reagent.core :as r]
            [front-end.components.styled.header-text :refer [header-text my-header-text]]
            [reagent.dom :as d]))


(defn text [type & text]
  (case type
    "1" [header-text "1" text]
    "2" [header-text "2" text]
    "3" [header-text "3" text]
    "4" [header-text "4" text]
    "5" [header-text "5" text]
    "body" [:span {:class ["body-text"]
                   } text]))


(defn my-text
  [{:keys [class type text] :or {class "", type "body", text ""}}]
  (case type
    ("1" "2" "3" "4" "5") [my-header-text {:class class, :type type, :text text}]
    "body" [:span {:class ["body-text" class]}
             text
            ])
  )
