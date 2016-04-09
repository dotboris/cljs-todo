(ns todo.core
  (:require [goog.dom :as dom]
            [goog.dom.classes :as classes]
            [goog.events :as events]))

(enable-console-print!)

(defonce *count* (atom 5))

;; dom elements we're playing with
(def todo-elem (dom/getElementByClass "todo"))
(def item-button-elems
  (mapcat #(array-seq (dom/getElementsByTagNameAndClass "button"))
          (array-seq (dom/getElementsByClass "item"))))
(def counter-elem (dom/getElementByClass "count"))

(defn do-thing []
  (this-as self
    (println "did a different thing")
    (let [item-elem (dom/getAncestorByTagNameAndClass self nil "item")]
      (do
        (classes/enable item-elem "done" true)
        (classes/enable self "disabled" true)
        (swap! *count* dec)
        (dom/setTextContent counter-elem @*count*)))))

(defn set-events! []
  (doseq [button item-button-elems]
    (do
      (events/removeAll button "click")
      (events/listenOnce button "click" do-thing))))

(def on-js-reload set-events!)

(set-events!)
