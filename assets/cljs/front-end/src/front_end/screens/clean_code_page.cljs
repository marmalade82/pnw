(ns front-end.screens.clean-code-page
  (:require
    [reagent.core :as r]
    [reagent.dom :as d]
    [front-end.components.styled.layout :refer [layout]]
    [front-end.screens.clean-code.data :refer [get-clean-code]]
    [front-end.components.styled.unordered-list :refer [unordered-list]]
    [front-end.components.styled.text :refer [my-text]]
    [front-end.components.styled.article :refer [article]]
    ))

(defn render-paragraph [p]
  [:p {:class "CleanCode-Paragraph"}
    p
   ]
  )

(defn render-clean-code [{:keys [text paragraphs]}]
  [:div {:class "CleanCode"}
    [my-text {:class "CleanCode-Title", :text text, :type "2"}]
   (let [rendered (map render-paragraph paragraphs)]
     (into [:div {:class "Clean-Code-Paragraphs"}] rendered)
     )
   ]
  )

(defn clean-code-page [{:keys [label topic]}]
  (let [clean (get-clean-code topic)]
    (fn [] 
      [layout {:label label}
       [article {:class "CleanCodePage-Container"}
        [render-clean-code clean ]
        ]
       ]))
  )

