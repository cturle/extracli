; === Principes ===
; un concept est soit un "the ? of ?" soit un "the ? val ?". Ce qui en fait sa définition.
; une et une seule définition par concept
; suppression d'un concept => supprimer les liens (C-the/of/val->?) et les concepts (? -of-> C)
;   impossible si il existe des concepts (? -the-> C)
; une première phase de compréhension permet de définir les liens sémantiques issus de l'interprétation


(The Number "10")
=> ; fonction clj 'The'
(C01 ^det The ^type Number)
(C02 ^type repr-litterale-de ^of C01)
(C03 ^type value-of ^of C02 ^is "10")
=>
(C04 ^type repr-conceptuelle-de ^of C01)
(C05 ^type value-of ^of C04 ^is [C*1, C*0])


(The Number "9")
=> ; fonction clj 'The'
(C01 ^det The ^type Number)
(C02 ^type repr-litterale-de ^of C01)
(C03 ^type value-of ^of C02 ^is "9")
=>
(C04 ^type repr-conceptuelle-de ^of C01)
(C05 ^type value-of ^of C04 ^is [C*9])
=>
(C01 ^is C*9)


(The Number "1.02")
=> ; fonction clj 'The'
(C01 ^det The ^type Number ^representation R01)
(R01 ^type repr-litterale-de ^of C01 ^value "1.02")
=>
(R02 ^type repr-conceptuelle-de ^of C01 ^value [C*1, Dot, C*0, C*2])
=>
(R03 ^type partie-entiere-de ^of C01 ^value [C*1])
(R04 ^type partie-decimale-de ^of C01 ^value [C*0, C*2])


; ===== definitions, terminologies 'Quotient', 'Fraction', 'Ecriture-Fractionnaire' =====
; "quotient" de a par b (non nul) est le nombre, qui multiplié par b, donne a.
; son "écriture fractionnaire" est "a/b" cad ["a", "/", "b"]
;  a est le "numérateur", b le "dénominateur (non nul)"
; quand a et b sont des nombres entiers, on dit de ces écritures sont des "fraction"
; il existe plusieurs ecritures fractionnaire pour un même quotient : 3/4, 12/16, ...
; url : http://www.assistancescolaire.com/eleve/6e/maths/reviser-une-notion/utiliser-les-termes-quotient-ecriture-fractionnaire-fraction-6mfr11
(The Number "7/2") --> C01
=> ; fonction clj 'The'
(C01 ^type Number)
(C02 ^type repr-litterale-de ^of C01)
(C03 ^type value-of ^of C02 ^val "7/2")
=>
(C04 ^type repr-conceptuelle-de ^of C01)
(C05 ^type value-of ^of C04 ^is [C*7, slash, C*2])
=>
(C01 )
...
=>
(C01 ^isa Fraction)

; "7/2"
; FractionRepresentation F1
(F1 ^type FractionRepresentation ^value "7/2")
; "7 / 2"
(RF1 ^type Representation ^of F1 ^value )
; (quotient F1)  = 7
(QF1 ^type quotient-de ^of F1)
(QF1 ^is C*7)
; (dividende F1) = 2
(DF1 ^type dividende-de ^of F1)
(DF1 ^is C*2)
; repr-fraction F2 : quotient=14, dividende=4
; repr-decimale D1: integer-part=3, decimal-part=[5]

;
(S01 ^type String ^val "7/2")                         ; (S01 ^isa String ^val "7/2")
(N01 ^type Number)                                    ; (N01 ^isa Number)
(R01 ^type LiteralRepr ^of N01 ^val S01)              ; (N01 ^literal-repr S01)

(T01 ^type Class ^of R01 ^val Ecriture-Fractionnaire) ; (S01 ^isa Ecriture-Fractionnaire) ; marche parce que le ^val de R01 est défini

(C01 ^type Fraction)
(C02 ^type Numerator   ^of C01)
(C03 ^type Denominator ^of C01)
(R02 ^type Representation ^of C01 ^val S01)

(N02 ^type Number ^val 7)
(C02 ^type Numerator   ^of C01 ^val N02)
(N03 ^type Number ^val 2)
(C03 ^type Denominator ^of C01 ^val N03)

(C01 ^type Character ^val \7)
(C02 ^type Character ^val \/)
(C03 ^type Character ^val \2)
  ;(R01 ^type Representation//fraction  )

; prendre plutot (le nombre "10/11") car multi-digit
; N1 est le nombre
; S1 = String "10/11"
; representation-literale-of N1 (R1) = S1
; interprete string S1 as number ...
; representation-conceptuelle-of R1 (R2) = [(le nombre "10"), slash, (le nombre "11")]
; R1 isa representation-conceptuelle-of-a-Fraction car [Integer, slash, Integer]
; F1 = Fraction dont R1 est la representation-conceptuelle-of


; "le nombre 10"

; "la somme de 1 + 2", "la somme de 2 + 1", "le nombre 3"

; "1X34", valeur de X ?, réponse 2 car (inc 1 2), (inc 2 3), (inc 3 4)
;























