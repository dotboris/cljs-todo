(ns todo.core)

(enable-console-print!)

(defonce *count* (atom 5))

(defn do-thing []
  (println "did a thing")
  (this-as js-this
    (let [$this (js/$ js-this)
          $item (.parent $this ".item")
          $count (js/$ ".todo .count")]
      (if (not (.hasClass $item "done"))
        (do
          (.addClass $item "done")
          (.addClass $this "disabled")
          (swap! *count* dec)
          (.html $count @*count*))))))

(defn set-callbacks! []
  "set the page callbacks"
  (.click (js/$ ".todo .item button") do-thing))

(set-callbacks!)

(defn on-js-reload [])
