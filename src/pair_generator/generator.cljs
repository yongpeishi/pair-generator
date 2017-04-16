(ns pair-generator.generator
  (:require [clojure.string :as str]))

(defn generate-pair [input]
  (if (str/blank? input)
    []
    (let [input-data (->> (str/split input "\n")
                          (map str/trim)
                          distinct
                          (remove str/blank?)
                          shuffle)
          per-group  2
          padding    "self :("]
      (partition per-group per-group [padding] input-data))))

