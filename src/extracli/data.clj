(ns extracli.data
  (require [extracli.kb :as kb]
           [extracli.core :as ecl]))

;;; DocumentBO
(def idxDBO (atom 2000))
(def DBO026 (swap! idxDBO inc))
(def DBO027 (swap! idxDBO inc))
(def DBO028 (swap! idxDBO inc))
(def DBO030 (swap! idxDBO inc))
(def DBO051 (swap! idxDBO inc))

;;; Flux
(def idxFlux (atom 3000))
(def ECL-IKA-E (swap! idxFlux inc))
(def ECL-IKA-I (swap! idxFlux inc))
(def ECL-MAC-D (swap! idxFlux inc))

;;; Types transformation
(def idxTX (atom 4000))
(def TX-F96-E (swap! idxTX inc))
(def TX-F96-I (swap! idxTX inc))

;;; Types BO
(def idxTBO (atom 5000))
(def TBO-026 (swap! idxTBO inc))
(def TBO-027 (swap! idxTBO inc))
(def TBO-028 (swap! idxTBO inc))
(def TBO-030 (swap! idxTBO inc))
(def TBO-051 (swap! idxTBO inc))

;;; Types enregistrement
(def idxTE (atom 6000))
(def TE-F96-D (swap! idxTE inc))
(def TE-F96-T (swap! idxTE inc))

;;; CubeBW
(def idxCubeBW (atom 7000))
(def CubeBW001 (swap! idxCubeBW inc))
(def CubeBW002 (swap! idxCubeBW inc))
(def CubeBW003 (swap! idxCubeBW inc))
(def CubeBW004 (swap! idxCubeBW inc))
(def CubeBW005 (swap! idxCubeBW inc))
(def CubeBW006 (swap! idxCubeBW inc))
(def CubeBW007 (swap! idxCubeBW inc))



(def KB0
  {;;; KB internals
   0      {:isa :kb/KB, :index-next-concept 10000}

   ;;; DocumentBO
   DBO026 {:isa :ecl/DocumentBO, :type-BO TBO-026 :data-source-v [CubeBW002, CubeBW003, CubeBW004, CubeBW005, CubeBW006, CubeBW007]}
   DBO027 {:isa :ecl/DocumentBO, :type-BO TBO-027}
   DBO028 {:isa :ecl/DocumentBO, :type-BO TBO-028}
   DBO030 {:isa :ecl/DocumentBO, :type-BO TBO-030}
   DBO051 {:isa :ecl/DocumentBO, :name "ECL_Italie_BO051", :type-BO TBO-051 :data-source-v [CubeBW001]}

   ;;; CubeBW
   CubeBW001 {:isa :ecl/CubeBW, :name "ZMGENLEDG/Z_ZMGENLEDG_Q001", :description "Comptabilté générale"}
   CubeBW002 {:isa :ecl/CubeBW, :name "ZMEXTRACT/ZSD_C01",          :description "facture vente"}
   CubeBW003 {:isa :ecl/CubeBW, :name "ZMEXTRACT/ZCRM_PRI",         :description "mandatement"}
   CubeBW004 {:isa :ecl/CubeBW, :name "ZMEXTRACT/ZCRM_CASE",        :description "dossier"}
   CubeBW005 {:isa :ecl/CubeBW, :name "ZMEXTRACT/ZCSRV_C02",        :description "contrat"}
   CubeBW006 {:isa :ecl/CubeBW, :name "ZMEXTRACT/ZCSRV_C01",        :description "mission"}
   CubeBW007 {:isa :ecl/CubeBW, :name "ZMEXTRACT/ZSD_C12",          :description "commande vente"}

   ;;; Flux
   ECL-IKA-E {:isa :ecl/Flux, :client-name "IKA" :type "E" :tx TX-F96-E :DBO-v [DBO027, DBO030] :description "frais externes"}
   ECL-IKA-I {:isa :ecl/Flux, :client-name "IKA" :type "I" :tx TX-F96-I :DBO-v [DBO028] :description "frais internes"}
   ECL-MAC-D {:isa :ecl/Flux, :client-name "MAC" :type "D"}

   ;;; TX
   TX-F96-E {:isa :ecl/TX, :name "TX-F96-E", :BO-TE-v [[TBO-027, TE-F96-D], [TBO-030, TE-F96-T]]}
   TX-F96-I {:isa :ecl/TX, :name "TX-F96-I", :BO-TE-v [[TBO-028, TE-F96-D]]}

   ;;; Types BO
   TBO-026  {:isa :ecl/typeBO, :name "026"}
   TBO-027  {:isa :ecl/typeBO, :name "027"}
   TBO-028  {:isa :ecl/typeBO, :name "028"}
   TBO-030  {:isa :ecl/typeBO, :name "030"}
   TBO-051  {:isa :ecl/typeBO, :name "051"}

   ;;; TEs
   TE-F96-D {:isa :ecl/TE}
   TE-F96-T {:isa :ecl/TE}

   })



;;; données
(comment
 (def ACA-E (new-flux {:client "ACA", :type "E", :typesBO ["027" "030"]}))
 (def ACA-I (new-flux {:client "ACA", :type "I", :typesBO ["028"]}))
 (def ACT-E (new-flux {:client "ACT", :type "E", :typesBO ["027" "030"]}))
 (def ACT-I (new-flux {:client "ACT", :type "I", :typesBO ["028"]}))
 (def AED-I (new-flux {:client "AED", :type "I", :typesBO ["028"]}))
 (def ATL-C (new-flux {:client "ATL", :type "C", :typesBO ["005"]}))
 (def ATL-D (new-flux {:client "ATL", :type "D", :typesBO ["006"]}))
 (def HVI-E (new-flux {:client "HVI", :type "E", :typesBO ["027" "030"] :dateInt "201310"}))
 (def HVI-I (new-flux {:client "HVI", :type "I", :typesBO ["028"]       :dateInt "201310"}))
 (def VIV-E (new-flux {:client "VIV", :type "E", :typesBO ["027" "030"]}))
 (def VIV-I (new-flux {:client "VIV", :type "I", :typesBO ["028"]}))


 (def re7-lundi [ACA-E, ACA-I, ACT-E, ACT-I, AED-I, ATL-C, ATL-D, VIV-E, VIV-I])

 (def flux-jeudi (atom [(flux AMA D), (flux AMA M), (flux ECA C), (flux ECA D), (flux ECA M), (flux MAC C), (flux MAC D),
                        (flux MAC M), (flux MAI C), (flux MAI D), (flux MAI M), (flux MAM C), (flux MAM D), (flux MAM M),
                        (flux PNP E), (flux PNP I) ]))

 (def all-trends (range 0 10))
  )








