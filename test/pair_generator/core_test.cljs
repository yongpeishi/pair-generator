(ns pair-generator.core-test
  (:require [pair-generator.core :as sut]
            [cljs.test :refer [deftest is run-tests]]))

(deftest split-input-into-group-of-2
  (let [result (sut/generate-pair "a,b")]
    (is (= (count result) 1))
    (is (= (sort (first result)) (sort ["a" "b"])))))

(deftest pad-with-empty-string
  (let [result (sut/generate-pair "a,b,c")]
    (is (= (last (last result)) ""))))
