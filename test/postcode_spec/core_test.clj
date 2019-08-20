(ns postcode-spec.core-test
  (:require [clojure.spec.alpha :as s]
            [clojure.test :as t]
            [postcode-spec.core :as sut]))

(t/deftest united-kingdom-postcode-test
  (t/testing "conforming"
    (t/testing "with all letters capitalised"
      (t/is (= "XM4 5HQ" (s/conform ::sut/united-kingdom-postcode "XM4 5HQ"))))

    (t/testing "with some letters lower-cased"
      (t/is (= "ab1 2AB" (s/conform ::sut/united-kingdom-postcode "ab1 2AB"))))

    (t/testing "when space is missing between outward and inward codes"
      (t/is (s/invalid? (s/conform ::sut/united-kingdom-postcode "XM45HQ"))))

    (t/testing "when invalid"
      (t/is (s/invalid? (s/conform ::sut/united-kingdom-postcode "foobar"))))

    (t/testing "the Giro Bank postcode"
      (t/is (= "GIR 0AA" (s/conform ::sut/united-kingdom-postcode "GIR 0AA"))))))
