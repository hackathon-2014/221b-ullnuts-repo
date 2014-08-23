(defproject pitch "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  
  :url "http://example.com/FIXME"
  
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.immutant/immutant "1.1.4"]
                 [mysql/mysql-connector-java "5.1.32"]
                 [hiccup "1.0.5"]
                 [compojure "1.1.8"]
                 [com.twilio.sdk/twilio-java-sdk "3.4.4"]]
  :plugins [[lein-ring "0.8.10"]]
  :ring {:handler pitch.handlers/app}
  :immutant {:context-path "/"})
