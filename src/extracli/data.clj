;(BW-prod referentiel ?r)
;(?s isa Schedule)
;(?s what ?what)
;(?s when ?when)
;(?what update ?r)

;(?d isa DocBoExtracli")
;(?d depend-des-sources-de-donnée* _)

;; représentation par atom
(defn new-flux
  []
  (new-flux {})
  [value]
  (atom value) )

(defn update-flux [flux value]
  (reset! flux value) )


;;; données

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


(def all-flux (atom {}))

(defflux AMA C {:typesBO ["015"]})
=> (set-or-update-flux '[AMA C] {:typesBO ["015"]})
==>

(flux AMA C)

(defn get-flux
"Récupère le flux identifié par son nom de client [string C] et son [type-flux TE].
"
  [[C TE]]
==> (get all-flux '[AMA E] (throw (str "flux unknown : id-flux = " id-flux)))


(defflux AMA D)
(defflux AMA M)
(defflux ECA C)
(defflux ECA D)
(defflux ECA M)
(defflux MAC C)
(defflux MAC D)
(defflux MAC M)
(defflux MAI C)
(defflux MAI D)
(defflux MAI M)
(defflux MAM C)
(defflux MAM D)
(defflux MAM M)
(defflux PNP E)
(defflux PNP I)


(def re7-lundi [ACA-E, ACA-I, ACT-E, ACT-I, AED-I, ATL-C, ATL-D, VIV-E, VIV-I])

(def flux-jeudi (atom [(flux AMA D), (flux AMA M), (flux ECA C), (flux ECA D), (flux ECA M), (flux MAC C), (flux MAC D),
                       (flux MAC M), (flux MAI C), (flux MAI D), (flux MAI M), (flux MAM C), (flux MAM D), (flux MAM M),
                       (flux PNP E), (flux PNP I) ]))


 (def all-trends (range 0 10))

