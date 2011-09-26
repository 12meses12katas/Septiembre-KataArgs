package es.rchavarria.kataargs;

import org.junit.Test 

import static org.junit.Assert.*;

class ArgsTest {

    @Test
    public void testOneBooleanFlagTrue() {
        def schema = "bB"
        def argList = ["-b"]
        Args args = new Args(schema, argList)
        
        assert "b" == args.nextFlag()
        assert true == args.getValueOfFlag("b")
}

    @Test
    public void testOneBooleanFlagFalse() {
        def schema = "bB"
        def argList = ["-f"]
        Args args = new Args(schema, argList)
        
        assert "b" != args.nextFlag()
        assert false == args.getValueOfFlag("b")
}
}
