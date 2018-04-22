(ns tmhas.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub
 :active-panel
 (fn [db _]
   (:active-panel db)))

(rf/reg-sub
 :active-post-id
 (fn [db _]
   (:active-post-id db)))

(rf/reg-sub
 :show-media-post
 (fn [db _]
   (:show-media-post db)))

(rf/reg-sub
  :filter-tag
  (fn [db _]
    (:filter-tag db)))

(rf/reg-sub
  :calendar-events
  (fn [db _]
    (:calendar-events db)))
