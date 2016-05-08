(ns todo.storage
  (:require [reagent.core :as r]))

(defonce id-count (atom 0))

(defn make-item [text & {:keys [done] :or {done false}}]
  (let [id (swap! id-count inc)]
    {:done done :text text :id id}))

(defonce todo-list
  (r/atom {:items (sorted-map)}))

(def text #(:text %))
(def done? #(:done %))

(defn toggle! [id]
  (swap! todo-list update-in [:items id :done] not))

(defn add-item! [text]
  (let [item (make-item text)]
    (swap! todo-list update-in [:items] #(assoc % (:id item) item))))

(defn remove-item! [id]
  (swap! todo-list update-in [:items] dissoc id))
