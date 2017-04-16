(ns pair-generator.core
  (:require [reagent.core :as reagent :refer [atom]]
            [pair-generator.generator :refer [generate-pair]]
            [clojure.string :as str]))

(enable-console-print!)

(defn on-js-reload [])

(defonce app-state (atom {:input ""
                          :pairs []}))

(defn pure-update-pair [state]
  (let [result (generate-pair (:input state))]
    (assoc state :pairs result)))

(defn update-pair []
  (swap! app-state pure-update-pair))

(defn update-input [event]
  (swap! app-state assoc :input event.target.value))

(defn root-element [state]
  [:div.container
   [:h1 "Pair Generator"]
   [:p "To generate random pairs, insert a list of names (each name on a seperate line) in the text box below:"]
   [:div
    [:textarea {:rows      (+ 2 (count (str/split (:input state) #"\n")))
                :value     (:input state)
                :on-change update-input}]
    [:button.submit {:type     "submit"
                     :on-click update-pair}
     "Generate"]]

   (when (not-empty (:pairs state))
     [:div.result
      [:p.header "These are your pairs:"]
      [:div.result-table
       [:table
        [:tbody
         (for [pair (:pairs state)
               :let [[first second] pair]]
           [:tr {:key (hash pair)}
            [:td first]
            [:td second]])]]]])])

(defn wrapper [app-state]
  (root-element @app-state))

(reagent/render-component [wrapper app-state]
                          (. js/document (getElementById "app")))
