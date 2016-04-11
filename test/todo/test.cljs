(ns todo.test
  (:require [doo.runner :refer-macros [doo-tests]]
            [todo.core-test]))

(doo-tests 'todo.core-test)
