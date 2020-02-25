;; Copyright (c) Trainline Limited, 2017. All rights reserved.
;; See LICENSE.txt in the project root for license information
(defproject com.trainline/optimus (-> "../ver/optimus.version" slurp .trim)
  :description "An API for storing and loading data with transactional semantics"
  :url "https://github.com/BrunoBonacci/optimus"
  :license {:name "All rights reserved"}

  :dependencies [[org.clojure/clojure "1.10.1"]

                 [metosin/compojure-api "1.1.13"]
                 [aleph "0.4.6"]

                 ;; aws sdk
                 [amazonica "0.3.152"
                  :exclusions [com.amazonaws/aws-java-sdk
                               com.amazonaws/amazon-kinesis-client]]
                 [com.amazonaws/aws-java-sdk-core "1.11.722"
                  :exclusions [commons-logging]]
                 [com.amazonaws/aws-java-sdk-dynamodb "1.11.722"
                  :exclusions [commons-logging]]

                 ;; logging
                 [org.clojure/tools.logging "0.6.0"]
                 [org.slf4j/slf4j-log4j12 "1.7.30"]
                 [org.slf4j/jul-to-slf4j "1.7.30"]
                 [org.slf4j/jcl-over-slf4j "1.7.30"]

                 ;; other libraries
                 [prismatic/schema "1.1.12"]
                 [com.brunobonacci/where "0.5.5"]
                 [samsara/trackit-core "0.9.3"]
                 [com.brunobonacci/safely "0.5.0-alpha8"
                  :exclusions [samsara/trackit-core]]
                 [cheshire "5.10.0"]
                 [amalloy/ring-gzip-middleware "0.1.4"]
                 [com.taoensso/nippy "2.14.0"]
                 [com.brunobonacci/oneconfig "0.15.0"]]

  :profiles {:uberjar {:aot :all}
             :dev {:plugins [[lein-midje "3.2.2"]
                             [lein-binplus "0.6.6"]]
                   :source-paths ["dev" "src"]
                   :dependencies [[midje "1.9.9"]]}}

  :bin {:name "optimus"
        :skip-realign true
        :jvm-opts ["-server" "-Dfile.encoding=utf-8" "$JVM_OPTS" ]}

  :global-vars {*warn-on-reflection* true}

  :resource-paths ["resources" "../ver" ]

  :aliases {"itest" ["midje" ":config" ".midje_int.clj"]}

  :main optimus.service.main

)
