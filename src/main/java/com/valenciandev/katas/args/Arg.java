package com.valenciandev.katas.args;

public class Arg {

    public final String argName;
    public final ArgType argType;

    public Arg(String argName, ArgType argType) {
        this.argName = argName;
        this.argType = argType;
    }

    public enum ArgType {
        BOOLEAN(false), STRING("");

        private final Object defaultValue;

        ArgType(Object defaultValue) {
            this.defaultValue = defaultValue;
        }

        public Object defaultValue() {
            return defaultValue;
        }
    }

    public static Arg ofBooleanType(String argName) {
        return new Arg(argName, ArgType.BOOLEAN);
    }

    public static Arg ofStringType(String argName) {
        return new Arg(argName, ArgType.STRING);
    }

}
