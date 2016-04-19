(ns todo.storage-test
  (:require [cljs.test :refer-macros [async deftest is testing]]
            [todo.storage :as s]))

(deftest todo-list-atom
  (is (not (empty? (get @s/todo-list :items)))))

(deftest make-item
  (testing "no keys"
    (is (not (s/done? (s/make-item "foobar"))))
    (is (= "Do a thing" (s/text (s/make-item "Do a thing"))))
    (is (:id (s/make-item "whatever"))))
  (testing ":done key"
    (is (s/done? (s/make-item "foo" :done true)))
    (is (not (s/done? (s/make-item "foo" :done false))))))

(deftest done?
  (is (s/done? {:done true}))
  (is (not (s/done? {:done false}))))

(deftest text
  (is (= "Do a thing!" (s/text (s/make-item "Do a thing!")))))

(deftest toggle!
  (let [done (s/make-item "done" :done true)
        not-done (s/make-item "not-done" :done false)]
    (do (swap! s/todo-list update-in [:items] empty)
        (swap! s/todo-list update-in [:items] assoc 1 done)
        (s/toggle! 1)
        (is (not (s/done? (get-in @s/todo-list [:items 1])))))
    (do (swap! s/todo-list update-in [:items] empty)
        (swap! s/todo-list update-in [:items] assoc 1 not-done)
        (s/toggle! 1)
        (is (s/done? (get-in @s/todo-list [:items 1]))))))
