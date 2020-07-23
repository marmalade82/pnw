(ns component-lib.logic.subscribe
  (:require
   [reagent.core :as r]
   [cljs.core.async :refer [go take! chan]]
   )
  )


; Takes a channel and returns controls for subscribing to it.
; The channel should only be made subscribable once,
; and no other values should consume directly from the channel
(defn mk-subscribable [c]
  (let [latest (r/atom nil)]
    (go
      (loop []
        (take! c #(reset! latest %))
        (recur)
        )
      )
    {:subscribe (fn [default]
                  (let [subscriber (r/atom (if (nil? @latest)
                                             default
                                             @latest
                                             ))
                        ]
                    (add-watch latest (random-uuid)
                               (fn [_key _ref _old new]
                                 (reset! subscriber new)
                                 )
                               )
                    subscriber 
                    )
                  )
     }
    )
  )
