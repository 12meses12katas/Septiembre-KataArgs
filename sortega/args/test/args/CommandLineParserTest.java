package args;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class CommandLineParserTest {

    @Test
    public void parseEmptyCommandLine() {
        CommandLineParser parser = new CommandLineParser("");
        CommandLine commandLine = parser.parse(asArgs());
        assertNotNull(commandLine);
        assertEquals(0, commandLine.getArguments().size());
    }

    @Test
    public void parseFlag() {
        CommandLineParser parser = new CommandLineParser("lv");
        CommandLine commandLine = parser.parse(asArgs("-l"));
        assertTrue("Flag l is set", (Boolean) commandLine.flag('l'));
        assertFalse("Flag v is unset", commandLine.flagAs('v', Boolean.class));
    }

    @Test(expected = CommandLineParser.UnknownFlag.class)
    public void exceptionOnUnknownFlag() {
        CommandLineParser parser = new CommandLineParser("");
        parser.parse(asArgs("-l"));
    }

    @Test
    public void argumentsArePreserved() {
        CommandLineParser parser = new CommandLineParser("lv");
        CommandLine line = parser.parse(asArgs("-l", "file.txt", "-v", "other_file.txt"));
        assertEquals(asList("file.txt", "other_file.txt"), line.getArguments());
    }

    @Test
    public void parseStringFlag() {
        CommandLineParser parser = new CommandLineParser("f=so=s");
        CommandLine line = parser.parse(asArgs("-f", "file.txt"));
        assertEquals("", line.flagAs('o', String.class));
        assertEquals("file.txt", line.flagAs('f', String.class));
        assertEquals("No arguments", 0, line.getArguments().size());
    }

    // TODO: flagAs wrong type
    // TODO: default value for string flags
    // TODO: incorrect flag type

    public String[] asArgs(String... arguments) {
        return arguments;
    }
}
