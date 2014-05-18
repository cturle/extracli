(ns extracli.core
    (:require [extracli.ksh   :refer :all]
              [clojure.string :refer [join]]) )



; (KshCmd=>Rule-1 {1 {:isa :ExportBO :env "rec" :type-BO "028" :client-name "ACA" :AAAA "2013" :MM "10" :type-flux "I"}, 2 {:isa :none}} 1 2)
(defn KshCmd=>Rule-1
  "  in: ?E
    pre: {?E {isa ExportBO, env _, type-BO _, client-name _, AAAA _, MM _, type-flux _}}
    out: ?C
   post: {?C {isa extracli.ksh/Cmd, genere ?E, ?C pgr _, arg-v _}}"

  [KB ?E ?C]
  (let [E (get KB ?E)]
    (assoc KB ?C {:isa     :extracli.ksh/Cmd
                  :pgr     "tgetBO.ksh"
                  :genere  ?E
                  :arg-v [(:type-BO E) (:AAAA E) (:MM E) (:client-name E) (str "ECL-" (:client-name E) "-" (:type-flux E)) (:env E)]} )))


(defn primitive-001
  ":in-v  [?CLI ?TFL ?AAAA ?MM ?ENV]
   :pre   {?FL {:isa Flux, :client ?CLI, :type ?TFL} }
   :out-1 ?CMD
   :post  {?CMD {:isa Cmd, :generate-v ?EBO-v :pgr \"getBOsFlux.ksh\" :arg-v [?CLI ?TFL ?AAAA ?MM ?ENV]}
           ?E   {:isa Extraction, :flux ?FL, :AAAA ?AAAA :MM ?MM :env ?ENV, :export-BO-v ?EBO-v} }"

  [KB ?CLI ?TFL ?AAAA ?MM ?ENV]
  (let [[KB1 ?CMD ?EBO-v ?E] (kb/add-concepts KB 3)
        ?FL (kb/find KB1 {:isa ::Flux, :client ?CLI, :type ?TFL})
        CMD {:isa :extracli.ksh/Cmd, :generate-v ?EBO-v :pgr "getBOsFlux.ksh" :arg-v [?CLI ?TFL ?AAAA ?MM ?ENV]}
        E   {:isa ::Extraction, :flux ?FL, :AAAA ?AAAA :MM ?MM :env ?ENV, :export-BO-v ?EBO-v}
        KB2 (assoc KB1 ?CMD CMD, ?E E) ]
    [KB2 ?CMD] )
  )


(def KshScript=>Rule-1A
  "  in: [?E]
    pre: {?E {isa ExportBO, env _, type-BO _, client-name _, AAAA _, MM _, type-flux _}}
    out: [?C]
   post: {?C {isa extracli.ksh/Cmd, genere ?E, ?C pgr _, arg-v _}}"

  (Mapping=>Rule2 KB+ :KshCmd=>Rule-1 :KshScript=>Rule-1A)
)





(comment

  (KshCmd=>Rule-1 KB ?E
"in : ?E
 pre: (?E isa ExportBO), (?E env _), (?E type-BO _), (?E client-name _), (?E AAAA _), (?E MM _), (?E type-flux _)
 out: ?C
post: (?C isa :extracli.ksh/Cmd), (?C genere ?E), (?C pgr _), (?C arg-v _)")

  )


(defn KshScript=>Rule-1B

"in : [?C], ?M
 pre: (?C :isa Cmd), (?C pgr _), (?C arg-v _)
      (?M :isa Mapping), (?M :in [?C]), (?M :kb-relation :genere), (?M :out [?E])
 out: ?S
post: (?S :isa Script), (?S :genere-v [?E]), (?S to-string-v _)"

  [KB ?C* ?M ?S]

(comment

  (Cmd=>Rule-1 KB ?C
"args: ?C
 pre: (?C isa Cmd), (?C pgr _), (?C arg-v _)
post: (?C to-string _)")

  (Script=>Rule-3 KB ?S ?C*
"args: ?S [?C]
 pre: (?C isa Cmd), (?S isa :none)
post: (?S isa Script), (?S cmd-v [?C])")

  (Script=>Rule-1 KB ?S
"args: ?S
 pre: (?S isa Script), (?S cmd-v [?C]), (?C to-string _)
post: (?S to-string-v _)")

(Script=>Rule-2 KB ?S
"in : ?S
 pre: (?S isa Script), (?S cmd-v [?C]), (?C genere _)
 out: ?M
post: (?M :isa Mapping), (?M :input [?C]), (?M :kb-relation :genere), (?M :output [?G])
      (?S genere-v [?G])")

  )
  )

(defn KshScript=>Rule-1
  "  in: [?E]
    pre: (?E isa ExportBO), (?E env _), (?E type-BO _), (?E client-name _), (?E AAAA _), (?E MM _), (?E type-flux _)
    out: ?S
   post: (?S isa :extracli.ksh/Script), (?S genere-v [?E]), (?S to-string-v _)"
  [KB ?E* ?S]

  (let [?C* (gensym)
        ?M  (gensym)
        KB-A (KshScript=>Rule-1A KB  [?E* ?C* ?M])
        ?S  (gensym)
        KB-B (KshScript=>Rule-1B KB-A [?C* ?M ?S])]
    KB-B)
)

;;; DonneesFacturationSAP

(defn DonneesFacturationSAP=>Rule-1
  "  in: [?E]
    pre: {?E  {isa Execution, executable VF06, AAAA ?AAAA, MM ?MM}}
    out: [?DF]
   post: {?E  {genere ?DF}
          ?DF {isa DonneesFacturationSAP, AAAA ?AAAA, MM ?MM}}"

  [KB, ?E ?DF]
  ; todo easy
  )


;;; DocInstanceBO

(defn DocInstanceBO=>Rule-1
  "  in: [?DF, ?DI]
    pre: {?DF {isa DonneesFacturationSAP, AAAA ?AAAA, MM ?MM, date-dispo ?D}
         {?DI {isa DocInstanceBO, type-BO _, AAAA ?AAAA, MM ?MM}}
    out: []
   post: {?DI {date-dispo ?D}}"

  [KB ?DF ?DI]
  ; todo easy
  )









