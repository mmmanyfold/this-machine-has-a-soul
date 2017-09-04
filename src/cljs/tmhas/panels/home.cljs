(ns tmhas.panels.home
  (:require [re-com.core :as re-com]
            [re-frame.core :as rf]))

(defn home-title []
      (let [name (rf/subscribe [:name])]
           (fn []
               [re-com/title
                :label "This is the Home Page."
                :level :level1])))

(defn home-panel []
      [re-com/v-box
       :gap "1em"
       :children [[home-title]]])