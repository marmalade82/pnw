(ns front-end.requests.target
  (:require
    [environ.core :refer [env]]
   )
  )

(def target
  (case (env :server)
    nil nil
    (env :server)
    )
  )

