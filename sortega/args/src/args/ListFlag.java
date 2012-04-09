package args;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ListFlag implements FlagType<List<String>> {

    public List<String> parseValue(Iterator<String> argIt) {
        return Arrays.asList(argIt.next().split(","));
    }

    public List<String> defaultValue() {
        return Collections.emptyList();
    }
}
