package args;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandLineParser {

    private final Map<Character, FlagType> flagTypes;

    public CommandLineParser(String flags) {
        this.flagTypes = new HashMap<Character, FlagType>();
        parseFlags(flags);
    }

    private void parseFlags(String flags) {
        Pattern flagPattern = Pattern.compile("(.)(=(.))?(.*)");

        while(!flags.isEmpty()) {
            Matcher m = flagPattern.matcher(flags);
            m.matches();
            char flag = m.group(1).charAt(0);
            char type = (m.group(3) != null) ? m.group(3).charAt(0) : 'b';
            flags = m.group(4);

            addFlag(flag, type);
        }
    }

    private void addFlag(char flag, char typeName) {
        FlagType type;

        switch(typeName) {
            case 'b':
                type = new BooleanFlag();
                break;

            case 's':
                type = new StringFlag();
                break;

            default:
                throw new IllegalArgumentException("Unknown flag type: '" + typeName + "'");
        }

        this.flagTypes.put(flag, type);
    }

    public CommandLine parse(String[] arguments) {
        CommandLine commandLine = new CommandLine(flagTypes);

        Iterator<String> argIt = Arrays.asList(arguments).iterator();

        while(argIt.hasNext()) {
            String arg = argIt.next();

            if (isFlag(arg)) {
                char flag = arg.substring(1).charAt(0);

                FlagType type = flagTypes.get(flag);
                if (type != null)
                    commandLine.setFlag(flag, type.parseValue(argIt));
                else
                    throw new UnknownFlag(flag);

            } else {
                commandLine.addArgument(arg);
            }
        }

        return commandLine;
    }

    private boolean isFlag(String argument) {
        return argument.startsWith("-") && argument.length() == 2;
    }

    public class UnknownFlag extends RuntimeException {
        public UnknownFlag(char flag) {
            super("Unknown flag '" + flag + "'");
        }
    }

}
