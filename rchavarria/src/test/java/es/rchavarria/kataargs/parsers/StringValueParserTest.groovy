package es.rchavarria.kataargs.parsers

import static org.junit.Assert.*;
import org.junit.Before 
import org.junit.Test;

import es.rchavarria.kataargs.exceptions.FlagValueNotPresentException;

class StringValueParserTest {

    private FlagValueParser parser

    @Before
    void setUp(){
        parser = new StringValueParser()
    }

    @Test    
    public void testFlagNotPresentReturnsDefaultValue(){
        assert "" == parser.parse("s", ["-d", "a-string"])
    }
    
    @Test
    public void testStringFlagWithValuePresent(){
        assert "one" == parser.parse("s", ["-s", "one"])
        assert "ten" == parser.parse("s", ["-s", "ten"])
        assert "a-string" == parser.parse("s", ["-s", "a-string"])
    }
    
    @Test(expected=FlagValueNotPresentException)
    public void testFlagValueNotPresent(){
        parser.parse("s", ["-s"])
    }

}    
