(ns component-lib.icons
  (:require
   [clojure.string]
   ))

(defmacro deficon [name hiccup-icon]
  `(defn ~name [{:keys [~'class] :or {~'class ""}}]
     [:span {:class ["Icon" ~(str "Icon-" (clojure.string/capitalize name)) ~'class]}
      ~hiccup-icon
      ]
     )
  )

