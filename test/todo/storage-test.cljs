(ns todo.storage-test
  (:require [cljs.test :refer-macros [async deftest is testing]]
            [todo.storage :as s]))

(deftest todo-list-atom
  (do (s/init!)
      (is (empty? @s/todo-list))))
