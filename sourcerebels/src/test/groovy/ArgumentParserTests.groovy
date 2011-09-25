import org.junit.Before
import org.junit.Test

import static org.junit.Assert.*

class ArgumentParserTests {

	private ArgumentParser argumentParser
	
	@Before
	void setUp() {
		argumentParser = new ArgumentParser()
	}
	
	@Test
	void shouldReturnTrueForExistingBooleanArguments() {
		argumentParser.parse("Bl", ["-l"])
		assertTrue argumentParser.getValue("-l")
	}
	
	@Test
	void shouldReturnFalseForUnexistingBooleanArguments() {	
		argumentParser.parse("Bp", [])
		assertFalse argumentParser.getValue("-p")
	}
	
	@Test
	void shouldReturnValueForExistingStringArguments() {
		argumentParser.parse("Sh", ["-h", "blog.sourcerebels.com"])
		assertEquals "blog.sourcerebels.com", argumentParser.getValue("-h")
	}
	
	@Test
	void shouldReturnEmptyStringForUnexistingStringArguments() {
		argumentParser.parse("Sh Sj", ["-h", "blog.sourcerebels.com"])
		assertEquals "", argumentParser.getValue("-j")
	}
	
	@Test
	void shouldReturnValueForExistingIntegerArguments() {
		argumentParser.parse("Ip", ["-p", "8080"])
		assertEquals 8080, argumentParser.getValue("-p")
	}
	
	@Test
	void shouldReturnZeroForUnexistingIntegerArguments() {
		argumentParser.parse("Ip In", ["-p", "8080"])
		assertEquals 0, argumentParser.getValue("-n")
	}
	
	@Test
	void shouldParseNegativeIntegers() {
		argumentParser.parse("Ip", ["-p", "-2"])
		assertEquals(-2, argumentParser.getValue("-p"))
	}
	
	@Test
	void shouldParseMixedArguments() {
		argumentParser.parse("Sh Ip Bs", ["-h", "blog.sourcerebels.com", "-p", "8080"])
		assertEquals "blog.sourcerebels.com", argumentParser.getValue("-h")
		assertEquals 8080, argumentParser.getValue("-p")
		assertFalse argumentParser.getValue("-s")
	}
	
	@Test
	void shouldParseUnorderedArguments() {
		argumentParser.parse("Ip Sh Bs", ["-h", "blog.sourcerebels.com", "-p", "8080"])
		assertEquals "blog.sourcerebels.com", argumentParser.getValue("-h")
		assertEquals 8080, argumentParser.getValue("-p")
		assertFalse argumentParser.getValue("-s")
	}
	
	@Test
	void shoultFailForArgumentThatDontMatchSchema() {
		
		try {
		
			argumentParser.parse("Bp", ["-p"])
			argumentParser.getValue("-j")
			fail("ArgumentParserException should be thrown")
				
		} catch (ArgumentParserException e) {
		
			assertEquals "-j flag not found on schema definition Bp", e.message
		}
	}
}
