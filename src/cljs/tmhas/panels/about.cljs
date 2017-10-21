(ns tmhas.panels.about
  (:require [re-frame.core :as rf]
            [re-com.core :as rc]
            [tmhas.components.common :refer [showdown]]))

(def query
  "{ aboutSections {
			 sectionTitle, sectionContent, sectionOrder
		 }
	}")

(defn about-panel []
      (let [db-key :about-panel]                            ;; 0. declare unique db-key
           (rf/reg-sub db-key #(db-key %))                  ;; 1. register subscriber db-key
           (rf/dispatch [:get-contentful-data db-key query :about]) ;; 2. retrieve contentful data & pass key for assoc in db

           (let [about-sections (:aboutSections @(rf/subscribe [db-key]))
                 about-sections-sorted (sort-by :sectionOrder about-sections)]
                [rc/v-box
                 :class "mb4"
                 :children
                 [(for [section about-sections-sorted
                         :let [title (:sectionTitle section)
                               content  (:sectionContent section)]]
                    ^{:key (gensym "aboutSection-")}
                     [:div.about-section
                      [:h1 {:class "bb bw1"} title]
                      [:div {:class "markdown"
                             "dangerouslySetInnerHTML"
                              #js{:__html (.makeHtml showdown content)}}]])]])))
