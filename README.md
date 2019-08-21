# postcode-spec
[![Clojars Project](https://img.shields.io/clojars/v/postcode-spec.svg)](https://clojars.org/postcode-spec)

Validates and generates UK postcodes in accordance with https://en.wikipedia.org/wiki/Postcodes_in_the_United_Kingdom

## Usage

``` clojure
(require '[postcode-spec.core :as postcode])

(comment
  (s/conform ::postcode/united-kingdom-postcode "XM45HQ")
  ;; => :clojure.spec.alpha/invalid

  (s/conform ::postcode/united-kingdom-postcode "XM4 5HQ")
  ;; => "XM4 5HQ"

  (gen/sample (s/gen ::postcode/united-kingdom-postcode))
  ;; => ("A0A 1BB"
  ;;     "AB0A 0BA"
  ;;     "BA1B 1AB"
  ;;     "C0B 0DB"
  ;;     "BB2 1BB"
  ;;     "EA15 5BA"
  ;;     "N6D 1TG"
  ;;     "G5B 5IQ"
  ;;     "B1A 1DC"
  ;;     "IB6A 5AB")
  )
```

## Status

[![CircleCI](https://circleci.com/gh/codeasone/postcode-spec/tree/master.svg?style=svg)](https://circleci.com/gh/codeasone/postcode-spec/tree/master)

## License

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
