(ns component-lib.core
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [clojure.string :refer (capitalize)]
   [mde]
   )
  )

(defn- children []
  (r/children (r/current-component))
  )

(defn- default-input-group [{:keys [label, type, class,
                                    value, id, on-change, max-length]
                             :or {type "text", class "", value nil, id "",
                                  on-change nil, max-length nil, label ""
                                  } :as all}]
  [:div {:class ["InputGroup" class]}
   [:label {:for id, :class "InputGroup-Label"} label]
   [:input {:class (str "InputGroup-" (capitalize type) "Input")
            :type type,
            :value value,
            :id id,
            :name id,
            :on-change on-change,
            :max-length max-length
            }]
   ]
  )

(defn input-group [{:keys [label, type, class,
                           value, id, on-change, max-length]
                    :or {type "text", class "", value nil, id "",
                         on-change nil, max-length nil, label ""
                         } :as all}]
  (case type
    "textarea" 
      [:div {:class ["InputGroup" class]}
        [:label {:for id, :class "InputGroup-Label"} label]
        [:textarea {:class (str "InputGroup-" (capitalize type) "Input")
                  :type type,
                  :value value,
                  :id id,
                  :name id,
                  :on-change on-change,
                  :max-length max-length
                  }]
       ]
    (default-input-group all)
    )

  )

(defn label [{:keys [for class] :or {for nil, class ""}}]
  (into [:label {:for for, :class ["Label" class]}] (children))
  )

(defn submit-button [{:keys [disabled class]}]
  (into [:button {:type "submit"
                 :disabled disabled
                 :class ["SubmitButton" class]
                 }]
        (r/children (r/current-component))
        )
  )

(defn button [{:keys [class disabled href on-click]
               :or {class "", disabled false, href nil, on-click identity }}]
  [:button {:class ["Button" class]
            :disabled disabled
            :on-click (if href
                        identity
                        on-click
                        )
            }
   (let [inner (into [:span {:class "Button-Text"}
                 ] (r/children (r/current-component)))
         ]
     (if href
       [:a {:href href
            :class ["Button-Link"]
            } inner]
       inner
       )
     )
   ]
  )


(defn text [{:keys [class type] :or {class "", type "nil"}}]
  (let [children (r/children (r/current-component))]
    (into
     (case (str type)
       "1" [:h1 {:class "Header1"}]
       "2" [:h2 {:class "Header2"}]
       "3" [:h3 {:class "Header3"}]
       "4" [:h4 {:class "Header4"}]
       "5" [:h5 {:class "Header5"}]
       "6" [:h6 {:class "Header6"}]
       [:span {:class "Text"}]
       )
      children  
     )
    )
  )

(defn markdown-editor [{:keys [on-change id]
                        :or {on-change #(js/console.log %)
                             id "md-editor"
                             }}]
  (r/create-class
   {:display-name "markdown-editor"
    :component-did-mount
    (fn [this]
      (let [editor (js/SimpleMDE. (clj->js { :element (.getElementById js/document id)}))
            ]
        (doto editor
          (-> (.-codemirror) (.on "change" #(on-change (.value editor))))
          ))
      )
    :component-will-unmount (fn [] 1)
    :reagent-render
    (fn []

      [:textarea {:id id}]
      )
    })
  )

(defn badge [{:keys [color class] :or {color "white", class ""}}]
  (into [:span {:class ["Badge" class]
                :style {:background-color color
                        
                        }
                }
         ]
        (children )
        )
  )
