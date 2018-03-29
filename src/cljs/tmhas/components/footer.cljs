(ns tmhas.components.footer
  (:require [re-com.core :as rc]
            [re-frame.core :as rf]))

(defn footer []
  [:div {:class "footer bt bw1 mh3 mh4-ns ph1 pv3 tr"}
   [:span {:on-click #(js/window.showMailingPopUp)
           :class "mail f3 bb bw1 pointer pb1"}
      "Join Mailing List >>"]])
