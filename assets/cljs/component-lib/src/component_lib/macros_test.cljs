(ns component-lib.macros-test
  (:require
    [cljs.test :as t :include-macros true]
    [component-lib.routing :as r]
             ))


(r/routing
  [test-path "/test" "test"]
  [rest-path "/rest" "rest"]
 )

