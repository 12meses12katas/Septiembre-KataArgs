package args;

import java.util.Iterator;

class StringFlag implements FlagType<String> {

    public String parseValue(Iterator<String> argIt) {
        return argIt.next();
    }

    public String defaultValue() {
        return "";
    }
}
