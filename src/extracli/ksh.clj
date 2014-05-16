(ns extracli.ksh
    (:require [clojure.string :refer [join]]))



; (Cmd=>Rule-1 {1 {:pgr "./cmd.ksh" :arg-v ["arg1" "arg2"]}} 1)
(defn Cmd=>Rule-1
"args: ?C
 pre: (?C isa Cmd), (?C pgr _), (?C arg-v _)
post: (?C to-string _)"
  [KB ?C]
  (let [C (get KB ?C)]
    (assoc KB ?C (assoc C :to-string (join \space `[~(:pgr C) ~@(:arg-v C)]))) ))


; (Cmd=>Rule-2 {1 {:isa :Cmd}} 1 "toto.txt")
(defn Cmd=>Rule-2
  "
  :in-v  [?CMD ?O]
  :pre   {?CMD {:isa Cmd, :output-to ?}
  :out-v []
  :post  {?CMD {:output-to ?O}}"

  [KB ?CMD ?O]
  (let [CMD  (get KB ?CMD)]
    (assoc KB ?CMD (assoc CMD :output-to ?O)) )
  )


; (Script=>Rule-1 {1 {:cmd-v [2, 3]}, 2 {:to-string "line 1"}, 3 {:to-string "line 2"}} 1)
(defn Script=>Rule-1
"args: ?S
 pre: (?S isa Script), (?S cmd-v [?C]), (?C to-string _)
post: (?S to-string-v _)"
  [KB ?S]
  (let [S  (get KB ?S)
        C* (for [?C (:cmd-v S)] (get KB ?C)) ]
    (assoc KB ?S (assoc S :to-string-v `["#!/bin/ksh", "# set -xv", "", ~@(for [C C*] (:to-string C)), "", "exit 0;"])) ))



; (Script=>Rule-2 {1 {:cmd-v [2 3]}, 2 {:genere 4}, 3 {:genere 5}} 1)
(defn Script=>Rule-2
"args: ?S
 pre: (?S isa Script), (?S cmd-v [?C]), (?C genere ?G)
post: (?S genere-v [?G])"
  [KB ?S]
  (let [S   (get KB ?S)
        ?C* (:cmd-v S)
        C*  (for [?C ?C*] (get KB ?C))
        ?G* (for [C C*] (:genere C))]
    (assoc KB ?S (assoc S :genere-v ?G*)) ))



; (Script=>Rule-3 {1 {:isa ::Cmd}, 2 {:isa ::Cmd}, 3 {:isa :none}} 3 [1 2])
(defn Script=>Rule-3
"args: ?S [?C]
 pre: (?C isa Cmd), (?S isa :none)
post: (?S isa Script), (?S cmd-v [?C])"
  [KB ?S ?C*]
  (assoc KB ?S {:isa ::Script, :cmd-v ?C*}) )



(comment
(def *indent* ^dynamic (apply str (repeat 8 " ")))

; (out-ksh-case-value {:value "val1", :ksh-lines ["line 1", "line 2"]})
(defn out-ksh-case-value
  "ksh-case-value : {:value string, :ksh-lines string*}"
  [ksh-case-value]
  (concat [(str (:value ksh-case-value) ")   " (first (:ksh-lines ksh-case-value)))]
          (map #(str *indent* %1) (rest (:ksh-lines ksh-case-value)))
          [";;", ""] ))

; (out-ksh-case {:key "$jour", :cases [{:value "lundi", :ksh-lines ["l1", "l2"]}], :error-msg "erreur !"})
(defn out-ksh-case
  "ksh-case : {:key string, :cases {:value string, :ksh-lines string*}*, :error-msg string}"
  [ksh-case]
  (map #(str *indent* %1)
       (concat [(interpose " " ["case" (:key ksh-case) "in"])]
               (mapcat out-ksh-case-value (:cases ksh-case))
               [(str "*)      echo \"EXIT=ERREUR:" (:error-msg ksh-case) \")
                "        exit 1;"
                "        ;;"
                "esac", "" ])))
  )
