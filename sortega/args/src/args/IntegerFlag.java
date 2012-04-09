package args;

import java.util.Iterator;

public class IntegerFlag implements FlagType<Integer> {

    public Integer parseValue(Iterator<String> argIt) {
        return Integer.parseInt(argIt.next(), 10);
    }

    public Integer defaultValue() {
        return 0;
    }
}
