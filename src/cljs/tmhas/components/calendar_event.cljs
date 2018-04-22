(ns tmhas.components.calendar-event)

(defn event []
  [:div.event-wrapper.flexrow-wrap.pl3.mb3
   [:div.event-date.mr4.relative.tc
    [:i.fa.fa-calendar-o]
    [:div.calendar-date.w-100.absolute.flex.flex-column
     [:p.f5.mb0 "MAY"]
     [:p.f2 "03"]]]
   [:div.event-info
    [:h2.mt0.b "Participatory Budgeting Town Hall"]
    [:h3.f4.mt3 "5:30 PM - 7:30 PM"
     [:br] "@ Bruce Randolph School Cafeteria"]
    [:p "Join us on May 3rd to vote on how to spend $30,000 in our community! There will be food, fun, and the opportunity to participate in the finale of our two-part series of Town Halls designed by youth to increase youth civic participation in our community and to pilot the creation of a Participatory Budgeting Process. This process creates an infrastructure for community to PARTICIPATE in the ways we spend our tax dollars as a community and it allows us to RECLAIM the narrative about our communities and what we want for ourselves."]]])
