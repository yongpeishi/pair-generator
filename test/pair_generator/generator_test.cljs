(ns pair-generator.generator-test
  (:require [pair-generator.generator :as sut]
            [cljs.test :refer [deftest is run-tests]]))

(deftest split-input-into-group-of-2
  (let [result (sut/generate-pair "a\nb")]
    (is (= (count result) 1))
    (is (= (sort (first result)) (sort ["a" "b"])))))

(deftest pad-with-some-string
  (let [result (sut/generate-pair "a\nb\nc")]
    (is (= (last (last result)) "no one :("))))

(deftest remove-repeated-name
  (let [result (sut/generate-pair "a\na\na\nb")]
    (is (= (count result) 1))
    (is (= (sort (first result)) (sort ["a" "b"])))))

(deftest strip-whitespace-around-name
  (let [result (sut/generate-pair " a \n b ")]
    (is (= (count result) 1))
    (is (= (sort (first result)) (sort ["a" "b"])))))

(deftest return-empty-array-when-given-empty-string
  (let [result (sut/generate-pair "  ")]
    (is (= result []))))

(deftest ignore-multiple-newline-in-a-row
  (let [result (sut/generate-pair "a\n\n\nb")]
    (is (= (count result) 1))
    (is (= (sort (first result)) (sort ["a" "b"])))))
