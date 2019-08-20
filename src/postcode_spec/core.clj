(ns postcode-spec.core
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as gen]))

(s/def ::postcode-letter
  (s/with-gen char? (fn [] (gen/fmap (fn [char-code] (char char-code)) (s/gen (s/int-in 65 91))))))

(s/def ::postcode-digit (s/int-in 0 10))

(s/def ::outward-code-district-digit-letter
  (s/with-gen (s/and seq? #(re-matches #"[0-9][A-Z]" (apply str %)))
    (fn []
      (gen/fmap (fn [parts] (list (:postcode-digit parts) (:postcode-letter parts)))
                (s/gen (s/keys :req-un [::postcode-letter ::postcode-digit]))))))

(s/def ::outward-code-area (s/coll-of ::postcode-letter :min-count 1 :max-count 2))
(s/def ::outward-code-district
  (s/or :two-letters (s/coll-of ::postcode-digit :min-count 1 :max-count 2)
        :digit-then-letter ::outward-code-district-digit-letter))
(s/def ::postcode-outward-code (s/keys :req-un [::outward-code-area ::outward-code-district]))

(comment
  (gen/sample (s/gen ::postcode-outward-code) 1)
  ;; => ({:outward-code-area [\B], :outward-code-district [0]})
  )

(s/def ::inward-code-sector (s/int-in 0 9))
(s/def ::inward-code-unit (s/coll-of ::postcode-letter :min-count 2 :max-count 2))
(s/def ::postcode-inward-code (s/keys :req-un [::inward-code-sector ::inward-code-unit]))

(comment
  (gen/sample (s/gen ::postcode-inward-code) 1)
  ;; => ({:inward-code-sector 0, :inward-code-unit [\B \A]})
  )

(s/def ::united-kingdom-postcode
  (let [re #"[A-Za-z][A-Ha-hK-Yk-y]?[0-9][A-Za-z0-9]? [0-9][A-Za-z]{2}|[Gg][Ii][Rr] 0[Aa]{2}"]
    (s/with-gen
      (s/and string? #(re-matches re %))
      (fn
        []
        (gen/fmap
         (fn [parts]
           (let [oca-str (apply str (get-in parts [:postcode-outward-code :outward-code-area]))
                 ocd-str (apply str (get-in parts [:postcode-outward-code :outward-code-district]))
                 ics-str (str (get-in parts [:postcode-inward-code :inward-code-sector]))
                 icu-str (apply str (get-in parts [:postcode-inward-code :inward-code-unit]))]
             (format "%s%s %s%s" oca-str ocd-str ics-str icu-str)))
         (s/gen (s/keys :req-un [::postcode-outward-code ::postcode-inward-code])))))))

(comment
  (gen/sample (s/gen ::united-kingdom-postcode) 10)
  ;; => ("A0A 0BB"
  ;;     "B1B 1BB"
  ;;     "BA0 1BA"
  ;;     "BD0E 3AC"
  ;;     "A4B 1AA"
  ;;     "KB0 4AD"
  ;;     "BC8K 6FX"
  ;;     "BD7B 1DV"
  ;;     "O5I 0CT"
  ;;     "PC5 2BT")
  )
