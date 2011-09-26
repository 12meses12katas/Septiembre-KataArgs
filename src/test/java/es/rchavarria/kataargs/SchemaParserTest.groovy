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

}
