(ns admin.components.header
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [component-lib.core :as c]
   [clojure.string :refer [join]]
   ))

(defn header [{:keys [class] :or {class ""}}]
  (into [c/header {:class (join " " ["My-Header" class])}
         [c/logo {:class "My-Logo"}
          [c/text {:class "My-LogoText"} "admin"]
          ]
         ]
        (r/children (r/current-component))
        )
  )

