(ns pair-generator.core
  (:require [reagent.core :as reagent :refer [atom]]
            [clojure.string :as str]))

(enable-console-print!)

(defn on-js-reload [])

(defonce app-state (atom {:input ""
                          :pairs []}))

(defn generate-pair [input]
  (if (str/blank? input)
    []
    (let [input-data (->> (str/split input ",")
                          (map str/trim)
                          distinct
                          shuffle)
          per-group  2
          padding    "self :("]
      (partition per-group per-group [padding] input-data))))

(defn pure-update-pair [state]
  (let [result (generate-pair (:input state))]
    (assoc state :pairs result)))

(defn update-pair []
  (swap! app-state pure-update-pair))

(defn update-input [event]
  (swap! app-state assoc :input event.target.value))

(defn root-element [state]
  [:div
   [:h1 "Generate Pair"]
   [:p "Insert names (comma separated):"]
   [:div
    [:input {:type "text"
             :value (:input state)
             :on-change update-input}]
    [:button {:type "submit"
              :on-click update-pair}
     "Generate"]]

   (if (not-empty (:pairs state))
     [:div.result
      (for [pair (:pairs state)]
        [:p {:key (hash pair)}
         (str/join " - " pair)])])])

(defn wrapper [app-state]
  (root-element @app-state))

(reagent/render-component [wrapper app-state]
                          (. js/document (getElementById "app")))
