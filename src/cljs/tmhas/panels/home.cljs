(ns tmhas.panels.home
  (:require [re-frame.core :as re-frame]
            [re-com.core :as re-com]))

(defn home-panel []
      [re-com/v-box
       :children
       [[:h1 {:class "bb bw1"} "Latest"]]])
