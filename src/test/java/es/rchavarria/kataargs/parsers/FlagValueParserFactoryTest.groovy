package es.rchavarria.kataargs.parsers
;

import static org.junit.Assert.*;

import org.junit.Test 

class FlagValueParserFactoryTest {

    @Test
    public void testBFlagReturnsBooleanParser() {
        assert FlagValueParserFactory.createParser("b") instanceof BooleanValueParser 
    }

    @Test
    public void testIFlagReturnsIntegerParser() {
        assert FlagValueParserFactory.createParser("i") instanceof IntegerValueParser 
    }
}
