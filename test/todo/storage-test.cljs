(ns todo.storage-test
  (:require [cljs.test :refer-macros [async deftest is testing]]
            [todo.storage :as s]))

(testing "todo-list atom"
  (is (not (empty? (get @s/todo-list :items)))))

(testing "make-item"
  (is (not (s/done? (s/make-item "foobar"))))
  (is (= (get (s/make-item "foobar") :text) "foobar")))

(testing "done?"
  (is (s/done? {:done true}))
  (is (not (s/done? {:done false}))))
