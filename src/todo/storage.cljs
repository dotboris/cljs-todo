(ns todo.storage)

(defn make-item [text]
  {:done false
   :text text})

(defonce todo-list (atom {:items [(make-item "Learn ClojureScript")
                                  (make-item "Learn om")
                                  (make-item "or reagent")
                                  (make-item "Find something cool to build")
                                  (make-item "Build that thing")
                                  (make-item "Figure out how to use cljs with electron")]}))

(defn done? [item]
  (get item :done))
