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

(defn counter [list owner]
  (om/component
    (dom/p nil
      "You have "
      (dom/span #js {:className "count"}
        (count (filter (comp not s/done?) (:items list))))
      " things left to do. Get on with it!")))

(defn todo-app [list owner]
  (om/component
    (dom/div #js {:className "container"}
      (dom/h1 nil "Todo")
      (om/build counter list)
      (om/build todo-list list))))
