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
        assert false == args.getValueOfFlag("f")
        assert false == args.getValueOfFlag("b")
    }

    @Test
    public void testTwoBooleanFlagsBothTrue() {
        def schema = "bB,cB"
        def argList = ["-b", "-c"]
        Args args = new Args(schema, argList)
        
        assert "b" == args.nextFlag()
        assert true == args.getValueOfFlag("b")
        assert "c" == args.nextFlag()
        assert true == args.getValueOfFlag("c")
        assert false == args.getValueOfFlag("f")
    }

    @Test
    public void testTwoBooleanFlagsOnlyOneTrue() {
        def schema = "bB,cB"
        def argList = ["-c", "-f"]
        Args args = new Args(schema, argList)
        
        assert "c" == args.nextFlag()
        assert true == args.getValueOfFlag("c")
        assert "f" == args.nextFlag()
        assert false == args.getValueOfFlag("f")
        assert false == args.getValueOfFlag("b")
    }

    @Test
    public void testOneIntegerFlag() {
        def schema = "iI"
        def argList = ["-i", "5"]
        Args args = new Args(schema, argList)
        
        assert "i" == args.nextFlag()
        assert 5 == args.getValueOfFlag("i")
    }
}
