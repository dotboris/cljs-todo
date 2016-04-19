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

(deftest toggle-done
  (let [done (s/make-item "something" :done true)
        not-done (s/make-item "something else" :done false)]
    (is (s/done? (s/toggle-done not-done)))
    (is (not (s/done? (s/toggle-done done))))))
