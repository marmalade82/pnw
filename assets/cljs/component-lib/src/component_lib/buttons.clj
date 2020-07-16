(ns component-lib.buttons)


(defmacro defbutton [name & body]
  `(defn ~name [{:keys [~'class ~'disabled ~'href ~'on-click] :as ~'all}]
     [component-lib.core/button ~'all
      ~@body
      ]
     )
  )

