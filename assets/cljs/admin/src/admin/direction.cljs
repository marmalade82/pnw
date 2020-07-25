(ns admin.direction
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   )
  )


(def direction (atom :forward))

(defn back! []
  (reset! direction :backward)
  )

(defn forward! []
  (reset! direction :forward)
  )

(defn is-forward? []
  (= @direction :forward)
  )

(defn dir []
  @direction
  )

(defn go-back! []
  (do 
    (back!)
    (.back js/window.history)
    )
  )
