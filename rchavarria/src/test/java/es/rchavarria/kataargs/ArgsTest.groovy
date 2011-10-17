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
        def schema = "bB,fB"
        def argList = ["-f"]
        Args args = new Args(schema, argList)
        
        assert "b" != args.nextFlag()
        assert true == args.getValueOfFlag("f")
        assert false == args.getValueOfFlag("b")
    }

    @Test
    public void testTwoBooleanFlagsBothTrue() {
        def schema = "bB,cB,fB"
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
        def schema = "bB,cB,fB"
        def argList = ["-c", "-f"]
        Args args = new Args(schema, argList)
        
        assert "c" == args.nextFlag()
        assert true == args.getValueOfFlag("c")
        assert "f" == args.nextFlag()
        assert true == args.getValueOfFlag("f")
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

    @Test
    public void testOneIntegerFlagNotPresent() {
        def schema = "iI,jI"
        def argList = ["-i", "5"]
        Args args = new Args(schema, argList)
        
        assert 0 == args.getValueOfFlag("j")
    }

    @Test
    public void testTwoIntegerFlags() {
        def schema = "iI,jI"
        def argList = ["-i", "5", "-j", "-5"]
        Args args = new Args(schema, argList)
        
        assert "i" == args.nextFlag()
        assert 5 == args.getValueOfFlag("i")
        assert "j" == args.nextFlag()
        assert -5 == args.getValueOfFlag("j")
    }

    @Test
    public void testOneFlagString() {
        def schema = "sS"
        def argList = ["-s", "this-is-a-string"]
        Args args = new Args(schema, argList)

        assert "s" == args.nextFlag()
        assert "this-is-a-string" == args.getValueOfFlag("s")        
    }
    
    @Test
    public void testDifferentFlags() {
        def schema = "iI,bB,jI,cB,sS,tS"
        def argList = ["-i", "5", "-b", "-s", "this-is-a-string"]
        Args args = new Args(schema, argList)
        
        assert "i" == args.nextFlag()
        assert 5 == args.getValueOfFlag("i")
        assert "b" == args.nextFlag()
        assert true == args.getValueOfFlag("b")
        assert "s" == args.nextFlag()
        assert "this-is-a-string" == args.getValueOfFlag("s")

        assert false == args.getValueOfFlag("c")
        assert 0 == args.getValueOfFlag("j")
        assert "" == args.getValueOfFlag("t")
    }
}
