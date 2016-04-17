(ns todo.storage-test
  (:require [cljs.test :refer-macros [async deftest is testing]]
            [todo.storage :as s]))

(deftest todo-list-atom
  (is (not (empty? (get @s/todo-list :items)))))

(deftest make-item
  (testing "1 arg"
    (is (not (s/done? (s/make-item "foobar"))))
    (is (= "Do a thing" (s/text (s/make-item "Do a thing")))))
  (testing "2 args"
    (is (s/done? (s/make-item "foo" true)))
    (is (not (s/done? (s/make-item "foo" false))))
    (is (= "stuff" (s/text (s/make-item "stuff" true))))
    (is (= "stuff" (s/text (s/make-item "stuff" false))))))

(deftest done?
  (is (s/done? {:done true}))
  (is (not (s/done? {:done false}))))

(deftest text
  (is (= "Do a thing!" (s/text (s/make-item "Do a thing!")))))

(deftest toggle-done
  (let [done (s/make-item "something" true)
        not-done (s/make-item "something else" false)]
    (is (s/done? (s/toggle-done not-done)))
    (is (not (s/done? (s/toggle-done done))))))
