(ns todo.core-test
  (:require [cljs.test :refer-macros [async deftest is testing]]))

(deftest sanity
  (is (= (+ 2 2) 4)))
