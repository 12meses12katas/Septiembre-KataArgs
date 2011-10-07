package es.rchavarria.kataargs.parsers

import static org.junit.Assert.*;
import org.junit.Before 
import org.junit.Test;

import es.rchavarria.kataargs.exceptions.FlagValueNotPresentException;

class IntegerValueParserTest {

    private FlagValueParser parser

    @Before
    void setUp(){
        parser = new IntegerValueParser()
    }
    
    @Test
    public void testIntegerFlagWithValuePresent(){
        assert 5 == parser.parse("i", ["-i", "5"])
        assert 10 == parser.parse("i", ["-i", "10"])
        assert -5 == parser.parse("i", ["-i", "-5"])
    }
    
    public void testFlagNotPresentReturnsDefaultValue(){
        assert 0 == parser.parse("i", ["-d", "5"])
    }
    
    @Test(expected=FlagValueNotPresentException)
    public void testFlagValueNotPresent(){
        parser.parse("i", ["-i"])
    }

}    
