(ns tmhas.components.person
  (:require [re-com.core :as rc]
            [tmhas.components.common :refer [showdown]]))

(defn person [data]
      [:div {:class "flexrow-wrap w-100 mv3"}
         [:div {:class "w-100 w-25-ns tc"}
             [:img {:src (-> data :image :url)
                    :class "w-70 w-100-ns br-100 mb3"}]]
         [rc/v-box
          :class "w-100 w-75-ns pl4-ns"
          :children [[:h2 {:class "mt0 f2 f3-ns"}
                         (:name data)]
                     [:div {:class "markdown"
                            "dangerouslySetInnerHTML"
                            #js{:__html (.makeHtml showdown (:bio data))}}]]]])
