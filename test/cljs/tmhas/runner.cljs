(ns tmhas.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [tmhas.core-test]))

(doo-tests 'tmhas.core-test)
