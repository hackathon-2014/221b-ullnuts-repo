(defproject pitch "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  
  :url "http://example.com/FIXME"
  
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.immutant/immutant "1.1.4"]
                 [mysql/mysql-connector-java "5.1.32"]]
  :immutant {:context-path "/"})
