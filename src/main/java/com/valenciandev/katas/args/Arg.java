package com.valenciandev.katas.args;

public class Arg {

    public final String argName;
    public final ArgType argType;

    public Arg(String argName, ArgType argType) {
        this.argName = argName;
        this.argType = argType;
    }

    public enum ArgType {
        BOOLEAN
    }

    public static Arg ofBooleanType(String argName) {
        return new Arg(argName, ArgType.BOOLEAN);
    }

}
