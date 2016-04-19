(ns todo.storage
  (:require [reagent.core :as r]))

(defonce id-count (atom 0))

(defn make-item [text & {:keys [done] :or {done false}}]
  (let [id (swap! id-count inc)]
    {:done done :text text :id id}))

(def initial-items
  [(make-item "Learn ClojureScript" :done true)
   (make-item "Learn om" :done true)
   (make-item "or reagent")
   (make-item "Find something cool to build")
   (make-item "Build that thing")
   (make-item "Figure out how to use cljs with electron")
   (make-item "Anything else?")])

(defonce todo-list
  (let [pairs (map #(vector (:id %) %) initial-items)]
    (r/atom {:items (into (sorted-map) pairs)})))

(defn text [item]
  (get item :text))

(defn done? [item]
  (get item :done))

(defn toggle! [id]
  (swap! todo-list update-in [:items id :done] not))
