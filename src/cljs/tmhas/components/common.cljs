(ns tmhas.components.common
  (:require [re-com.core :as re-com]
            [cljsjs.showdown]))

(defn link [label to]
      [re-com/hyperlink-href
       :label label
       :href (str "#" to)])

;; for parsing markdown with showdown
(def showdown (js/showdown.Converter.))
