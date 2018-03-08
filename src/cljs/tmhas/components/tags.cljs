(ns tmhas.components.tags
  (:require [re-frame.core :as rf]))

(def query
  "{
    imageGalleryPosts {
      sys { id contentTypeId }
			tags
		},
    singleImageTextPosts {
      sys { id contentTypeId }
			tags
		},
    videoPosts {
      sys { id contentTypeId }
			tags
		}
	}")

(defn tags []
  (let [db-key :tag-component]                               ;; 0. declare unique db-key
    (rf/reg-sub db-key #(db-key %))                          ;; 1. register subscriber db-key
    (rf/dispatch [:get-contentful-data db-key query :media]) ;; 2. retrieve contentful data & pass key for assoc in db
    (fn []
      (let [tags-&-meta (rf/subscribe [db-key])]
        (prn @tags-&-meta)
        [:div#tags
         "test"]))))
