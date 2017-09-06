(ns tmhas.panels.about
  (:require [re-frame.core :as rf]
            [re-com.core :as re-com]))

(def query
  "{ aboutSections {
			 sectionTitle, sectionText
		 }
	}")

(defn about-panel []
      (let [ref-key :about-panel]                                ;; 0. declare unique ref-key
           (rf/reg-sub ref-key #(ref-key %))                     ;; 1. register subscriber ref-key
           (rf/dispatch [:get-contentful-data ref-key query])    ;; 2. retrieve contentful data & pass key for assoc in db

           (let [aboutSections (:aboutSections @(rf/subscribe [ref-key]))] ;; 3. react to retrieved data
                [re-com/v-box
                 :children
                 ;; TODO: needs markdown parser here
                 [(for [section aboutSections
                         :let [title (:sectionTitle section)
                               text  (:sectionText section)]]
                    ^{:key (gensym "aboutSection-")}
                     [:div
                      [:h1
                       {:class "bb bw1"}
                       title]
                      [:p text]])]])))
