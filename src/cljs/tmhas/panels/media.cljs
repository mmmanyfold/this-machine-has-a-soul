(ns tmhas.panels.media
  (:require [re-frame.core :as rf]
            [re-com.core :as rc]
            [tmhas.components.media-thumb :as media-thumb]
            [tmhas.panels.media-post :refer [media-post-panel]]
            [tmhas.components.common :refer [showdown]]))

(def query
  "{
    imageGalleryPosts {
      sys { id contentTypeId }
			postText
			postDate
			postTitle
			tags
			images { url description }
		},
    singleImageTextPosts {
      sys { id contentTypeId }
			postText
			postDate
			postTitle
			tags
			imageFile { url description }
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

(def filter-tag (rf/subscribe [:filter-tag]))

(defn media-panel []
  (let [db-key :media-posts]                                 ;; 0. declare unique db-key
    (rf/reg-sub db-key #(db-key %))                          ;; 1. register subscriber db-key
    (rf/dispatch [:get-contentful-data db-key query :media]) ;; 2. retrieve contentful data & pass key for assoc in db
    (let [posts (rf/subscribe [db-key])]
      [rc/v-box
       :children
       [[rc/v-box
         :children
         [[media-post-panel posts]
          [:div {:class "flexrow-wrap w-100 mt1"}
           (doall
             (for [post @posts
                   :let [post-id (-> post :sys :id)
                         tags (set (-> post :tags))
                         type (-> post :sys :contentTypeId)]
                   :when (or (nil? @filter-tag)
                             (contains? tags @filter-tag))]
               (case type
                 "manyImagePost" ^{:key post-id}[media-thumb/image-gallery post-id post]
                 "singleImage" ^{:key post-id}[media-thumb/single-image post-id post]
                 "video" ^{:key post-id}[media-thumb/video post-id post])))]]]]])))
