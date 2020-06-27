(ns front-end.components.styled.footer
  (:require [reagent.core :as r]
            [reagent.dom :as d]
            [front-end.components.styled.text :refer [my-text]]
            ))


(defn footer []
  [:footer {:class "Footer-Container"}
   (my-text {:class "Footer-Text"
             :text "Built with ClojureScript, Elixir, and Sass"
             })
   (my-text {:class "Footer-Text"
             :text "Howard Chen, 2020"
             })
   ]
  )
