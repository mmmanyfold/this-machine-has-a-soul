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
      "https://docs.google.com/forms/d/e/1FAIpQLScRHmGvRMhAIweOvIadlpoBKeqZnjVO3h4VfKQZL1_gLrUt5A/viewform?embedded=true"}
     "Loading..."]])
