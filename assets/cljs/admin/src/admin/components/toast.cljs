(ns admin.components.toast
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [react-transition-group :as t]
   [component-lib.buttons :as b]
   [admin.global-state.toasts :as to]
   )
  )


(defn entered-toast [{:keys [message id timeout] :or {message "", timeout 10000}}
                     ]
  (let [in (r/atom true)
        ]
    (fn []
      [:> t/CSSTransition {:in @in, :appear true,
                           :timeout 3000, :classNames "EnteredToast"
                           :onEntered
                              (fn []
                                (js/setTimeout #(reset! in false) timeout))
                           :onExited #(to/toast-expired id)
                           }
        [:div {:class "Toast-Entered"}
         [:div {:class "Toast-Entered-Header"}
            message
            [b/close {:on-click #(swap! in (fn [i] (not i)))}]
          ]
         ]
       ]
      )
    )
  )

(defn render-entered-toasts [toasts]
  (into [:div {:class "Toast-EnteredList"}]
        (map #(into [] [entered-toast %]) toasts)
        )
  )


(defn toast []
  ; Subscribes to the toast topic, 
  ; on new values, place them in the toast queue
  ; what's in the toast queue is rendered, and when it goes to the 
  ; expired state, or when the message is cancelled
  ; , the leaving animation is animated (fades away).
  (let [entered (to/get-toasts)
        ]
    (fn []
      [:div {:class "Toast"}
          [render-entered-toasts @entered]
       ]
      )
    )
  )
