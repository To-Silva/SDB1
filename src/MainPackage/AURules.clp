(deftemplate incentivo
 (slot AENum)
 (slot valor)
 (slot coordX)
 (slot coordY)
 )


(defrule aceitar (incentivo ?n ?v ?x ?y) (?valor) => 
	(?valor<?v)
)