(ns todo.core-test
  (:require [cljs.test :refer-macros [async deftest is testing]]
            [todo.core :as t]
            [todo.storage :as s]))

(deftest make-item
  (testing "no keys"
    (is (not (t/done? (t/make-item "foobar"))))
    (is (= "Do a thing" (t/text (t/make-item "Do a thing"))))
    (is (:id (t/make-item "whatever"))))
  (testing ":done key"
    (is (t/done? (t/make-item "foo" :done true)))
    (is (not (t/done? (t/make-item "foo" :done false))))))

(deftest done?
  (is (t/done? (t/make-item "foobar" :done true)))
  (is (not (t/done? (t/make-item "foobar" :done false)))))

(deftest text
  (is (= "Do a thing!" (t/text (t/make-item "Do a thing!")))))

#_(deftest toggle!
    (let [done (t/make-item "done" :done true)
          not-done (t/make-item "not-done" :done false)]
      (do (s/init!)
          (swap! s/todo-list assoc 1 done)
          (t/toggle! 1)
          (is (false? (t/done? (get @s/todo-list 1)))))
      (do (s/init!)
          (swap! s/todo-list assoc 1 not-done)
          (t/toggle! 1)
          (is (true? (t/done? (get @s/todo-list 1)))))))

#_(deftest add-item!
    (do (s/init!)
        (t/add-item! "foobar")
        (let [items @s/todo-list
              [id item] (first items)]
          (is (= 1 (count items)))
          (is (= "foobar" (:text item)))
          (is (false? (:done item))))))

#_(deftest remove-item!
    (do (s/init!)
        (swap! s/todo-list assoc 1 (t/make-item "fi"))
        (swap! s/todo-list assoc 2 (t/make-item "fo"))
        (swap! s/todo-list assoc 3 (t/make-item "fum"))
        (t/remove-item! 2)
        (let [items @s/todo-list]
          (is (= 2 (count items)))
          (is (get items 1))
          (is (nil? (get items 2)))
          (is (get items 3)))))
