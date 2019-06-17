package com.valenciandev.katas.args;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static com.valenciandev.katas.args.Arg.ArgType.BOOLEAN;
import static com.valenciandev.katas.args.Arg.ArgType.STRING;
import static java.util.stream.Collectors.toMap;

public class ArgsParser {
    private final Map<String, Arg> schema;
    private final Map<String, Object> values;

    public ArgsParser(Arg[] argsSchema, String[] programArgs) {
        schema = Stream.of(argsSchema).collect(toMap(arg -> arg.argName, arg -> arg));
        values = new HashMap<>(argsSchema.length);
        loadProgramArgs(programArgs);
    }

    private void loadProgramArgs(String[] programArgs) {

        int i = 0;
        while (i < programArgs.length) {

            String argName = removeDashFrom(programArgs[i++]);
            Arg.ArgType argType = schema.get(argName).argType;
            if (argType == BOOLEAN) {
                values.put(argName, true);
            } else {
                values.put(argName, valueOrBlowUp(programArgs[i++], argType));
            }
        }
    }

    private Object valueOrBlowUp(String argValue, Arg.ArgType argType) {
        argType.validate(argValue);
        return argValue;
    }

    private String removeDashFrom(String programArg) {
        return programArg.charAt(0) == '-' ? programArg.substring(1) : programArg;
    }

    public boolean booleanValueFor(String argName) {
        return (boolean) valueFor(argName, BOOLEAN);
    }

    public String stringValueFor(String argName) {
        return (String) valueFor(argName, STRING);
    }

    private Object valueFor(String argName, Arg.ArgType argType) {
        Object value = values.get(argName);
        if (value == null) {
            if (schema.containsKey(argName)) {
                return argType.defaultValue();
            } else {
                throw new IllegalArgumentException("Arg " + argName + " not expected.");
            }
        }
        return value;
    }

}
