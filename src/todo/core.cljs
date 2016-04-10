(ns todo.core
  (:require [goog.dom :as dom]
            [goog.dom.classes :as classes]
            [goog.events :as events]
            [todo.views :as v]
            [om.core :as om]))

(enable-console-print!)

(om/root v/hello-world
  nil
  {:target (.getElementById js/document "todo-app")})
