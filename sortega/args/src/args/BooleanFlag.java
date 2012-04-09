package args;

import java.util.Iterator;

class BooleanFlag implements FlagType<Boolean> {

    public Boolean parseValue(Iterator<String> argIt) {
        return true;
    }

    public Boolean defaultValue() {
        return false;
    }
}
