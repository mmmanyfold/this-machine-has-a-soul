(ns tmhas.panels.about
  (:require [re-frame.core :as re-frame]
            [re-com.core :as re-com]))

(defn about-panel []
      [re-com/v-box
       :children
       [; TODO: replace text with contentful data
        [:h1 "About the Project"]
        [:p "This Machine Has A Soul! is a community project that combines participatory budgeting with artworks and performances inspired by Rube Goldberg machines."]
        [:p "You might be asking what one has to do with the other. Well, a Rube Goldberg machine takes a simple process and complicates it in a fun way. Designing budgets that allocate public funds is a complicated process involving countless people, interests, and systems. A Rube Goldberg machine is a perfect metaphor for the budgeting process and allows us to explore its themes of complexity and simplicity. More than this, the artwork they inspire serves as a fun and engaging entry point for discussions about the current model of local budgeting as it compares to participatory budgeting."]
        [:p "Participatory budgeting (PB) offers an alternative to the traditional model by giving a community a portion of public funds to spend on projects in their neighborhoods at their discretion. Communities come together to propose ideas for using funds to improve their neighborhoods, design project proposals, and host a vote to decide which projects are implemented. In a nutshell: PB gives citizens more than a voice in the decision-making process; it gives them real decision-making power over real money and puts the soul back into the machine."]
        [:p "This Machine has a Soul! is committed to bringing participatory budgeting to Denver by piloting two PB processes in District 9 over the course of a year from August 2017 to August 2018. Project Belay and resident leaders from the Cole neighborhood, will lead a pb process anchored in Cole and supported by participants who live, work, or attend school there. Students from Project VOYCE, a non-profit working with Denver youth to encourage civic engagement and action, will lead a PB process in Elyria and Swansea. Participants in each PB process will design and vote on project proposals that decide how $30,000 will be invested in their communities. Civic health club Warm Cookies of the Revolution along with other local artists will design artworks and performances that support the work of resident leaders and the PB process, and stir the imagination of District 9 residents, spurring artistic collaboration and civic action."]
        [:p "This Machine has a Soul! is generously funded by grants from ArtPlace America; Arts in Society with the support of the Bonfils-Staton Foundation, Hemera Foundation, and Colorado Creative Industries; and Colorado Art Tank with the support of Denver Arts and Venues and Denver Foundation’s Arts Affinity Group."]

        [:h1 "Press Kit"]
        [:p "Links"]

        [:h1 "Contact"]
        [:p "For more information, please contact Jenette Preciado at jenette@warmcookiesoftherevolution.net."]]])
