(ns todo.test
  (:require [doo.runner :refer-macros [doo-tests]]
            [todo.core-test]
            [todo.storage-test]))

(doo-tests 'todo.core-test
           'todo.storage-test)
