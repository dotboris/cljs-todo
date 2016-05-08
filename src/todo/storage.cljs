(ns todo.storage
  (:require [reagent.core :as r]))

(defonce todo-list (r/atom nil))

(defn init! []
  (reset! todo-list
    {:items (sorted-map)}))

(defonce _init
  (do (init!)
      nil))
