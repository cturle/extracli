(ns extracli.core-test
  (:require [clojure.test  :refer :all]
            [extracli.core :refer :all]
            [extracli.kb :as kb] ))



(deftest a-test
  (let [KB {1 {:isa         :extracli.core/ExportBO
               :type-BO     "028"
               :client-name "ACA"
               :AAAA        "2013"
               :MM          "10"
               :type-flux   "I"
               :env         "rec"}
            2 {:isa :none}
            3 {:isa Flux, :client "MAI", :type "C"}
            }]
    (testing "KshCmd=>Rule-1"
      (is (= {:isa :extracli.ksh/Cmd
              :genere 1
              :pgr "./tgetBO.ksh"
              :arg-v ["028" "2013" "10" "ACA" "ECL-ACA-I" "rec"]}
             (get (KshCmd=>Rule-1 KB 1 2) 2) ))
      )
    (testing "primitive-001"
      (let [[?CLI ?TFL ?AAAA ?MM ?ENV] ["MAI" "C" "2012" "05" "rec"]
            ?FL 3
            [KB1 ?CMD] (primitive-001 KB ?CLI ?TFL ?AAAA ?MM ?ENV)
            CMD (get KB1 ?CMD)
            ?EBO-v (get CMD :generate-v) ]
        (is (= :extracli.ksh/Cmd          (get CMD :isa)))
        (is (= "getBOsFlux.ksh"           (get CMD :pgr)))
        (is (= [?CLI ?TFL ?AAAA ?MM ?ENV] (get CMD :arg-v)))
        (is (kb/exist? KB1 {:isa Extraction, :flux ?FL, :AAAA ?AAAA :MM ?MM :env ?ENV, :export-BO-v ?EBO-v})) )
      )
    ))

