(ns tmhas.components.common
  (:require [re-com.core :as rc]
            [cljsjs.showdown]))

(defn link [label to]
      [rc/hyperlink-href
       :label label
       :href (str "#" to)])

;; for parsing markdown with showdown
(def showdown (js/showdown.Converter.))
