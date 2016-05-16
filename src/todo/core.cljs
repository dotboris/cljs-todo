(ns todo.core
  (:require [todo.storage :as s]
            [matchbox.core :as m]))

(defn make-item [name & {:keys [done] :or {done false}}]
  {:done done :name name})

(def text #(:name %))
(def done? #(:done %))

(defn toggle! [key]
  (m/swap-in! s/items-ref [key :done] not))

(defn add-item! [text]
  (let [item (make-item text)]
    (m/conj! s/items-ref item)))

(defn remove-item! [key]
  (m/dissoc-in! s/items-ref [key]))
