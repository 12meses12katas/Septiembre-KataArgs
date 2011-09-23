
class ArgumentParser {
	
	def values = [:]
	String schema
	
	void parse(String schema, List<String> arguments) {

		schema.split().each { schemaPart ->
			
			SchemaEntry entry = new SchemaEntry(schemaPart)
			values[entry.buildFlag()] = entry.parse(arguments)
		}
		this.schema = schema
	}
	
	def getValue(String flag) {
		
		if (values[flag] == null) {
			
			def msg = "${flag} flag not found on schema definition ${schema}"
			throw new ArgumentParserException(msg)
		}
		return values[flag]
	}	
}

class ArgumentParserException extends Throwable {
	
	ArgumentParserException(String msg) {
		super(msg)
	}
}
