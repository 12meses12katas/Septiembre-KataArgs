package kataArgs

class Args {
	
	def storage = [:]
	def propertyMissing(String name, value) {
		println "Guardando $name con value $value" 
		storage[name] = value 
	}
	def propertyMissing(String name) { 
		println "devolviendo valor de  $name " + storage[name] 
		storage[name] 
	}
	
	Args(Map schema, String arguments){	
		arguments.split("-").each{ parameterValue->	
			String metodo				
			ArrayList keyValue = parameterValue.split(" ")	
			schema.each{ flag, value ->
				if (keyValue[0]==flag){	
					metodo = keyValue[0]				
					this."$metodo" = convert(keyValue[1],value)
				}
			}
			if (keyValue[0]=='l'){				
				this.l = convert(keyValue[1],'b')
			}
			if (keyValue[0]=='p'){
				this.p = convert(keyValue[1],'i')
			}
			if (keyValue[0]=='d'){
				this.d = convert(keyValue[1],'s')
			}
		}		
	}
	Boolean l
	Integer p
	String d
	def convert(def value, String type){
		switch ( type ) {
			case "b":
				return value.toBoolean()
			case "i":
				return value.toInteger()
			case "s":
				return value.toString()
				break
		}
	}
}
