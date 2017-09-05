(ns tmhas.panels.about
  (:require [re-frame.core :as rf]
            [re-com.core :as re-com]
            [goog.string :as gstring]
            goog.string.format))

(def entry-id "eJCWlzmH8Ai2euGY2eQm4")

(def query
  (gstring/format "{ aboutSections(q: \"sys.id=%s\") {
			 sectionTitle, sectionText
		 }
	}" entry-id))

(defn about-panel []
      (let [ref-key :about-panel]                                ;; 0. declare unique ref-key
           (rf/reg-sub ref-key #(ref-key %))                     ;; 1. register subscriber ref-key
           (rf/dispatch [:get-contentful-data ref-key query])    ;; 2. retrieve contentful data & pass key for assoc in db

           (let [aboutSections (first (:aboutSections @(rf/subscribe [ref-key])))] ;; 3. react to retrieved data
                [re-com/v-box
                 :children
                 [[:h1
                   {:class "bb bw1"}
                   (:sectionTitle aboutSections)]
                  ;; TODO: needs markdown parser here
                  [:p (:sectionText aboutSections)]]])))