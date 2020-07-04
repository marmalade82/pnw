(ns front-end.components.styled.footer
  (:require [reagent.core :as r]
            [reagent.dom :as d]
            [front-end.components.styled.text :refer [my-text]]
            ))


(defn footer []
  [:footer {:class "Footer-Container"}
   [:div {:class "Footer-Subcontainer"}
    (my-text {:class "Footer-Text"
              :text "Built with ClojureScript, Elixir, and Sass/SCSS."
              })
    (my-text {:class "Footer-Text"
              :text "Howard Chen \u00A9 2020"
              })
    ]
   ]
  )
