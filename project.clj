(defproject postcode-spec "0.2.0"
  :description "Generating and validating UK postcodes"
  :url "https://github.com/codeasone/postcode-spec"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v20.html"}

  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/test.check "0.10.0"]]

  :deploy-repositories
  [["clojars"
    {:url "https://clojars.org/repo"
     :username :env/clojars_username
     :password :env/clojars_password
     :sign-releases false}]])
