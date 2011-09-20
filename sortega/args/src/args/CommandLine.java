package args;

import java.util.*;

public class CommandLine {

    private List<String> arguments;
    private Map<Character, Object> flagValues;
    private Map<Character, FlagType> flagTypes;

    public CommandLine(Map<Character, FlagType> flagTypes) {
        this.flagTypes = flagTypes;
        this.flagValues = new HashMap<Character, Object>();
        this.arguments = new LinkedList<String>();
    }

    public List<String> getArguments() {
        return arguments;
    }

    public boolean hashFlag(char flag) {
        return flagValues.contains(flag);
    }

    public void setFlag(char flag, Object value) {
        flagValues.add(flag);
    }

    public void addArgument(String argument) {
        arguments.add(argument);
    }

    public String flagValue(char flag) {
        return "";
    }
}
