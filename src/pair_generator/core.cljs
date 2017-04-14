(ns pair-generator.core
  (:require [reagent.core :as reagent :refer [atom]]
            [clojure.string :as str]))

(enable-console-print!)

(println "This text is printed from src/pair-generator/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world"}))

(defn generate-pair [input]
  (let [input-data (shuffle (str/split input ","))
        per-group 2
        padding ""]
    (partition per-group per-group [padding] input-data)))

(defn root-element []
  [:div {:class "container"}
   [:h1 "Generate Pair"]
   [:p "Insert names (comma separated):"]
   [:div
    [:input {:type "text"}]
    [:button {:type "submit" :on-click generate-pair} "Generate"]]
   [:div {:class "result"}
    [:p "Generated Pairs:"]
    [:p "cat - dog"]]])

(reagent/render-component [root-element]
                          (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
