(ns todo.core
  (:require [goog.dom :as dom]
            [goog.dom.classes :as classes]
            [goog.events :as events]
            [todo.views :as v]
            [todo.storage :as s]
            [om.core :as om]))

(enable-console-print!)

(om/root v/todo-app
  s/todo-list
  {:target (.getElementById js/document "todo-app")})
