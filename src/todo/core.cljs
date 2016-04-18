(ns todo.core
  (:require [todo.views :as v]
            [reagent.core :as r]))

(enable-console-print!)

(r/render
  [v/todo-app]
  (.getElementById js/document "todo-app"))
