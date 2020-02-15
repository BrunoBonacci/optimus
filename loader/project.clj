;; Copyright (c) Trainline Limited, 2017. All rights reserved.
;; See LICENSE.txt in the project root for license information
(defproject com.trainline.optimus/loader (-> "../ver/optimus.version" slurp .trim)
  :description "Spark based component that parallely loads key value
  pairs to the Optimus API"
  :url "https://github.com/BrunoBonacci/optimus"
  :license {:name "All rights reserved"}

  :main optimus.loader.main

  :dependencies [[org.clojure/clojure "1.10.1"]

                 [clj-http "3.10.0"]
                 ;; spark
                 [gorillalabs/sparkling "2.1.3" ]

                 ;; logging
                 [org.clojure/tools.logging "0.6.0"]
                 [org.slf4j/slf4j-log4j12 "1.7.30"]
                 [org.slf4j/jul-to-slf4j "1.7.30"]
                 [org.slf4j/jcl-over-slf4j "1.7.30"]

                 [com.brunobonacci/safely "0.5.0-alpha8"]
                 [org.clojure/tools.cli "0.4.2"]

                 [org.apache.spark/spark-core_2.10 "2.2.3"
                  :exclusions [commons-net org.slf4j/slf4j-log4j12]]
                 [org.apache.spark/spark-sql_2.10 "2.2.3"]

                 [com.cognitect/transit-clj "0.8.319"]]

  :profiles {:dev {:plugins [[lein-midje "3.2.2"]
                             [lein-binplus "0.6.6"]]
                   :dependencies [[midje "1.9.9"]]}}

  :global-vars {*warn-on-reflection* true}

  :resource-paths ["resources" "../ver" ]

  :bin {:name "loader"
        :skip-realign true
        :jvm-opts ["-server" "-Dfile.encoding=utf-8" "$JVM_OPTS" ]}

  :uberjar-merge-with {#"reference.conf$" [slurp str spit]}

  :uberjar-exclusions [#"META-INF/LICENSE/*" #"META-INF/license/*"]

  ;;;:aliases {"itest" ["midje" ":config" ".midje_int.clj"]}

  :aot [#".*" sparkling.serialization sparkling.destructuring]

)
