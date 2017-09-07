(ns tmhas.panels.about
  (:require [re-frame.core :as rf]
            [re-com.core :as re-com]
            [tmhas.components.common :refer [showdown]]))

(def query
  "{ aboutSections {
			 sectionTitle, sectionContent, sectionOrder
		 }
	}")

(defn about-panel []
      (let [rf-key :about-panel]                                ;; 0. declare unique rf-key
           (rf/reg-sub rf-key #(rf-key %))                      ;; 1. register subscriber rf-key
           (rf/dispatch [:get-contentful-data rf-key query])    ;; 2. retrieve contentful data & pass key for assoc in db

           (let [about-sections (:aboutSections @(rf/subscribe [rf-key]))
                 about-sections-sorted (sort-by :sectionOrder about-sections)]
                [re-com/v-box
                 :children
                 [(for [section about-sections-sorted
                         :let [title (:sectionTitle section)
                               content  (:sectionContent section)]]
                    ^{:key (gensym "aboutSection-")}
                     [:div.about-section
                      [:h1 {:class "bb bw1"} title]
                      [:div {"dangerouslySetInnerHTML"
                              #js{:__html (.makeHtml showdown content)}}]])]])))
