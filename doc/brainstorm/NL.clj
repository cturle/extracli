(create-domain "NL")
(add-assertion "Les français croient que le vin est bon pour la santé")
(add-assertion "Les quadra ne croient pas que le vin est bon pour la santé")
(add-assertion "Thierry est français et quadra")
(ask-question  "Que pense Thierry sur l'effet du vin sur la santé ?")

; ==========================================================================

; retour user ?

; ==========================================================================

; question :
"Peux-tu m'indiquer pour le mois d'août les présences et les périmètres en terme de flux des acteurs côté BI"

; on s'attend en retour à un ensemble de K (?Ks), tel que ?Ks détermine la fonction ci-dessous :
:in-a  #{?J, ?F}
:pre   {?J {:isa Jour :member "aout-2014"}
        ?F {:isa Flux} }
:out   ?A
:post  {?A {:isa Acteur-BI :est-present ?J, :perimetre ?F}}

; ==========================================================================
; comprehension - cas 3

; hypothese en mémoire
; se comprend en "si (?L isa List) alors proposer un elaborate-operator (?L isa Collection) avec preference acceptable"

;{:pre  {?C {:isa Collection :of ?E :member ?X}}
; :post {?X {:isa ?E}} }

{:pre  {?DefR {:isa DefRange :dom ?D :rel ?REL :range ?R}
        ?X    {:isa ?D, ?REL ?Y} }
 :post {?Y    {:isa ?R}} }

{:pre  {?DefR {:isa DefRange= :dom ?D :rel ?REL :range= ?Y}
        ?X    {:isa ?D} }
 :post {?X    {?REL ?Y}} }

{:pre  {?S {:genl ?G}
        ?X {:isa ?S} }
 :post {?X {:isa ?G}} }

{List {:genl Collection}
 Set  {:genl Collection} }

"soit L, une liste de nombre"
{?L  {:isa ?CL}
 ?CL {:genl List}
 ?D1 {:isa DefRange, :dom ?CL :rel member :range Number} }
; == en utilisant la definition de of ==
;{?L  {:isa List :of Number}}


"soit E, un ensemble de liste de nombre"
{?E  {:isa ?CS}
 ?CS {:genl Set}
 ?D2 {:isa DefRange, :dom ?CS :rel member :range ?CL}
 ?CL {:genl List}
 ?D1 {:isa DefRange, :dom ?CL :rel member :range Number} }
; == en utilisant la definition de of ==
;{?E  {:isa Set :of ?CL}
; ?CL {:genl List}
; ?D1 {:isa DefRange, :dom ?CL :rel member :range Number} }

; ==== definir le vocabulaire ====
; différencier le niveau représentation du niveau langage
; # pré-requis :
; ## il y a une classe de nom "BO"
(C01 :isa Class :name "BO")
; ## il y a un élément de cette classe qui s'appelle "BO015"
(C02 :isa C01 :name "BO015")

; # Questions n-3
; est-ce que C01 est un BO ?
; ## langage L01
; (est-ce-que C01 est-un "BO")
; -> phase de compréhension
; A1 = (concept C01)    => C01,    throw ConceptNotFoundException ou throw ConceptMultiFoundException
; --> C01 compris comme le concept C01
; A2 = (concept est-un) => est-un
; --> est-un compris comme le concept est-un
; A3 = (concept "BO")   => C01
; --> "BO" compris comme le concept C01
; --> (est-ce-que C01 est-un "BO") compris comme (_est-ce-que C01 est-un C01)
; - creer Tache
{C03 :isa Fact :x C01 :r est-un :y C01}
{C04 :isa Task :activity fill-in :facet truth-value :concept C03}
; - et la lancer (pas de gestion asynchrone pour l'instant, ni ne multi-taches)
; - phase elaboration en vue de classifier la Tache
; => rien de plus
; - phase proposition en vue de resolution de la tâche
; => réussir à lier cet objectif avec la tâche ci dessous (lie la notion de Fait à la notion de Représentation).
{C05 :isa Data :x C01 :r est-un :y C01}
{C06 :isa Task :activity fill-in :facet data-exist :concept C03}

{H001 :isa heuristic :if-isa= Task :if-activity= fill-in :if-facet= data-exist :if-concept-defined :then-clj (fn [A F C] "todo")}
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
; ## réponse attendue : non

; est-ce que C02 est un "BO" ?
; ## langage L01
; (est-ce-que C02 est-un "BO")
; => compréhension (est-ce-que C02 est-un C01)
; ## réponse attendue : oui

; # Question n-2
; Donne moi les BOs dont tu es sur
; sens : avec E = reponse
; 1- X appartient à E, Question "est-ce que X est un BO", Réponse est oui
; 2- X n'appartient pas à E, Question "est-ce que X est un BO", Réponse n'est pas oui
; ## comprehension 1. Simpliste car ne récupère que les ?x qui sont directement mémorisés comme étant des BOs.
; ceci revient donc à une requête type Datomic.
; l'aspect original porte sur le lien entre BO et C01 à réaliser par le système.
; find ?x | (?x :isa C01)
; ## Reponse attendue
; #{C02}
; # Question n-1
; Quel est son nom ?
; ## Reponse attendue
; "BO015"
; # information
; ## l'id-zone de la première Zone du BO "BO015" est "ZD1"
; => la valeur de la propriété "id-zone" de la première Zone du BO "BO015" est "ZD1"
( "id-zone" "ZD1")
; ## le nom-zone de la première Zone du BO "BO015" est "Code Dossier IMA"
; => la valeur de la propriété "nom-zone" de la première Zone du BO "BO015" est "Code Dossier IMA"
; # question
; quelle est la valeur de la propriété "nom-zone" de la Zone "ZD1" du BO "BO015" ?
; reponse attendue : "Code Dossier IMA"


















