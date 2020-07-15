(ns admin.screens.edit-page
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [mde]
   )
  )


(defn markdown-editor [{:keys [on-change] :or {on-change #(js/console.log %)}}]
  (r/create-class
   {:display-name "markdown-editor"
    :component-did-mount
    (fn [this]
      (let [editor (js/SimpleMDE. (clj->js { :element (.getElementById js/document "md-editor")}))
            ]
        (doto editor
          (-> (.-codemirror) (.on "change" #(on-change (.value editor))))
          ))
      )
    :component-will-unmount (fn [] 1)
    :reagent-render
      (fn []

        [:textarea {:id "md-editor"}]
        )
    })
  )


(defn edit-page []
  [:div {:class "EditPage"}
   [:h2 "Edit Post"]
   [markdown-editor {:onchange #(js/console.log "hi")}]
   ]
  )
