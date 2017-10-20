(ns tmhas.panels.home
  (:require [re-frame.core :as rf]
            [re-com.core :as re-com]
            [tmhas.components.media-thumb :as media-thumb]
            [tmhas.panels.media-post :refer [media-post-panel]]
            [tmhas.components.common :refer [showdown]]))

;; latest

(def query
  "{ imageGalleryPosts {
      sys { id }
			postText
			postDate
			postTitle
			tags
			images { url }
		 }
	}")

(defn home-panel []
      (let [rf-key :latest-panel]                                   ;; 0. declare unique rf-key
           (rf/reg-sub rf-key #(rf-key %))                          ;; 1. register subscriber rf-key
           (rf/dispatch [:get-contentful-data rf-key query :media]) ;; 2. retrieve contentful data & pass key for assoc in db
           (let [image-gallery-posts (:imageGalleryPosts @(rf/subscribe [rf-key]))]
                [re-com/v-box
                 :children
                 [[re-com/v-box
                   :children
                   [[media-post-panel]
                    [:h1 {:class "bb bw1"} "Latest"]
                    [:div {:class "flexrow-wrap w-100 mv3"}
                     (for [post image-gallery-posts
                           :let [entry-id (-> post :sys :id)]]
                          ^{:key entry-id}
                          [media-thumb/image-gallery post])]]]]])))
