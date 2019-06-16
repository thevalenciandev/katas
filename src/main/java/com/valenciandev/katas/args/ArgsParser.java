package com.valenciandev.katas.args;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static com.valenciandev.katas.args.Arg.ArgType.BOOLEAN;
import static java.util.stream.Collectors.toMap;

public class ArgsParser {
    private final Map<String, Arg> schema;
    private final Map<String, Object> values;

    public ArgsParser(Arg[] argsSchema) {
        schema = Stream.of(argsSchema).collect(toMap(arg -> arg.argName, arg -> arg));
        values = new HashMap<>(argsSchema.length);
    }

    public void loadProgramArgs(String[] programArgs) {

        int i = 0;
        while (i < programArgs.length) {

            String argName = removeDashFrom(programArgs[i++]);
            if (schema.get(argName).argType == BOOLEAN) {
                values.put(argName, true);
                continue;
            }
        }
    }

    private String removeDashFrom(String programArg) {
        return programArg.charAt(0) == '-' ? programArg.substring(1) : programArg;
    }

    public boolean valueFor(String argName) {
        Object value = values.get(argName);
        if (value == null) {
            if (schema.containsKey(argName)) {
                return false;
            } else {
                throw new IllegalArgumentException("Arg " + argName + " not expected.");
            }
        }
        return true;
    }

}
