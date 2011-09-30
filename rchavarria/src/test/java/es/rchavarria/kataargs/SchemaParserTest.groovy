package es.rchavarria.kataargs;

import static org.junit.Assert.*;

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

        assert ["b": "B"] == parser.toMap("bB") 
        assert ["c": "B"] == parser.toMap("cB") 
        assert ["f": "B"] == parser.toMap("fB") 
        assert ["f": "I"] == parser.toMap("fI") 
        assert ["f": "L"] == parser.toMap("fL") 
}
    
    @Test
    public void testTwoFlags(){
        SchemaParser parser = new SchemaParser()
        
        assert ["b":"B", "c":"B"] == parser.toMap("bB,cB")
        assert ["c":"B", "f":"I"] == parser.toMap("cB,fI")
        assert ["f":"I", "g":"L"] == parser.toMap("fI,gL")
}
    
    @Test
    public void testNFlags(){
        SchemaParser parser = new SchemaParser()
        
        def map = ["a":"A", "b":"B", "c":"C"] 
        assert map == parser.toMap("aA,bB,cC")
        map.d = "D"
        map.e = "E"
        assert map == parser.toMap("aA,bB,cC,dD,eE")
}

}
