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

(defonce _request-notify-rights
  (when js/Notification
    (.requestPermission js/Notification)))

(defn results-text [pass total]
  (let [success? (= pass total)]
    (str (if success? "✓" "✗") " " pass "/" total)))

(defn notify-results! [pass error fail]
  (when (and js/Notification
             (= "granted" (-> js/Notification .-permission)))
    (let [total (+ pass error fail)
          success? (= pass total)
          options #js {:icon (color-favicon-data-url
                               (if success? "#0d0" "#d00"))
                       :renotify true
                       :silent true
                       :body (str pass " passes, "
                                  fail " fails, "
                                  error " errors")}]
      (js/Notification. (results-text pass total) options))))

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
        total (+ pass fail error)
        success? (= pass total)]
    (println "\nRan" (:test m) "tests containing"
             total "assertions.")
    (println fail "failures," error "errors.")
    (set! (.-title js/document)
          (results-text pass total))
    (change-favicon-to-color (if success? "#0d0" "#d00"))
    (notify-results! pass error fail)))

(test/run-tests 'todo.core-test
                'todo.storage-test)
