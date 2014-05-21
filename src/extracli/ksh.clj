(ns extracli.ksh
    (:require [clojure.string :refer [join]]
              [extracli.kb :as kb] ))


;;; ==== Cmd ====

(defn Cmd=>create-1 "
  :in-v   [?P [?A]]
  :type-v {?P :string, ?A :string}
  :out-1  ?C
  :post   {?C {:isa ::Cmd, :pgr ?P, :arg-v [?A]}}"

  [KB_pre ?P ?A*]
  (let [[KB1 ?C] (kb/add-concepts KB_pre 1)
        KB_post  (kb/add-properties KB_pre ?C {:isa ::Cmd, :pgr ?P, :arg-v ?A*}) ]
    [KB_post ?C] ))


(defn Cmd=>to-string-1   "
  :in-1  ?C
  :pre   {?C {:isa ::Cmd, :pgr ?P :arg-v ?A*}}
  :out-v []
  :post  {?C {:to-string ?S}}
  :with  {?S (join \\space (cons (str \"./\" ?P) ?A*))}"

  [KB_pre ?C]
  (let [C_pre   (get KB_pre ?C)
        ?P      (get C_pre :pgr)
        ?A*     (get C_pre :arg-v)
        ?S      (join \space (cons (str "./" ?P) ?A*))
        C_post  (assoc C_pre :to-string ?S)
        KB_post (assoc KB_pre ?C C_post) ]
    [KB_post] ))


; ==== RedirectCmd ====

(defn RedirectCmd=>create-1  "
  :in-v    [?C ?O]
  :pre     {?C {:isa ::Cmd}}
  :type-v  {?O :string}
  :out-v   [?R]
  :post    {?R {:isa ::RedirectCmd, :cmd ?C, :output-to ?O}}"

  [KB_pre ?C ?O]
  (let [[KB1 ?R]  (kb/add-concepts KB_pre 1)
        KB_post   (kb/add-properties KB1 ?R {:isa ::RedirectCmd, :cmd ?C, :output-to ?O}) ]
    [KB_post ?R] ))


(defn RedirectCmd=>to-string-1  "
  :in-v   [?R]
  :pre    {?R {:isa ::RedirectCmd, :cmd ?C, :output-to ?O}
           ?C {:isa ::Cmd, :to-string ?S}}
  :type-v {?R :concept, ?O :string}
  :out-v  []
  :post   {?R {:to-string ?S2}}
  :with   {?S2 (str ?S \" > \" ?O)}"

  [KB_pre ?R]
  (let [?C       (get-in KB_pre [?R :cmd])
        ?O       (get-in KB_pre [?R :output-to])
        ?S       (get-in KB_pre [?C :to-string])
        ?S2      (str ?S " > " ?O)
        KB_post  (kb/add-properties KB_pre ?R {:to-string ?S2}) ]
    [KB_post]
    ))


; ==== Script ====

(defn Script=>create-1 "
  :in-1  [?C]
  :pre   {?C {:isa ::Cmd}}
  :out-1 ?S
  :post  {?S {:isa ::Script, :cmd-v [?C]}}"

  [KB_pre ?C*]
  (let [[KB1 ?S] (kb/add-concepts KB_pre 1)
        KB_post  (kb/add-properties KB_pre ?S {:isa ::Script, :cmd-v ?C*}) ]
    [KB_post ?S] ))

(defn Script=>to-string-1 "
  :in-1  ?S
  :pre   {?S  {:isa ::Script, :cmd-v [?C]}
          ?C  {:to-string ?CS} }
  :out-v []
  :post: {?S  {:to-string-v ?SS}}"

  [KB_pre ?S]
  (let [?C*     (get-in KB_pre [?S :cmd-v])
        ?CS*    (for [?C ?C*] (get-in KB_pre [?C :to-string]))
        ?SS     (concat ["#!/bin/ksh", "# set -xv", ""] ?CS* ["", "exit 0;"])
        KB_post (kb/add-properties KB_pre ?S {:to-string-v ?SS}) ]
    [KB_post] ))


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
