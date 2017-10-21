(ns tmhas.panels.latest
  (:require [re-frame.core :as rf]
            [re-com.core :as re-com]
            [tmhas.components.media-thumb :as media-thumb]
            [tmhas.panels.full-media-post :refer [full-media-post-panel]]
            [tmhas.components.common :refer [showdown]]))

(def query
  "{
    imageGalleryPosts {
      sys { id contentTypeId }
			postText
			postDate
			postTitle
			tags
			images { url }
		},
    singleImageTextPosts {
      sys { id contentTypeId }
			postText
			postDate
			postTitle
			tags
			imageFile { url }
		},
    videoPosts {
      sys { id contentTypeId }
			postText
			postDate
			postTitle
			tags
			videoUrl
		}
	}")

(defn latest-panel []
      (let [db-key :latest-panel]                                   ;; 0. declare unique db-key
           (rf/reg-sub db-key #(db-key %))                          ;; 1. register subscriber db-key
           (rf/dispatch [:get-contentful-data db-key query :media]) ;; 2. retrieve contentful data & pass key for assoc in db
           (let [posts @(rf/subscribe [db-key])]
                [re-com/v-box
                 :children
                 [[re-com/v-box
                   :children
                   [[full-media-post-panel]
                    [:h1 {:class "bb bw1"} "Latest Posts"]
                    [:div {:class "flexrow-wrap w-100 mv3"}
                     (for [post posts
                           :let [entry-id (-> post :sys :id)
                                 type (-> post :sys :contentTypeId)]]
                          (case type
                            "manyImagePost" ^{:key entry-id}[media-thumb/image-gallery entry-id post]
                            "singleImage" ^{:key entry-id}[media-thumb/single-image entry-id post]
                            "video" ^{:key entry-id}[media-thumb/video entry-id post]))]]]]])))
