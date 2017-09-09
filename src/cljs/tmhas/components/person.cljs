(ns tmhas.components.person
  (:require [re-com.core :as re-com]))

(defn person []
      [:div {:class "flexrow-wrap w-100 mv3"}
         [:div {:class "w-100 w-25-ns tc"}
             [:img {:src "/img/candi.png"
                    :class "w-70 w-100-ns br-100 mb3"}]]
         [re-com/v-box
          :class "w-100 w-75-ns pl4-ns"
          :children [[:h2 {:class "mt0 f2 f3-ns tc"}
                         "Candi CdeBaca"]
                     [:p "Candi CdeBaca is Co-Founder and Co-Executive Director of Project VOYCE, Founder & Member of Cross Community Coalition and Founder & Principal of Rebel Soul Strategies. She is a Community Advocate in her home community of Northeast Denver. She has been featured as an influential leader and advocate by several publications and outlets including Politico, Fortune, Forbes, New York Times, The Guardian, The Atlantic, 5280 Magazine, and Canadian Broadcast Corporation. She has also authored and co-authored publications featured in the Nonprofit Quarterly and Equity Alliance. She is a fierce advocate for equity. CdeBaca has an unwavering commitment to ensuring increased participation of underrepresented groups in the political process and in leadership and decision-making roles across sectors."]]]])
