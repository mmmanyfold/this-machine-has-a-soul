(ns tmhas.events
  (:require-macros [adzerk.env :as env])
  (:require [re-frame.core :as rf]
            [ajax.core :as ajax :refer [GET]]
            [day8.re-frame.http-fx]
            [tmhas.db :as db]
            [tmhas.config :as config]))

(env/def API_KEY "EVENTUALLY")

(rf/reg-event-fx
  :get-contentful-data
  (fn [{db :db} [_ db-key query space]]
      ;; TODO: add loading state...
      (let [endpoint (if config/debug? "http://localhost:4000/graphql/"
                                       "https://this-machine-has-a-soul-jppxccuwtc.now.sh/graphql/")]
           {:db         db
            :http-xhrio {:method          :get
                         :format          (ajax/json-request-format)
                         :params          {:query query}
                         :uri             (str endpoint (name space))
                         :response-format (ajax/json-response-format {:keywords? true})
                         :on-success      [:get-contentful-data-success db-key]}})))

(rf/reg-event-db
  :get-contentful-data-success
  (fn [db [_ db-key & [{data :data}]]]
    (if (= db-key :latest-panel)
      (let [posts (apply concat (vals data))
            sorted-posts (sort-by :postDate (comp - compare) posts)]
        (assoc db db-key sorted-posts))
      (assoc db db-key data))))

(rf/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))

(rf/reg-event-db
 :set-active-panel
 (fn [db [_ active-panel]]
   (assoc db :active-panel active-panel)))
