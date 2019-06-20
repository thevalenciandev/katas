package com.valenciandev.katas.args;

import java.util.regex.Pattern;

public class Arg {

    public final String argName;
    public final ArgType argType;

    public Arg(String argName, ArgType argType) {
        this.argName = argName;
        this.argType = argType;
    }

    public enum ArgType {

        BOOLEAN(false), STRING(""), INT(0);

        private final Object defaultValue;

        ArgType(Object defaultValue) {
            this.defaultValue = defaultValue;
        }

        public Object defaultValue() {
            return defaultValue;
        }

        private boolean isANumber(Object argValue) {
            return Pattern.matches("\\d+", String.valueOf(argValue));
        }

        public Object toProperType(String programArg) {
            switch (this) {
                case STRING:
                    if (isANumber(programArg)) {
                        throw new IllegalArgumentException("String expected. Was a number => " + programArg);
                    }
                    return programArg;
                case INT:
                    if (!isANumber(programArg)) {
                        throw new IllegalArgumentException("Int expected. Was something else => " + programArg);
                    }
                    return Integer.parseInt(programArg);
                default:
                    throw new IllegalArgumentException("ArgType " + this + " not supported yet.");
            }
        }

    }

    public static Arg ofBooleanType(String argName) {
        return new Arg(argName, ArgType.BOOLEAN);
    }

    public static Arg ofStringType(String argName) {
        return new Arg(argName, ArgType.STRING);
    }

    public static Arg ofIntType(String argName) {
        return new Arg(argName, ArgType.INT);
    }


}
