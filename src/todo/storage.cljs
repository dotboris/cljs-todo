(ns todo.storage)

(defn make-item
  ([text] {:done false :text text})
  ([text done?] {:done done? :text text}))

(def original-list
  {:items [(make-item "Learn ClojureScript")
           (make-item "Learn om")
           (make-item "or reagent")
           (make-item "Find something cool to build")
           (make-item "Build that thing")
           (make-item "Figure out how to use cljs with electron")
           (make-item "Anything else?")]})

(defonce todo-list (atom original-list))

(defn text [item]
  (get item :text))

(defn done? [item]
  (get item :done))

(defn toggle-done [item]
  (assoc item :done (not (get item :done))))
