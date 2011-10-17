package es.rchavarria.kataargs;

import static org.junit.Assert.*;

import es.rchavarria.kataargs.parsers.BooleanValueParser;
import es.rchavarria.kataargs.parsers.IntegerValueParser 
import org.junit.After;
import org.junit.Before;
import org.junit.Test 

class SchemaParserTest {

    @Before
    public void setUp() throws Exception {
}

    @After
    public void tearDown() throws Exception {
}

    @Test
    public void testOneFlag(){
        SchemaParser parser = new SchemaParser()

        checkFlagAndParser("b", BooleanValueParser.class, parser.toMap("bB"))
        checkFlagAndParser("c", BooleanValueParser.class, parser.toMap("cB"))
        checkFlagAndParser("c", IntegerValueParser.class, parser.toMap("cI"))
        checkFlagAndParser("f", IntegerValueParser.class, parser.toMap("fI"))
    }
    
    private void checkFlagAndParser(def flag, def parserClass, def map){
        assert parserClass.isInstance(map[flag])
    }
    
    @Test
    public void testTwoFlags(){
        SchemaParser parser = new SchemaParser()
        
        def scheme1 = "bB,cB"
        checkFlagAndParser("b", BooleanValueParser.class, parser.toMap(scheme1))
        checkFlagAndParser("c", BooleanValueParser.class, parser.toMap(scheme1))

        def scheme2 = "cB,fI"
        checkFlagAndParser("c", BooleanValueParser.class, parser.toMap(scheme2))
        checkFlagAndParser("f", IntegerValueParser.class, parser.toMap(scheme2))
}
    
    @Test
    public void testNFlags(){
        SchemaParser parser = new SchemaParser()
        
        def scheme = "aB,bI,cB,dI"
        checkFlagAndParser("a", BooleanValueParser.class, parser.toMap(scheme))
        checkFlagAndParser("b", IntegerValueParser.class, parser.toMap(scheme))
        checkFlagAndParser("c", BooleanValueParser.class, parser.toMap(scheme))
        checkFlagAndParser("d", IntegerValueParser.class, parser.toMap(scheme))
    }

}
