(ns extracli.utils
    (:require [clojure.string :refer [join]]
              ;[extracli.ksh   :refer :all]
              ))


(defn spitln [filename lignes]
  (with-open [out (clojure.java.io/writer (str "C:/ctu/repo/extracli/test/extracli/" filename))]
    (binding [*out* out]
      (doseq [L lignes]
        (println L) ))))
