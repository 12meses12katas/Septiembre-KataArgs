package es.rchavarria.kataargs.parsers
;

import static org.junit.Assert.*;

import org.junit.Before 
import org.junit.Test 

class BooleanValueParserTest {

    private FlagValueParser parser

    @Before
    void setUp(){
        parser = new BooleanValueParser()
    }
    
    @Test
    public void testParserShouldReturnTrue() {
        assert true == parser.parse("b", ["-b"])
    }
    
    @Test
    public void testParserShouldReturnFalse() {
        assert false == parser.parse("b", ["-c"])
    }
}
