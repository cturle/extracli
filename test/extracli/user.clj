(ns extracli.user
  (:require [extracli.core :refer :all]))

; (require '[extracli.core :refer :all])

;;; re7-lundi

; à revoir : préférer un "./tgetBOsRe7.ksh lundi", "./tcopyBOsRe7.ksh lundi", "./tgenECLre7.ksh lundi"

;(spitln "tgetBOsRe7-lundi.ksh"
;   (out-getBOs-M-1 re7-lundi)
;)


; (let [[KB1, CMD] (action-003 KB0 "MAC" "C")] (:to-string CMD))
(comment
(defAction action-003
  :in-v [?CLI ?TF]
  :pre  {?FL {:isa Flux, :client-name ?CLI, :type-flux ?TF}}
  :out  ?CMD
  :post {?CMD {:isa Cmd, :to-string _, :generate-1 ?E, :output-to "log.txt"}
         ?E   {:isa Extraction, :flux ?FL, :AAAA "2012" :MM "05" :env "rec"} }
  )

(defAction action-004
  :in-v [?E]
  :pre  {?FL {:isa Flux, :client-name _, :type-flux _}
         ?E  {:isa Extraction, :flux ?FL, :AAAA _ :MM _ :env _} }
  :out  ?CMD
  :post {?CMD {:isa Cmd, :generate-1 ?E :cmd _ :arg-v _}}
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
