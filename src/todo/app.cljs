(ns todo.app
  (:require [reagent.core :as r]
            [todo.views :as v]))

(enable-console-print!)

(r/render
  [v/todo-app]
  (.getElementById js/document "todo-app"))
