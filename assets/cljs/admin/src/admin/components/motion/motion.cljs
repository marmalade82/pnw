(ns admin.components.motion.motion
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   )
  )


(defn motion []
  (let [classes (r/atom [])]
    (r/create-class
     {:display-name "motion"
      :component-did-mount (fn [this]
                             (do 
                               (reset! classes ["motion" "apple" "baby"])
                               (js/console.log this)
                               ))
      :component-will-unmount (fn [this] (reset! classes ["xavier"]))
      :reagent-render (fn [] [:div {:class @classes}])
      }
     ))
  )

; Creates an animation object
(defn anim [{:keys []}]
  (let [render (r/atom true)]
      {:finish! (reset! render false)
       
       :anim (fn []
               (if @render
                 [:div
                  (r/children (r/current-component))
                  ]
                 )
               )
       })
  )
