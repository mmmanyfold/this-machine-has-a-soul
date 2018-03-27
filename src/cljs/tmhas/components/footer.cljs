(ns tmhas.components.footer
  (:require [re-com.core :as rc]
            [re-frame.core :as rf]))

(defn footer []
  [:div {:class "bt bw1 mh3 pv3 ph1"
         :style {:margin-top "-0.3em"}}
   [:div {:on-click #(js/window.showMailingPopUp)}
     [:span {:class "mail bb bw1 pointer mt1 mb4"}
        "Join Mailing List >>"]]])
