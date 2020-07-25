(defproject admin "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.773"]
                 [reagent "0.10.0"]
                 [component-lib "0.1.6"]
                 [fork "2.0.0"]
                 [org.clojure/core.async "1.2.603"]
                 [org.clojure/core.match "1.0.0"]
                 [environ "1.2.0"]
                 [cljsjs/react-transition-group "4.3.0-0"]
                 ]
  
  :compiler {
    :foreign-libs [{:file "simplemde.min.js"
                    :provides ["mde"]
                    }]
    }

  :plugins [[lein-cljsbuild "1.1.7"]
            [lein-figwheel "0.5.20"]]

  :clean-targets ^{:protect false}

  [:target-path
   [:cljsbuild :builds :app :compiler :output-dir]
   [:cljsbuild :builds :app :compiler :output-to]]

  :resource-paths ["public"]

  :figwheel {:http-server-root "."
             :nrepl-port 7002
             :nrepl-middleware [cider.piggieback/wrap-cljs-repl]
             :css-dirs ["public/css"]
             }

  :cljsbuild {:builds {:app
                       {:source-paths ["src" "env/dev/cljs"]
                        :compiler
                        {:main "admin.dev"
                         :output-to "public/js/app.js"
                         :output-dir "public/js/out"
                         :asset-path   "js/out"
                         :source-map true
                         :optimizations :none
                         :foreign-libs [{:file "admin/foreign/simplemde.min.js"
                                          :provides ["mde"]
                                          }]
                         :pretty-print  true}
                        :figwheel
                        {:on-jsload "admin.core/mount-root"
                         :open-urls ["http://localhost:3449/index.html"]}}
                       :release
                       {:source-paths ["src" "env/prod/cljs"]
                        :compiler
                        {:output-to "public/js/app.js"
                         :output-dir "target/release"
                         :optimizations :advanced
                         :infer-externs true
                         :pretty-print false}}}}

  :aliases {"package" ["do" "clean" ["cljsbuild" "once" "release"]]}

  :profiles {:project/dev {:source-paths ["src" "env/dev/clj"]
                           :dependencies [[binaryage/devtools "1.0.2"]
                                          [figwheel-sidecar "0.5.20"]
                                          [nrepl "0.7.0"]
                                          [cider/piggieback "0.5.0"]]
                            }
             :project/test {}


             :profiles/dev {}
             :profiles/test {}
             :profiles/prod {}

             :dev [:project/dev :profiles/dev]
             :test [:project/test :profiles/test]
             }
                                  )
