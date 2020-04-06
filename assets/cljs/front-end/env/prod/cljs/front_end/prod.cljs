(ns front-end.prod
  (:require
    [front-end.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
