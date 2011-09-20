package args;

import java.util.Iterator;

interface FlagType {
    Object parseValue(Iterator<String> argIt);
}
