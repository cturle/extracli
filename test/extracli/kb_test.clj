(ns extracli.kb-test
  (:require [clojure.test :refer :all]
            [extracli.kb :refer :all] ))

(deftest test-exist?
  (let [KB {1 {:isa :class, :pp1 1, :pp2 "pp2", :pp3 [1 2], :pp4 :other-property}}]
    (testing "true cases"
      (is (exist? KB {:isa :class, :pp1 1, :pp2 "pp2", :pp3 [1 2]}))
      )
    (testing "false cases"
      (is (not (exist? KB {:isa :clasx, :pp1 1, :pp2 "pp2", :pp3 [1 2]})))
      (is (not (exist? KB {:isa :class, :pp1 2, :pp2 "pp2", :pp3 [1 2]})))
      (is (not (exist? KB {:isa :class, :pp1 1, :pp2 "ppx", :pp3 [1 2]})))
      (is (not (exist? KB {:isa :class, :pp1 1, :pp2 "pp2", :pp3 [1 :x]})))
      (is (not (exist? KB {:isa :class, :pp2 "pp2", :pp3 [1 2]})))
      )
  ))

