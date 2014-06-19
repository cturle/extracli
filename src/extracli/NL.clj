(create-domain "NL")
(add-assertion "Les français croient que le vin est bon pour la santé")
(add-assertion "Les quadra ne croient pas que le vin est bon pour la santé")
(add-assertion "Thierry est français et quadra")
(ask-question  "Que pense Thierry sur l'effet du vin sur la santé ?")

; retour user ?


; question :
"Peux-tu m'indiquer pour le mois d'août les présences et les périmètres en terme de flux des acteurs côté BI"

; on s'attend en retour à un ensemble de K (?Ks), tel que ?Ks détermine la fonction ci-dessous :
:in-a  #{?J, ?F}
:pre   {?J {:isa Jour :member "aout-2014"}
        ?F {:isa Flux} }
:out   ?A
:post  {?A {:isa Acteur-BI :est-present ?J, :perimetre ?F}}

