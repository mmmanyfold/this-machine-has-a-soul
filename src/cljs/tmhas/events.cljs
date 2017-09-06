(ns tmhas.events
  (:require-macros [adzerk.env :as env])
  (:require [re-frame.core :as rf]
            [ajax.core :as ajax :refer [GET]]
            [day8.re-frame.http-fx]
            [tmhas.db :as db]))

(env/def API_KEY "EVENTUALLY")

(rf/reg-event-fx
  :get-contentful-data
  (fn [{db :db} [_ ref-key query]]
      ;; TODO: add loading state...
      {:db         db
       :http-xhrio {:method          :get
                    ;:headers         {:Authorization (str "Bearer " API_KEY)}
                    :format          (ajax/json-request-format)
                    :params          {:query query}
                    :uri             "http://localhost:4000/graphql" ;; local dev server
                    ; :uri             "https://node-project-starter-hxumagngjh.now.sh/graphql" ;; stage dev server
                    :response-format (ajax/json-response-format {:keywords? true})
                    :on-success      [:get-contentful-data-success ref-key]}}))

(rf/reg-event-db
  :get-contentful-data-success
  (fn [db [_ ref-key & [{data :data}]]]
      (assoc db ref-key data)))


(rf/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))

(rf/reg-event-db
 :set-active-panel
 (fn [db [_ active-panel]]
   (assoc db :active-panel active-panel)))
