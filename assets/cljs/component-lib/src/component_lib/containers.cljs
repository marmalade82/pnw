(ns component-lib.containers
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [component-lib.buttons :as b]
   [component-lib.core :as c]
   )
  )

(defn mk-modal []
  (let [stage? (r/atom false)
        in-stage? #(= % @stage?)
        open? #(not= false @stage?)
        closed? #(= false @stage?)
        disable-unless? #(not (or (in-stage? %) (closed?)))
        close! #(reset! stage? false)
        open! #(reset! stage? (or % true))
        toggle! #(swap! stage? (fn [stage]
                                (cond
                                  (= stage %) false
                                  :else %
                                  )
                                ))
        ]
    { :modal
     (let [
           modal (fn [{:keys [class title] :or {class "", title ""}} hiccup]
                   [:div {:class ["Modal" class]
                          :on-click #(close!)
                          }
                      [:div {:class ["ModalContent"]}
                       [:div {:class "ModalHeader"}
                        [c/text {:type 1, :class "ModalHeaderTitle"} title]
                        [b/close {:on-click #(close!)}]
                        ]
                       hiccup
                       ]
                      ]
                   )
           ]
       (fn [{:keys [title] :or {title {}} :as attrs} render]
         (if @stage?
           [modal (assoc attrs :title (@stage? title)) (@stage? render)]
           )
         ))
     :open! open!
     :close! close!
     :toggle! toggle!
     :stage? in-stage?
     :disable-unless? disable-unless?
     :open? open?
     :closed? closed?
     }
    )
  )


