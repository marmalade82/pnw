(ns admin.external.side-effect
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   )
  )


; this component will allow side effects to occur based on true/false prop.
; this allows us to use side-effects in our reagent code without
; the problems of subscribing and unsubscribing.
(defn side-effect []
  (r/create-class
   {:display-name "side-effect"
    :constructor (fn [this]
                   (set! (.-state this ) #js {} ))
    :get-derived-state-from-props
    (fn [props state]
      (let [{:keys [is-true on-true on-false]
             :or {is-true false,
                  on-true #(identity 1)
                  on-false #(identity 2)
                  }} props
            ]
        (if is-true
            (on-true)
            (on-false)
          )
        (js/console.log "DERIVING")
        state
        )
      )
    :reagent-render
    (fn []
      nil
      )
    }
   )
  )
