(defproject front-end "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [
                 [org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.597"]
                 [org.clojure/core.async "1.2.603"]
                 [org.clojure/core.match "1.0.0"]
                 [reagent "0.10.0"]
                 [hiccup-icons "0.4.2"]
                 [clj-commons/secretary "1.2.5-SNAPSHOT"]
                 [fork "2.0.0"]
                 [environ "1.2.0"]
                 [cljs-http "0.1.46"]]

  :plugins [[lein-cljsbuild "1.1.7"]
            [lein-figwheel "0.5.19"]]

  :clean-targets ^{:protect false}

  [:target-path
   [:cljsbuild :builds :app :compiler :output-dir]
   [:cljsbuild :builds :app :compiler :output-to]]

  :resource-paths ["public" "resources"]

  :figwheel {:http-server-root "."
             :nrepl-port 7002
             :nrepl-middleware [cider.piggieback/wrap-cljs-repl]
             :css-dirs ["public/css"]}

  :cljsbuild 
    {:builds  {:app {:source-paths ["src" "env/dev/cljs"]
                     :compiler
                        {:main "front-end.dev"
                         :output-to "public/js/app.js"
                         :output-dir "public/js/out"
                         :asset-path   "js/out"
                         :source-map true
                         :optimizations :none
                         :pretty-print  true }
                     :figwheel
                        {:on-jsload "front-end.core/mount-root"
                         :open-urls ["http://localhost:3449/index.html"]}}
               :release {:source-paths ["src" "env/prod/cljs"]
                         :compiler
                          { :output-to "public/js/release.js"
                            :output-dir "target/release"
                            :optimizations :advanced
                            :infer-externs true
                            :pretty-print false}}
               :lib {:source-paths ["src" "env/lib/cljs"]
                     :compiler
                        { :output-to "../../js/reagent.js"
                          :output-dir "target/lib"
                          :optimizations :simple
                          :infer-externs true
                          :pretty-print false }} 
                                                  }}

  :aliases {"package" ["do" "clean" ["cljsbuild" "once" "release"]]}

  :profiles {:project/dev {:source-paths ["src" "env/dev/clj"]
                   :dependencies [[binaryage/devtools "1.0.0"]
                                  [figwheel-sidecar "0.5.19"]
                                  [nrepl "0.6.0"]
                                  [cider/piggieback "0.4.2"]]}
              :project/test {}


              :profiles/dev {}
              :profiles/test {}
              :profiles/prod {}

              :dev [:project/dev :profiles/dev]
              :test [:project/test :profiles/test]
                            }
                                  )

