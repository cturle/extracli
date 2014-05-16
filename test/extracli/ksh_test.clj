(ns extracli.ksh-test
  (:require [clojure.test :refer :all]
            [extracli.ksh :refer :all]))

(deftest a-test
  (let [KB {1 {:isa   :extracli.ksh/Cmd
               :pgr   "./tgetBO.ksh",
               :arg-v ["028" "$AAAA" "$MM" "ACA" "ECL-ACA-I" "$env"]
               :output-to :none}
            2 {:isa   :extracli.ksh/Script
               :cmd-v [3, 4]}
            3 {:isa  :extracli.ksh/Cmd
               :to-string "line 1"
               :genere 5}
            4 {:isa  :extracli.ksh/Cmd
               :to-string "line 2"
               :genere 6}
            5 {}
            6 {}
            7 {:isa :none}
            8 {:isa   :extracli.ksh/Cmd
               :pgr   "./tgetBO.ksh",
               :arg-v ["arg1" "arg2"]
               :output-to "output.txt"}
            }]
    (testing "Cmd=>Rule-1"
      (is (= "./tgetBO.ksh 028 $AAAA $MM ACA ECL-ACA-I $env"
             (get-in (Cmd=>Rule-1 KB 1) [1 :to-string]) ))
      (is (= "./tgetBO.ksh arg1 arg2 > output.txt"
             (get-in (Cmd=>Rule-1 KB 8) [8 :to-string]) ))
      )
    (testing "Cmd=>Rule-2"
      (is (= "toto.txt"
             (get-in (Cmd=>Rule-2 KB 3 "toto.txt") [3 :output-to]) ))
      )
    (testing "Script=>Rule-1"
      (is (= ["#!/bin/ksh", "# set -xv", "", "line 1", "line 2", "", "exit 0;"]
             (get-in (Script=>Rule-1 KB 2) [2 :to-string-v])))
      )
    (testing "Script=>Rule-2"
      (is (= [5 6]
             (get-in (Script=>Rule-2 KB 2) [2 :genere-v])))
      )
    (testing "Script=>Rule-3"
      (is (= {:isa :extracli.ksh/Script :cmd-v [3, 4]}
             (get (Script=>Rule-3 KB 7 [3, 4]) 7)))
      )
    ))





