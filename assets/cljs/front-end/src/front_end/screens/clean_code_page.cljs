(ns front-end.screens.clean-code-page
  (:require
    [reagent.core :as r]
    [reagent.dom :as d]
    [front-end.components.styled.layout :refer [layout]]
    [front-end.screens.clean-code.data :refer [get-clean-code]]
    [front-end.components.styled.unordered-list :refer [unordered-list]]
    [front-end.components.styled.text :refer [my-text]]
    ))

(defn render-paragraph [p]
  [:p {:class "CleanCode-Paragraph"}
    p
   ]
  )

(defn render-clean-code [{:keys [text paragraphs]}]
  [:div {:class "CleanCode"}
    [:h2 {:class "CleanCode-Title"} text]
   (let [rendered (map render-paragraph paragraphs)]
     (into [:div {:class "Clean-Code-Paragraphs"}] rendered)
     )
   ]
  )

(defn clean-code-page [{:keys [label topic]}]
  (let [clean (get-clean-code topic)]
    (js/console.log topic)
    (js/console.log clean)
    (fn [] 
      [layout {:label label}
       [:article {:class "CleanCodePage-Container"}
        [render-clean-code clean ]
        ]
       ]))
  )

