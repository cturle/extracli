(ns extracli.ksh
    (:require [extracli.kb :as kb]) )


; SQL permet la gestion et l'interrogation des bases de données.
; Une base de données peut être considérée comme une table à deux dimensions, dont les colonnes sont les champs et les lignes
; sont les tuples.

; == BUT ==
; la representation-textuelle de la sql/command qui permettra de Update le contenu de la sql/TableCell C001 avec la valeur "_VIDE"


; données encodées d'une certaine manière
; le but cherche ces données mais indexées différement
; Comment faire le lien

; == données recherchées ==
; donne-moi la syntaxe de la commande SQL permettant, pour la table "t1", de faire la mise-à-jour du champ "c1" de la ligne qui a pour f2 "v2" et f3 "v3", avec la nouvelle valeur "_VIDE".
;
; donne-moi
; => {?TSK {:isa ::ComputeTask}}
; ... la syntaxe de la commande SQL
; => {?X   {:isa :String}
;     ?CMD {:isa ::SQLCmd, :syntax ?X}
;     ?TSK {:what ?X} }
; ... permettant, pour la table "t1"
; => {?T   {:isa ::SQLTable, :name "t1"}
;     ?CMD {:table ?T} }
; ... de faire la mise-à-jour
; => {?U   {:isa :update}
;     ?CMD {:effect ?U} }
; ... du champ "c1"
; => {?C   {:isa ::SQLCell, :field ?F1, :table ?T}
;     ?F1  {:isa ::SQLField, :name "c1"}
;     ?U   {:transient ?C} }
; ... de la ligne qui a pour f2 "v2" et f3 "v3",
; => {?R   {:isa ::SQLRow, :table ?T}
;     ?C   {:row ?R} }
; ... avec la nouvelle valeur "_VIDE"
; => {?U   {:new-value "_VIDE"}}


; données déclaratives mémorisées
; ce que je sais, c'est qu'il existe une commande SQL qui permet de mettre à jour la valeur d'un champ d'une ligne d'une table.
; =>


; prérequis 1 : connaissances déclaratives en mémoire
; prérequis 2 : tâche à réaliser (ecrire "Update ...")

; etapes du comportement souhaité

;

;

