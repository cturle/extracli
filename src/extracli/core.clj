(ns extracli.core
    (:require [extracli.ksh   :refer :all]
              [clojure.string :refer [join]]) )



; (KshCmd=>Rule-1 {1 {:isa :ExportBO :env "rec" :type-BO "028" :client-name "ACA" :AAAA "2013" :MM "10" :type-flux "I"}, 2 {:isa :none}} 1 2)
(defn KshCmd=>Rule-1
"args: ?E, ?C
 pre: (?E isa ExportBO), (?E env _), (?E type-BO _), (?E client-name _), (?E AAAA _), (?E MM _), (?E type-flux _)
      (?C isa :none)
post: (?C isa :extracli.ksh/Cmd), (?C genere ?E), (?C pgr _), (?C arg-v _)"
  [KB ?E ?C]
  (let [E (get KB ?E)]
    (assoc KB ?C {:isa     :extracli.ksh/Cmd
                  :pgr     "./tgetBO.ksh"
                  :genere  ?E
                  :arg-v [(:type-BO E) (:AAAA E) (:MM E) (:client-name E) (str "ECL-" (:client-name E) "-" (:type-flux E)) (:env E)]} )))


(comment
(defn KshScript=>Rule-1
"args: [?E], ?S
 pre: (?E isa ExportBO), (?E env _), (?E type-BO _), (?E client-name _), (?E AAAA _), (?E MM _), (?E type-flux _)
      (?S isa :none)
post: (?S isa :extracli.ksh/Script), (?S genere-v [?E]), (?S to-string-v _)"
  [KB ?E* ?S])

;pour tout E:

(KshCmd=>Rule-1 KB ?E
"args: ?E, ?C
 pre: (?E isa ExportBO), (?E env _), (?E type-BO _), (?E client-name _), (?E AAAA _), (?E MM _), (?E type-flux _)
      (?C isa :none)
post: (?C isa :extracli.ksh/Cmd), (?C genere ?E), (?C pgr _), (?C arg-v _)")

;pour tout C:

(Cmd=>Rule-1 KB ?C
"args: ?C
 pre: (?C isa Cmd), (?C pgr _), (?C arg-v _)
post: (?C to-string _)")

(Script=>Rule-3 KB ?S ?C*
"args: ?S [?C]
 pre: (?C isa Cmd), (?S isa :none)
post: (?S isa Script), (?S cmd-v [?C])")

(Script=>Rule-2 KB ?S
"args: ?S
 pre: (?S isa Script), (?S cmd-v [?C]), (?C genere ?G)
post: (?S genere-v [?G])")

(Script=>Rule-1 KB ?S
"args: ?S
 pre: (?S isa Script), (?S cmd-v [?C]), (?C to-string _)
post: (?S to-string-v _)"

  )
)


;;; app-generators
(comment
(defn out-getBO [flux]
  (let [CLI          (flux=>client flux)
        FLUX         (str (join "-" ["ECL" CLI (flux=>type flux)])) ]
    (map #(join " " ["./tgetBO.ksh" %1 "$AAAA" "$MM" CLI FLUX "$env"])
         (flux=>typesBO flux) )))

(defn out-getBOsRe7case [jour liste-flux]
  (concat [(str indent jour ")" "  " (out-getBO (first liste-flux)))]
          (map #(str indent indent %1)
               (mapcat out-getBO (rest liste-flux)) )
          [(str indent indent ";;"), ""] ))


(defn out-getBOsRe7 []
  (concat ["#!/bin/ksh", "# set -xv", "",
           "jour=$1"
           "env=$2",
           "AAAAMM=$(date +%Y%m -d \"$(date +%Y%m)03 1 month ago\")"
           "AAAA=${AAAAMM:0:4}"
           "MM=${AAAAMM:4:2}", ""]
          ["case $jour in", ""]
          (out-getBOsRe7case "lundi" re7-lundi)
          ["        *)      echo \"EXIT=ERREUR: jour de re7 inconnu\""
           "                exit 1;"
           "                ;;"
           "esac", ""]
          ["exit 0;"] ))
)








