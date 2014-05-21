(ns extracli.kb-test
  (:require [clojure.test :refer :all]
            [extracli.kb :refer :all] ))

(def KB0 (new-kb {1 {:isa :class, :pp1 1, :pp2 "pp2", :pp3 [1 2], :pp4 :other-property}}))

(deftest test-exist?
  (let [KB KB0]
    (testing "true cases"
      (is (exist? KB {:isa :class, :pp1 1, :pp2 "pp2", :pp3 [1 2]}))
      )
    (testing "false cases"
      (is (not (exist? KB {:isa :clasx, :pp1 1, :pp2 "pp2", :pp3 [1 2]})))
      (is (not (exist? KB {:isa :class, :pp1 2, :pp2 "pp2", :pp3 [1 2]})))
      (is (not (exist? KB {:isa :class, :pp1 1, :pp2 "ppx", :pp3 [1 2]})))
      (is (not (exist? KB {:isa :class, :pp1 1, :pp2 "pp2", :pp3 [1 :x]})))
      )
  ))


(deftest test-add-concepts
  (let [KB KB0]
    (testing "cas nominal"
      (let [NB          3
            [KB2 & IDs] (add-concepts KB NB)]
        (is (= NB (count IDs)))
        (is (every? #(= {} (get KB2 %)) IDs))
        ))))


(deftest test-add-properties
  (let [C1_pre  {:pp0 0}
        C2_pre  {:pp2 0}
        KB      (new-kb {1 C1_pre, 2 C2_pre})]
    (testing "cas nominal"
      (let [PPs1     {:pp1 1 :pp2 2}
            PPs2     {:pp2 2 :pp3 3}
            KB2      (add-properties KB, 1 PPs1, 2 PPs2)
            C1_post  (get KB2 1)
            C2_post  (get KB2 2)]
        (is (= {:pp0 0 :pp1 1 :pp2 2} C1_post))
        (is (= {:pp2 2 :pp3 3} C2_post))
        ))))








