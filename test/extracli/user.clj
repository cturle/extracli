(ns extracli.user
  (:require [extracli.core :refer :all]))

; (require '[extracli.core :refer :all])

;;; re7-lundi

; à revoir : préférer un "./tgetBOsRe7.ksh lundi", "./tcopyBOsRe7.ksh lundi", "./tgenECLre7.ksh lundi"

;(spitln "tgetBOsRe7-lundi.ksh"
;   (out-getBOs-M-1 re7-lundi)
;)

(comment

  (def KB-0 (atom {}))


  ;;; <IHM>
  (def KB  (atom @KB-0))
  (def idE (gensym "ExportBO-"))
  (def idC (gensym "KshCmd-"))

  (swap! KB assoc idE {:isa 'ExportBO, :env "rec", :type-BO "016", :client-name "MAI", :AAAA "2012", :MM "03", :part-name "ECL-MAI-D"})
  (swap! KB assoc idC {:isa 'KshCmd, :produit idE})
  ;;; </IHM>


  ; (KshCmd=>to-string-1! KB idC)
  (defn KshCmd=>to-string-app-1! [KB idC]
    (let [idE (get-in @KB [idC :produit])]
      (swap! KB KshCmd=>AppRule-1 idC idE) )
    (swap! KB KshCmd=>Rule-1 idC)
    (get-in @KB [idC :to-string]) )


(spitln "tgetBOs.ksh"
   (out-getBOs (map new-rapportBO
                    (for [TBO ["015" "016"]]
                       {:type-BO TBO :client "MAC" :AAAA "2012" :MM "02"} )))
)

(spitln "tgetBOsRe7.ksh"
   (out-getBOsRe7)
)

(gen
  (out-copyBOs-M-1 re7-lundi)
)

(gen
  (out-genECL-M-1 re7-lundi)
)

(spitln "tcopyBOs.ksh")


)
