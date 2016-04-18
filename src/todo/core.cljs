(ns todo.core
  (:require [todo.views :as v]
            [todo.storage :as s]
            [reagent.core :as r]))

(enable-console-print!)

(r/render-component
  [v/todo-app @s/todo-list]
  (.getElementById js/document "todo-app"))
