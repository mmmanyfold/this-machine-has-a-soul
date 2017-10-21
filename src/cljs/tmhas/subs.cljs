(ns tmhas.subs
  (:require-macros [reagent.ratom :refer [reaction]])
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
