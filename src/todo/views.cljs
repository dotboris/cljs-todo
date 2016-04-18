(ns todo.views
  (:require [reagent.core :as r]
            [todo.storage :as s]))

(defn todo-item [item owner]
  (let [class (if (s/done? item) "item done" "item")]
    [:li {:class class
          :on-click (fn [_] (println (str "clicked on " (s/text item))))}
      (s/text item)]))

(defn counter [list]
  [:p
    "You have "
    [:span {:class "count"}
      (count (filter (comp not s/done?) (:items list)))]
    " things left to do. Get on with it!"])

(defn todo-app [list]
  [:div {:className "container todo"}
    [:h1 "Todo"]
    [counter list]
    [:ul (for [item (:items list)] [todo-item item])]])
