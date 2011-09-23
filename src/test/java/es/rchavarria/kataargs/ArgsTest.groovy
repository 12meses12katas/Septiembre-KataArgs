package es.rchavarria.kataargs;

import org.junit.Test 

import static org.junit.Assert.*;

class ArgsTest {

    @Test
    public void testOneBooleanFlag() {
        def schema = "bB"
        def argList = ["-b"]
        Args args = new Args(schema:schema, argList:argList)
        
        assert "b" == args.nextFlag()
        assert true == args.getValueOfFlag("b")
}
}
