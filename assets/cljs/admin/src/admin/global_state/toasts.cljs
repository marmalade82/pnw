(ns admin.global-state.toasts
  (:require
    [reagent.core :as r]
    [reagent.dom :as d]
   )
  )

(defonce toasts (r/atom []))

(defn get-toasts []
  toasts
  )

(defn not-id [id]
  (fn [{:keys [item-id]}]
    (not= item-id id)
    )
  )

(defn toast-expired [id]
  (swap! toasts #(->> % (filter (not-id id)))
   )
  )

(defn new-toast [message]
  (swap! toasts #(conj % {:message message, :id (random-uuid)}))
  )

