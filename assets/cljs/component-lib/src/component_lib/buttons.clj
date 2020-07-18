(ns component-lib.buttons
  (:require
   [clojure.string :refer [capitalize]]
   )
  )


(defmacro defbutton [name & body]
  `(defn ~name [{:keys [~'class ~'disabled ~'href ~'on-click] :as ~'all}]
     [component-lib.core/button (update ~'all :class #(str ~'class " " ~(str (capitalize name) "Button")))
      ~@body
      ]
     )
  )

