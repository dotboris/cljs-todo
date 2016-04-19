(ns todo.views
  (:require [reagent.core :as r]
            [todo.storage :as s]))

(defn todo-button [item]
  (if (s/done? item)
    [:i.glyphicon.glyphicon-ok]
    [:i.glyphicon.glyphicon-unchecked]))

(defn todo-item [item]
  [:li.item {:on-click #(s/toggle! (:id item))}
    [todo-button item] " " (s/text item)])

(defn counter [list]
  [:p
    "You have "
    [:span.count
      (->> (:items list)
           vals
           (filter (comp not s/done?))
           count)]
    " things left to do. Get on with it!"])

(defn todo-app []
  [:div.container.todo
    [:h1 "Todo"]
    [counter @s/todo-list]
    [:ul (for [[id item] (:items @s/todo-list)]
            ^{:key id} [todo-item item])]])
