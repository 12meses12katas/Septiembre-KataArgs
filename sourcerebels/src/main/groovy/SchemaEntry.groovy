class SchemaEntry {

	private final static String BOOLEAN_PREFIX = "B"
	private final static String STRING_PREFIX = "S"
	private final static String INTEGER_PREFIX = "I"
	private final static String FLAG_PREFIX = "-"
	
	private final static boolean DEFAULT_BOOLEAN_VALUE = false
	private final static String DEFAULT_STRING_VALUE = ""
	private final static int DEFAULT_INTEGER_VALUE = 0

	final String schemaPart
	
	SchemaEntry(String schemaPart) {
		this.schemaPart = schemaPart
	}
	
	boolean isBoolean() {
		return schemaPart.startsWith(BOOLEAN_PREFIX)
	}
	
	boolean isString() {
		return schemaPart.startsWith(STRING_PREFIX)
	}
	
	boolean isInteger() {
		return schemaPart.startsWith(INTEGER_PREFIX)
	}
	
	def parse(List<String> arguments) {
		if (isBoolean()) {
			return parseBoolean(arguments)
		}
		if (isString()) {
			return parseString(arguments)
		}
		if (isInteger()) {
			return parseInteger(arguments)
		}
	}

	private String buildFlag() {
		return FLAG_PREFIX + schemaPart[1]
	}
		
	private int parseInteger(List<String> arguments) {
		def index = getFlagIndex(arguments)
		if (index == -1) {
			return DEFAULT_INTEGER_VALUE
		}
		return arguments[index + 1].toInteger()
	}
	
	private String parseString(List<String> arguments) {
		def index = getFlagIndex(arguments)
		if (index == -1) {
			return DEFAULT_STRING_VALUE
		}
		return arguments[index + 1]
	}
	
	private boolean parseBoolean(List<String> arguments) {
		if (arguments.contains(buildFlag())) {
			return true
		}
		return DEFAULT_BOOLEAN_VALUE
	}
	
	private getFlagIndex(List<String> arguments) {
		return arguments.indexOf(buildFlag())
	}
}
