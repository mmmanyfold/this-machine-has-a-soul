(ns tmhas.panels.submit-ideas)

(defn submit-ideas-panel []
  [:div.w-100
   [:iframe
     {:marginWidth "0",
      :marginHeight "0",
      :frameBorder "0",
      :height "1700px",
      :width "100%",
      :src
      "https://docs.google.com/forms/d/e/1FAIpQLSdqe9Fu8c3kaOcCQG87QkqOzLnrwWj0Pl6DlGecJ-1Dc-YQEg/viewform?embedded=true"}
     "Loading..."]])
