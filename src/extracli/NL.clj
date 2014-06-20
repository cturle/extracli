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




