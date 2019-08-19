# postcode-spec

Validates and generates UK postcodes in accordance with https://en.wikipedia.org/wiki/Postcodes_in_the_United_Kingdom

## Usage

``` clojure
(require '[postcode-spec.core :as postcode])

(s/def ::customer-postcode ::postcode/united-kingdom-postcode)

(comment
  (gen/sample (s/gen ::customer-postcode))
  )
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
```

## License

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
