(ns todo.views
  (:require [reagent.core :as r]
            [todo.storage :as s]))

(defn remove-button [id]
  [:span.remove>i.glyphicon.glyphicon-remove
    {:on-click #(do (.stopPropagation %)
                    (s/remove-item! id))}])

(defn todo-item [item]
  (let [tag (if (s/done? item) :div.item.done :div.item)]
    [tag {:on-click #(s/toggle! (:id item))}
      [:span.check>i.glyphicon.glyphicon-ok]
      " " [:span.text (s/text item)] " "
      [remove-button (:id item)]]))

(defn counter [list]
  [:p
    "You have "
    [:span.count
      (->> (:items list)
           vals
           (filter (comp not s/done?))
           count)]
    " things left to do. Get on with it!"])

(defn new-item-box []
  (let [text (r/atom "")]
    (fn []
      [:form.form-inline
        {:on-submit #(do (.preventDefault %)
                         (s/add-item! @text)
                         (reset! text ""))}
        [:div.form-group>input.form-control.new-item-textbox
          {:type "text"
           :placeholder "Do something..."
           :value @text
           :on-change #(reset! text (-> % .-target .-value))}]
        " "
        [:button.btn.btn-primary {:type "submit"}
          [:i.glyphicon.glyphicon-plus]]])))

(defn todo-app []
  [:div.container.todo
    [:h1 "Todo"]
    [counter @s/todo-list]
    [new-item-box]
    (for [[id item] (:items @s/todo-list)]
      ^{:key id} [todo-item item])])
