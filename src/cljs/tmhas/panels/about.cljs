(ns tmhas.panels.about
  (:require [re-frame.core :as rf]
            [re-com.core :as rc]
            [tmhas.components.common :refer [showdown]]
            [tmhas.components.loading :refer [loading-component]]
            [camel-snake-kebab.core :refer [->kebab-case]]
            [clojure.string :refer [lower-case]]))

(def query
  "{ aboutSections {
			 sectionTitle, sectionContent, sectionOrder
		 }
	}")

(defn about-panel []
  (let [db-key :about-panel]                            ;; 0. declare unique db-key
    (rf/reg-sub db-key #(db-key %))                  ;; 1. register subscriber db-key
    (rf/dispatch [:get-contentful-data db-key query :about]) ;; 2. retrieve contentful data & pass key for assoc in db
    (if-let [about-sections (:aboutSections @(rf/subscribe [db-key]))]
      (let [about-sections-sorted (sort-by :sectionOrder about-sections)]
        [rc/v-box
         :children
         [(for [section about-sections-sorted
                 :let [title (:sectionTitle section)
                       content  (:sectionContent section)]]
            ^{:key (gensym "aboutSection-")}
             [:div.about-section {:id (->kebab-case (lower-case title))}
              [:h1 {:id (str (->kebab-case (lower-case title)) "-section")} title]
              [:div {:class "markdown"
                     "dangerouslySetInnerHTML"
                      #js{:__html (.makeHtml showdown content)}}]])]])
      [loading-component])))
