(ns extracli.ksh-test
  (:require [clojure.test :refer :all]
            [extracli.ksh :refer :all]
            [extracli.kb :as kb] ))

(def KB0 (kb/new-kb
          {1 {:isa :extracli.ksh/Cmd, :pgr "tgetBO.ksh", :arg-v ["028" "$AAAA" "$MM" "ACA" "ECL-ACA-I" "$env"]}
           2 {:isa :extracli.ksh/Script, :cmd-v [3, 4]}
           3 {:isa :extracli.ksh/Cmd, :to-string "line 1", :genere 5}
           4 {:isa :extracli.ksh/Cmd, :to-string "line 2", :genere 6}
           5 {}
           6 {}
           7 {:comment "not used"}
           8 {:isa :extracli.ksh/Cmd, :pgr "tgetBO.ksh", :arg-v ["arg1" "arg2"], :output-to "output.txt"}
           9 {:isa :extracli.ksh/RedirectCmd, :cmd 3, :output-to "log.txt"}
           }))


;;; ==== tests Cmd ====
(deftest test-Cmd=>create-1
  (testing "nominal case"
    (let [KB_pre   KB0
          ?P       "tgetBO.ksh"
          ?A*      ["028" "$AAAA" "$MM" "ACA" "ECL-ACA-I" "$env"]
          [KB_post ?C] (Cmd=>create-1 KB_pre ?P ?A*) ]
      (is (= :extracli.ksh/Cmd (get-in KB_post [?C :isa])))
      (is (= ?P                (get-in KB_post [?C :pgr])))
      (is (= ?A*               (get-in KB_post [?C :arg-v])))
      )))

(deftest test-Cmd=>to-string-1
  (let [KB KB0]
    (testing "nominal case"
      (let [[KB1] (Cmd=>to-string-1 KB 1)]
          (is (= "./tgetBO.ksh 028 $AAAA $MM ACA ECL-ACA-I $env"
                 (get-in KB1 [1 :to-string]) )))
      )))


;;; ==== tests RedirectCmd ====
(deftest test-RedirectCmd=>create-1
  (testing "nominal case"
    (let [KB_pre   KB0
          ?C       1
          ?O       "log.txt"
          [KB_post ?R] (RedirectCmd=>create-1 KB_pre ?C ?O) ]
      (is (= :extracli.ksh/RedirectCmd  (get-in KB_post [?R :isa])))
      (is (= ?C                         (get-in KB_post [?R :cmd])))
      (is (= ?O                         (get-in KB_post [?R :output-to])))
      )))

(deftest test-RedirectCmd=>to-string-1
  (testing "nominal case"
    (let [KB_pre   KB0
          ?R       9
          ?S2      "line 1 > log.txt"
          [KB_post] (RedirectCmd=>to-string-1 KB_pre ?R) ]
      (is (= ?S2 (get-in KB_post [?R :to-string])))
      )))


;;; ==== tests Script ====
(deftest test-Script=>create-1
  (testing "nominal case"
    (let [KB_pre   KB0
          ?C*      [3 4]
          [KB_post ?S] (Script=>create-1 KB_pre ?C*) ]
      (is (= :extracli.ksh/Script (get-in KB_post [?S :isa])))
      (is (= ?C*                  (get-in KB_post [?S :cmd-v])))
      )))

(deftest test-Script=>to-string-1
  (testing "nominal case"
    (let [KB_pre    KB0
          ?S        2
          [KB_post] (Script=>to-string-1 KB_pre ?S)
          ?SS       ["#!/bin/ksh", "# set -xv", "", "line 1", "line 2", "", "exit 0;"] ]
      (is (= ?SS  (get-in KB_post [?S :to-string-v])))
      )))



;;; ===== TODO CLEAN =====
(deftest others-test
  (let [KB KB0]
    (testing "Script=>Rule-2"
      (is (= [5 6]
             (get-in (Script=>Rule-2 KB 2) [2 :genere-v])))
      )
    ))





