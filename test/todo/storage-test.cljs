(ns todo.storage-test
  (:require [cljs.test :refer-macros [async deftest is testing]]
            [todo.storage :as s]))

(deftest todo-list-atom
  (is (not (empty? (get @s/todo-list :items)))))

(deftest make-item
  (is (not (s/done? (s/make-item "foobar"))))
  (is (= (get (s/make-item "foobar") :text) "foobar")))

(deftest done?
  (is (s/done? {:done true}))
  (is (not (s/done? {:done false}))))

(deftest text
  (is (= "Do a thing!" (s/text (s/make-item "Do a thing!")))))
