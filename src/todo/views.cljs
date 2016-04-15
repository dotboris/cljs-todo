(ns todo.views
  (:require [om.core :as om]
            [om.dom :as dom]
            [todo.storage :as s]))

(defn todo-item [item owner]
  (om/component
    (dom/li (when (s/done? item) #js {:className "done"})
      (s/text item))))

(defn todo-list [list owner]
  (om/component
    (dom/ul nil
      (om/build-all todo-item (:items list)))))

(defn todo-app [list owner]
  (om/component
    (dom/div #js {:className "container"}
      (dom/h1 nil "Todo")
      (om/build todo-list list))))
