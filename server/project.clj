(defproject pitch "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  
  :url "http://example.com/FIXME"
  
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/java.jdbc "0.3.5"]
                 [org.apache.httpcomponents/httpclient "4.2.2"]
                 [org.immutant/immutant-jobs "1.1.4"]
                 [org.immutant/immutant-messaging "1.1.4"]
                 [org.immutant/immutant-web "1.1.4"]
                 [mysql/mysql-connector-java "5.1.32"]
                 [twilio-api "0.1.0-SNAPSHOT"]
                 [org.clojure/core.async "0.1.338.0-5c5012-alpha"]
                 [hiccup "1.0.5"]
                 [compojure "1.1.8"]
                 [com.twilio.sdk/twilio-java-sdk "3.4.4"]
                 [org.clojure/data.json "0.2.5"]]

  :immutant {:context-path "/"})
