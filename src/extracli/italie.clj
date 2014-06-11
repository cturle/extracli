(ns extracli.ksh
    (:require [extracli.kb :as kb]) )

(comment "connaissances sur les extractions Italie"
  {CL01 {:isa :extracli/Client, :pays "Italie", :trigramme "UNI"}
   C001 {:isa :pgr/Programme,
         :fr-def "C'est un programme présent sur le S5.
                  Il prend les arguments suivants :
                  - un trigramme de client Italie
                  - une année
                  - un mois
                  Il génère en sortie les extractions correspondantes.
                  sous qtcps5/ROOTS5/DFC/STATS
                  point d'entrée : CL"}
   CLASSIF-UGF {:isa set/Set :member-a #{"S", "F", "B", "N"}}
   })

(comment "règles"
  (defrule1
    :in   [?PE ?PP1]
    :pre  {?PE  {:isa :pgr/Execution, :pgr C001, :in-client ?CLI, :in-AA ?AA :in-MM ?MM, :status :ok}
           ?PP1 {:isa :set/Member-of, :set CLASSIF-UGF :element-value ?T} }
    :out  [?F]
    :post {?PE {:genere ?F}
           ?F  {:isa :file/File :path "qtcps5/ROOTS5/DFC/STATS" :filename ?FN}
           ?CC {:isa :Concatenate :in-v [?CLI ?T ?AA ?MM] :out ?FN} }
  ))


(comment "problème silvia D. du 30/05/2014"
  {EXE01 {:isa :pgr/Execution, :prg C001, :in-client "UNI", :in-AA "14" :in-MM "05"}
   PB01  {:isa :incoherence
          :fr-def "le fichier UNIS1405 n'est pas généré"
   }})

(comment "problème silvia D. du 03/06/2014"
  "le txt UNIN1405 généré par l'AS 400 est vide.

Les dossiers qui auraient du etre rémontés sont:

I4A234070 => formule MUOVERSI
I4A240992 => formule VIAGGI PROTETTO
I4A244759 => formule PRODOTTO ORO
I4A245169 => formule VIAGGI PROTETTO
I4A245725 => formule PRODOTTO ORO

Il s'agit de dossiers TOURISME (UNIN1405). Ces dossiers là, par contre, ont été rémontés dans le txt UNIS1405." )


(comment "problème silvia D. du 04/06/2014"
  "Les extractions pour les clients BNI, BSP ne sont pas générés sur l'environnement de recette.
   Cela fonctionnait il y a une heure pour d'autres clients (uni...)"
)

(comment "problème Elga S. du 04/06/2014"
  "Pour la période d'extraction le système recette ne change pas la date de Début extraction même si
    nous validons la periode saisie avec un O (oui) sur la zone Validation Extraction.
   Normalement dans le système S6, la date de Début Extraction se met à la date début M+1 lorsque
    nous validons la zone Validation Extraction avec un O (oui) pour la periode choisie.
   En recette la date de début reste figée au 01/01/2014 même si nous avons bien validé la periode par ex. 01/01/2014 - 31/01/2014.
   Nous avons donc lancé l'extraction du 01/01/2014 au 31/05/2014"
  {ECRAN-STATS-ITALIE2-APRES-SAISIE-CLIENT
   {:date-debut <f1?>, :date-fin <f2?>} })

(comment "module connaissance déclarative"
  "Dans le fichier AASRmmaa, on n'indique pas les n° de dossiers mais les n° de sinistre car type = 'REPA'"
  "Le flux n'est généré à partir de ISODOP (ouverture des dossiers) que si on n'est pas REPA")













