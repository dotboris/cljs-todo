(defproject todo "0.1.0-SNAPSHOT"
  :description (str "The ever loved TODO app. "
                    "This is my first play around with clojure. "
                    "It probably sucks!")
  :url "https://github.com/dotboris/cljs-todo"
  :license {:name "MIT"}

  :min-lein-version "2.6.1"

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.7.228"]
                 [org.clojure/core.async "0.2.374"
                  :exclusions [org.clojure/tools.reader]]
                 [reagent "0.5.1"]]

  :plugins [[lein-figwheel "0.5.2"]
            [lein-cljsbuild "1.1.3" :exclusions [[org.clojure/clojure]]]]

  :source-paths ["src"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled"
                                    "resources/public/js/test"
                                    "target"
                                    "out"]

  :cljsbuild {:builds
              [{:id "dev"
                :source-paths ["src"]
                :figwheel true
                :compiler {:main todo.core
                           :asset-path "js/compiled/out"
                           :output-to "resources/public/js/compiled/todo.js"
                           :output-dir "resources/public/js/compiled/out"
                           :source-map-timestamp true}}

               {:id "test"
                :source-paths ["src" "test"]
                :figwheel true
                :compiler {:main todo.test
                           :optimizations :none
                           :asset-path "js/test/out"
                           :output-to "resources/public/js/test/test.js"
                           :output-dir "resources/public/js/test/out"
                           :source-map-timestamp true}}

               {:id "min"
                :source-paths ["src"]
                :compiler {:output-to "resources/public/js/compiled/todo.js"
                           :main todo.core
                           :optimizations :advanced
                           :pretty-print false}}]}

  :figwheel {;; watch and update CSS
             :css-dirs ["resources/public/css"]})
