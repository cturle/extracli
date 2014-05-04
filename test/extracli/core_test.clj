(ns extracli.core-test
  (:require [clojure.test  :refer :all]
            [extracli.core :refer :all]))



(deftest a-test
  (let [KB {1 {:isa         :extracli.core/ExportBO
               :type-BO     "028"
               :client-name "ACA"
               :AAAA        "2013"
               :MM          "10"
               :type-flux   "I"
               :env         "rec"}
            2 {:isa :none}
            }]
    (testing "KshCmd=>Rule-1"
      (is (= {:isa :extracli.ksh/Cmd
              :genere 1
              :pgr "./tgetBO.ksh"
              :arg-v ["028" "2013" "10" "ACA" "ECL-ACA-I" "rec"]}
             (get (KshCmd=>Rule-1 KB 1 2) 2) ))
      )
    ))

