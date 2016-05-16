(ns todo.storage
  (:require [reagent.core :as r]
            [matchbox.core :as m]
            [matchbox.reagent :as mr]))

(def *list-id* :default-hardcoded)

(defonce root (m/connect "https://almighty-todo.firebaseio.com/"))

(defonce items-ref (m/get-in root [:items *list-id*]))
(defonce todo-list (mr/sync-r items-ref))

(defn init! []
  (m/auth-anon root)
  (reset! todo-list (sorted-map)))

(defonce _init
  (do (init!)
      nil))
