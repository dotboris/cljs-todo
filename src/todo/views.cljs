(ns todo.views
  (:require [om.core :as om]
            [om.dom :as dom]))

(defn hello-world []
  (om/component
    (dom/h1 nil "Hello World!")))
