(ns extracli.user
  (:require [extracli.kb   :as kb]
            [extracli.ksh  :as ksh]
            [extracli.core :as ecl]
            [extracli.data :as data]))

; (require '[extracli.core :refer :all])

;;; re7-lundi

;(spitln "tgetBOsRe7-lundi.ksh"
;   (out-getBOs-M-1 re7-lundi)
;)


; (let [[KB1, ?RC] (action-001 data/KB0 "MAC" "D")] (:to-string (get KB1 ?RC)))
;   => "./tgetBOsFlux.ksh MAC D 2012 05 rec > log.txt"
; (let [[KB1, ?RC] (action-001 data/KB0 "MAC" "B")] KB1)
  (comment
  :in-v   [?CLI ?TF]
  :pre    {?FL {:isa :ecl/Flux, :client-name ?CLI, :type-flux ?TF}}
  :out-1  ?RC
  :post   {?RC    {:isa :ksh/RedirectCmd, :cmd ?C, :output-to "log.txt", :to-string ?S}
           ?C     {:isa :ksh/Cmd, :genere-v ?EBO-v}
           ?ECL   {:isa :ecl/Extraction, :flux ?FL :AAAA "2012" :MM "05" :env "rec", :exportBO-v ?EBO-v} }
  )
(defn action-001 [KB_pre ?CLI ?TF]
  (let [?FL           (kb/get-by-properties         KB_pre {:isa :ecl/Flux, :client-name ?CLI, :type ?TF})
        [KB_-4 ?ECL]  (ecl/Extraction=>create-1     KB_pre ?FL "2012" "05" "rec")
        [KB_-3 ?C]    (ecl/KshCmd=>create-1         KB_-4  ?ECL)
        [KB_-2]       (ksh/Cmd=>to-string-1         KB_-3  ?C)
        [KB_-1 ?RC]   (ksh/RedirectCmd=>create-1    KB_-2  ?C "log.txt")
        [KB_post]     (ksh/RedirectCmd=>to-string-1 KB_-1  ?RC)]
    [KB_post ?RC]
  ))



(comment
  (defRequest
" in: [?E]
 pre: {?E {:isa :DocInstanceBO, :type-BO :_ :AAAA :_ :MM :_}}
 out: []
post: {?E {:date-dispo :_}}"
  [KB ?E] )

 ; (User=>Action-2 {1 {:isa :Extraction, :client-name "MAC", :type-flux "C", :AAAA "2012", :MM "06"} 2 {:isa :none}} 1)
 (defAction Action-2
   "  in: ?E
     pre: {?E {isa Extraction, client-name _, type-flux _, AAAA _, MM _}}
     out: ?S
    post: {?S {isa KshScript, generate* ?E, to-string-v _}}"
   )
)


(comment
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
