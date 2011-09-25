import org.junit.Test

import static org.junit.Assert.*

class SchemaEntryTests {

	@Test
	void isBooleanShouldReturnTrueForBooleanEntries() {
		SchemaEntry entry = new SchemaEntry("Bl")
		assertTrue "entry should be a boolean", entry.isBoolean()		
	}
	
	@Test
	void isStringShouldReturnTrueForStringEntries() {
		SchemaEntry entry = new SchemaEntry("Sh")
		assertTrue "entry should be a string", entry.isString()
	}
	
	@Test
	void isIntegerShouldReturnTrueForIntegerEntries() {
		SchemaEntry entry = new SchemaEntry("Ip")
		assertTrue "entry should be an integer", entry.isInteger()
	}
	
	@Test
	void buildFlagShouldReturnTheFlag() {
		
		SchemaEntry entry = new SchemaEntry("Bl")
		assertEquals "-l", entry.buildFlag()
	}
	
	@Test
	void parseShouldReturnTrueForExistingBooleanArguments() {
		SchemaEntry entry = new SchemaEntry("Bl")
		assertTrue "-l should be present", entry.parse(["-l"])
	}
	
	@Test
	void parseShouldReturnFalseForUnexistingBoooleanArguments() {
		SchemaEntry entry = new SchemaEntry("Bl")
		assertFalse "-j should not be present", entry.parse(["-j"])
	}

	@Test
	void parseShouldReturnValueForExistingStringArguments() {
		final String value = "blog.sourcerebels.com"
		SchemaEntry entry = new SchemaEntry("Sh")
		assertEquals value, entry.parse(["-h", value])
	}
	
	@Test
	void parseShouldReturnDefaultStringValueForUnexistingStringArguments() {
		SchemaEntry entry = new SchemaEntry("Sh")
		assertEquals "", entry.parse(["-j", "test"])
	}
	
	@Test
	void parseShouldReturnValueForExistingIntegerArguments() {
		SchemaEntry entry = new SchemaEntry("Ip")
		assertEquals 8080, entry.parse(["-p", "8080"])
	}
	
	@Test
	void parseShouldReturnDefaultValueForUnexistingIntegerArguments() {
		SchemaEntry entry = new SchemaEntry("Ip")
		assertEquals 0, entry.parse(["-j", "8080"])
	}
}
