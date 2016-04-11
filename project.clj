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
                 [org.omcljs/om "0.9.0"]]

  :plugins [[lein-figwheel "0.5.2"]
            [lein-cljsbuild "1.1.3" :exclusions [[org.clojure/clojure]]]
            [lein-doo "0.1.6"]]

  :source-paths ["src"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  :doo {:paths {:karma "./node_modules/karma-cli/bin/karma"}
        :build "test"}

  :cljsbuild {:builds
              [{:id "dev"
                :source-paths ["src"]

                ;; If no code is to be run, set :figwheel true for continued automagical reloading
                :figwheel {:on-jsload "todo.core/on-js-reload"}

                :compiler {:main todo.core
                           :asset-path "js/compiled/out"
                           :output-to "resources/public/js/compiled/todo.js"
                           :output-dir "resources/public/js/compiled/out"
                           :source-map-timestamp true}}
               ;; This next build is an compressed minified build for
               ;; production. You can build this with:
               ;; lein cljsbuild once min
               {:id "min"
                :source-paths ["src"]
                :compiler {:output-to "resources/public/js/compiled/todo.js"
                           :main todo.core
                           :optimizations :advanced
                           :pretty-print false}}

               {:id "test"
                :source-paths ["src" "test"]
                :compiler {:output-to "resources/public/js/compiled/todo-test.js"
                           :main todo.test
                           :optimizations :none}}]}

  :figwheel {;; watch and update CSS
             :css-dirs ["resources/public/css"]})
