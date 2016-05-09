(ns todo.storage
  (:require [reagent.core :as r]
            [matchbox.core :as m]))

(defonce root (m/connect "https://cljstodo-dev.firebaseio.com/"))

(defonce todo-list (r/atom nil))

(defn init! []
  (m/auth-anon root)
  (reset! todo-list
    {:items (sorted-map)}))

(defonce _init
  (do (init!)
      nil))
