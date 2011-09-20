package args;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CommandLine {

    private List<String> arguments;
    private Map<Character, Object> flagValues;
    private Map<Character, FlagType> flagTypes;

    public CommandLine(Map<Character, FlagType> flagTypes) {
        this.arguments = new LinkedList<String>();
        this.flagTypes = flagTypes;
        setDefaultValues();
    }

    private void setDefaultValues() {
        this.flagValues = new HashMap<Character, Object>();
        for (Map.Entry<Character, FlagType> entry: this.flagTypes.entrySet()) {
            flagValues.put(entry.getKey(), entry.getValue().defaultValue());
        }
    }

    public List<String> getArguments() {
        return arguments;
    }

    public void addArgument(String argument) {
        arguments.add(argument);
    }

    public Object flag(char flag) {
        return flagValues.get(flag);
    }

    public<T> T flagAs(char flag, Class<T> clazz) {
        return (T) flag(flag);
    }

    public void setFlag(char flag, Object value) {
        flagValues.put(flag, value);
    }
}
