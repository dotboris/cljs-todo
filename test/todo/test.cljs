(ns ^:figwheel-always todo.test
  (:require
    [cljs.test :as test :include-macros true :refer [report]]
    [goog.dom :as dom]
    [todo.core-test]
    [todo.storage-test]))

(enable-console-print!)

(defn color-favicon-data-url [color]
  (let [cvs (.createElement js/document "canvas")]
    (set! (.-width cvs) 16)
    (set! (.-height cvs) 16)
    (let [ctx (.getContext cvs "2d")]
      (set! (.-fillStyle ctx) color)
      (.fillRect ctx 0 0 16 16))
    (.toDataURL cvs)))

(defn nodelist->clj [nodelist]
  (->> nodelist
      (.call (-> js/Array .-prototype .-slice))
      js->clj))

(defonce favicon
  (let [el (dom/createDom "link" #js {:rel "shortcut icon"
                                      :type "image/png"
                                      :href (color-favicon-data-url "#ddd")})
        head-el (-> (dom/getElementsByTagNameAndClass "head")
                    nodelist->clj
                    first)]
    (do (dom/append head-el el)
        el)))

(defn change-favicon-to-color [color]
  (set! (.-href favicon) (color-favicon-data-url color)))

(defmethod report [::test/default :summary] [m]
  (let [pass (:pass m)
        fail (:fail m)
        error (:error m)
        fail-error (+ fail error)
        total (+ pass fail error)
        success? (= 0 fail-error)]
    (println "\nRan" (:test m) "tests containing"
             total "assertions.")
    (println fail "failures," error "errors.")
    (set! (.-title js/document)
          (str (if success? "✓" "✗") " " pass "/" total))
    (if success?
      (change-favicon-to-color "#0d0")
      (change-favicon-to-color "#d00"))))

(test/run-tests 'todo.core-test
                'todo.storage-test)
