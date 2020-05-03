(defproject tmhas "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.753"]
                 [reagent "0.7.0"]
                 [re-frame "0.10.1"]
                 [re-frisk "0.4.5"]
                 [org.clojure/core.async "0.4.474"]
                 [re-com "2.1.0"]
                 [secretary "1.2.3"]
                 [compojure "1.5.0"]
                 [yogthos/config "0.8"]
                 [ring "1.4.0"]
                 [cljs-ajax "0.8.0"]
                 [day8.re-frame/http-fx "v0.2.0"]
                 [http-kit "2.4.0-alpha6"]
                 [adzerk/env "0.4.0"]
                 [cljsjs/showdown "1.4.2-0"]
                 [cljsjs/bootstrap "3.3.6-1"]
                 [cljsjs/moment "2.17.1-1"]
                 [cljsjs/jquery "2.2.4-0"]
                 [im.chit/purnam "0.5.2"]
                 [camel-snake-kebab "0.4.0"]]

  :plugins [[lein-cljsbuild "1.1.5"]
            [lein-less "1.7.5"]]

  :min-lein-version "2.5.3"

  :source-paths ["src/clj"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"
                                    "test/js"]

  :figwheel {:css-dirs ["resources/public/css"]
             :ring-handler tmhas.handler/dev-handler}

  :less {:source-paths ["less"]
         :target-path  "resources/public/css"}

  :profiles
  {:dev
   {:dependencies [[binaryage/devtools "0.9.4"]]

    :plugins      [[lein-figwheel "0.5.13"]
                   [lein-doo "0.1.7"]]}}


  :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src/cljs"]
     :figwheel     {:on-jsload "tmhas.core/mount-root"}
     :compiler     {:main                 tmhas.core
                    :output-to            "resources/public/js/compiled/app.js"
                    :output-dir           "resources/public/js/compiled/out"
                    :asset-path           "js/compiled/out"
                    :source-map-timestamp true
                    :preloads             [devtools.preload]
                    :external-config      {:devtools/config {:features-to-install :all}}}}


    {:id           "min"
     :source-paths ["src/cljs"]
     :jar true
     :compiler     {:main            tmhas.core
                    :output-to       "resources/public/js/compiled/app.js"
                    :optimizations   :advanced
                    :closure-defines {goog.DEBUG false}
                    :pretty-print    false
                    :externs ["externs.js"]}}

    {:id           "test"
     :source-paths ["src/cljs" "test/cljs"]
     :compiler     {:main          tmhas.runner
                    :output-to     "resources/public/js/compiled/test.js"
                    :output-dir    "resources/public/js/compiled/test/out"
                    :optimizations :none}}]}


  :main tmhas.server

  :aot [tmhas.server]

  :uberjar-name "tmhas.jar"

  :prep-tasks [["cljsbuild" "once" "min"]["less" "once"] "compile"])
