(ns tmhas.events
  (:require-macros [adzerk.env :as env])
  (:require [re-frame.core :as rf]
            [ajax.core :as ajax :refer [GET]]
            [day8.re-frame.http-fx]
            [tmhas.db :as db]
            [tmhas.config :as config]))

(env/def API_KEY "EVENTUALLY")

(rf/reg-event-db
  :initialize-db
  (fn [_ _]
    db/default-db))

(rf/reg-event-db
  :set-filter-tag
  (fn [db [_ tag]]
    (if (= (:filter-tag db) tag)
      (assoc db :filter-tag nil) ;; deselect if already selected
      (assoc db :filter-tag tag))))

(rf/reg-event-fx
  :get-contentful-data
  (fn [{db :db} [_ db-key query space]]
    (when-not (db-key db)
      (let [endpoint (if config/debug? "http://localhost:4000/graphql/"
                                       "http://45.55.175.107:4000/graphql/")]
        {:db         db
         :http-xhrio {:method          :get
                      :format          (ajax/json-request-format)
                      :params          {:query query}
                      :uri             (str endpoint (name space))
                      :response-format (ajax/json-response-format {:keywords? true})
                      :on-failure      [:get-contentful-data-failed]
                      :on-success      [:get-contentful-data-success db-key]}}))))
(rf/reg-event-db
  :get-contentful-data-failed
  (fn [db _]
    (js/console.error ":get-contentful-data event failed, is the graph server running ?")
    db))

(rf/reg-event-db
  :get-contentful-data-success
  (fn [db [_ db-key & [{data :data}]]]
    (if (= db-key :media-posts)
      (let [posts (apply concat (vals data))
            sorted-posts (sort-by :postDate (comp - compare) posts)]
        (assoc db db-key sorted-posts))
      (assoc db db-key data))))

(rf/reg-event-db
  :set-active-panel
  (fn [db [_ active-panel & [post-id]]]
    (if post-id
      (assoc db :active-panel active-panel
                :active-post-id post-id
                :show-media-post true)
      (assoc db :active-panel active-panel))))

(rf/reg-event-db
  :set-show-media-post
  (fn [db [_ state]]
    (assoc db :show-media-post state)))
