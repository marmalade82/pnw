(ns component-lib.routing
  )

(defmacro routing
  [first & rest]
  (let [all (cons first rest)]
    `(do
       (def ~'unknown-route (reagent.core/atom 0))
       (def ~'history
          (reagent.core/atom (goog.History.)))
       (def ~'render-chan (cljs.core.async/chan))
       (secretary.core/set-config! :prefix "#")
       ~@(for [[path-name path id] all]
           `(secretary.core/defroute ~path-name ~path {}
              (cljs.core.async/put! ~'render-chan ~id)
              )
           )
       (defn- ~'render-loop [~'render-target]
         (cljs.core.async/take! ~'render-chan
                                (fn [~'target]
                                  (~'render-target ~'target)
                                  (trampoline ~'render-loop ~'render-target)
                                  ))
         )
       (defn ~'init-client-routing [~'render-target]
         (~'render-loop ~'render-target)
         (doto @~'history
           (goog.events/listen goog.history.EventType.NAVIGATE
                          #(do
                             (secretary.core/dispatch! (~'.-token %))))
           (~'.setEnabled true))
         )
       (defn ~'retry-route-or [~'render-fn]
         (if (> @~'unknown-route 50)
           (do 
             (~'render-fn)
             (reset! ~'unknown-route 0))
           (do
             (swap! ~'unknown-route #(+ % 1))
             (let [~'token (~'.getToken @~'history)]
               (secretary.core/dispatch! ~'token)
               )
             )
           )
         )
       )
    )


  )

