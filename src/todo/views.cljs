(ns todo.views
  (:require [om.core :as om]
            [om.dom :as dom]
            [todo.storage :as s]))

(defn todo-item [item owner]
  (om/component
    (dom/li
      (if (s/done? item)
        #js {:className "item done"}
        #js {:className "item"})
      (s/text item))))

(defn counter [list owner]
  (om/component
    (dom/p nil
      "You have "
      (dom/span #js {:className "count"}
        (count (filter (comp not s/done?) (:items list))))
      " things left to do. Get on with it!")))

(defn todo-app [list owner]
  (om/component
    (dom/div #js {:className "container todo"}
      (dom/h1 nil "Todo")
      (om/build counter list)
      (dom/ul nil
        (om/build-all todo-item (:items list))))))
