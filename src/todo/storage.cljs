(ns todo.storage
  (:require [reagent.core :as r]
            [matchbox.core :as m]))

(defonce root (m/connect "https://almighty-todo.firebaseio.com/"))

(defonce todo-list (r/atom nil))

(defn init! []
  (m/auth-anon root)
  (reset! todo-list (sorted-map)))

(defonce _init
  (do (init!)
      nil))
