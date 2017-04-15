(ns pair-generator.core-test
  (:require [pair-generator.core :as sut]
            [cljs.test :refer [deftest is run-tests]]))

(deftest split-input-into-group-of-2
  (let [result (sut/generate-pair "a,b")]
    (is (= (count result) 1))
    (is (= (sort (first result)) (sort ["a" "b"])))))

(deftest pad-with-some-string
  (let [result (sut/generate-pair "a,b,c")]
    (is (= (last (last result)) "self :("))))

(deftest remove-repeated-name
  (let [result (sut/generate-pair "a,a,a,b")]
    (is (= (count result) 1))
    (is (= (sort (first result)) (sort ["a" "b"])))))

(deftest strip-whitespace-around-name
  (let [result (sut/generate-pair " a , b ")]
    (is (= (count result) 1))
    (is (= (sort (first result)) (sort ["a" "b"])))))

(deftest return-empty-array-when-given-empty-string
  (let [result (sut/generate-pair "  ")]
    (is (= result []))))
