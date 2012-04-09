package args;

import java.util.Iterator;

interface FlagType<T> {
    T parseValue(Iterator<String> argIt);
    T defaultValue();
}
