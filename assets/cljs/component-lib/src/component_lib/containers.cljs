(ns component-lib.containers
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [component-lib.buttons :as b]
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
           modal (fn [{:keys [class] :or {class ""}} hiccup]
                     [:div {:class ["Modal" class]}
                      [:div {:class ["Modal-Content"]}
                       [b/close {:on-click #(close!)}]
                       hiccup
                       ]
                      ]
                   )
           ]
       (fn [attrs render]
         (if @stage?
           [modal attrs (@stage? render)]
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


