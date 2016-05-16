(ns todo.core
  (:require [todo.storage :as s]
            [matchbox.core :as m]))

(defonce id-count (atom 0))

(defn make-item [text & {:keys [done] :or {done false}}]
  (let [id (swap! id-count inc)]
    {:done done :name text :id id}))

(def text #(:name %))
(def done? #(:done %))

(defn toggle! [id]
  (m/swap-in! s/items-ref [id :done] not))

(defn add-item! [text]
  (let [item (make-item text)]
    (m/conj! s/items-ref item)))

(defn remove-item! [key]
  (m/dissoc-in! s/items-ref [key]))
