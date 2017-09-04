(ns tmhas.components.common
  (:require [re-com.core :as re-com]))

(defn link [label to]
      [re-com/hyperlink-href
       :label label
       :href (str "#/" to)])
