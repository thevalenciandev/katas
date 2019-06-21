package com.thevalenciandev.katas.args;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ArgsParserTest {

    @Test
    void parsesBooleanValues() {
        Arg[] argsSchema = new Arg[]{Arg.ofBooleanType("l")};
        String[] programArgs = {"-l"};
        ArgsParser argsParser = new ArgsParser(argsSchema, programArgs);

        assertThat(argsParser.booleanValueFor("l"), is(true));
    }

    @Test
    void defaultsBooleanValuesToFalse() {
        Arg[] argsSchema = new Arg[]{Arg.ofBooleanType("l")};
        ArgsParser argsParser = new ArgsParser(argsSchema, new String[0]);

        assertThat(argsParser.booleanValueFor("l"), is(false));
    }

    @Test
    void blowsUpIfBooleanArgIsUnknown() {
        Arg[] argsSchema = new Arg[]{Arg.ofBooleanType("l")};
        ArgsParser argsParser = new ArgsParser(argsSchema, new String[0]);

        assertThrows(IllegalArgumentException.class, () -> argsParser.booleanValueFor("UNKNOWN"));
    }

    @Test
    void parsesStringValues() {
        Arg[] argsSchema = new Arg[]{Arg.ofStringType("d")};
        String[] programArgs = {"-d", "/usr/logs"};
        ArgsParser argsParser = new ArgsParser(argsSchema, programArgs);

        assertThat(argsParser.stringValueFor("d"), is("/usr/logs"));
    }

    @Test
    void defaultsStringValuesToEmptyString() {
        Arg[] argsSchema = new Arg[]{Arg.ofStringType("d")};
        ArgsParser argsParser = new ArgsParser(argsSchema, new String[0]);

        assertThat(argsParser.stringValueFor("d"), is(emptyString()));
    }

    @Test
    void blowsUpIfStringArgIsUnknown() {
        Arg[] argsSchema = new Arg[]{Arg.ofStringType("d")};
        ArgsParser argsParser = new ArgsParser(argsSchema, new String[0]);

        assertThrows(IllegalArgumentException.class, () -> argsParser.stringValueFor("UNKNOWN"));
    }

    @Test
    void blowsUpIfStringArgIsNotAString() {
        Arg[] argsSchema = new Arg[]{Arg.ofStringType("d")};
        String[] programArgs = {"-d", "8080"};

        assertThrows(IllegalArgumentException.class, () -> new ArgsParser(argsSchema, programArgs));
    }

    @Test
    void parsesIntegerValues() {
        Arg[] argsSchema = new Arg[]{Arg.ofIntType("p")};
        String[] programArgs = {"-p", "8080"};
        ArgsParser argsParser = new ArgsParser(argsSchema, programArgs);

        assertThat(argsParser.intValueFor("p"), is(8080));
    }

    @Test
    void defaultsIntValuesToZero() {
        Arg[] argsSchema = new Arg[]{Arg.ofIntType("p")};
        ArgsParser argsParser = new ArgsParser(argsSchema, new String[0]);

        assertThat(argsParser.intValueFor("p"), is(0));
    }

    @Test
    void blowsUpIfIntArgIsUnknown() {
        Arg[] argsSchema = new Arg[]{Arg.ofIntType("p")};
        ArgsParser argsParser = new ArgsParser(argsSchema, new String[0]);

        assertThrows(IllegalArgumentException.class, () -> argsParser.intValueFor("UNKNOWN"));
    }

    @Test
    void blowsUpIfIntArgIsNotAnInt() {
        Arg[] argsSchema = new Arg[]{Arg.ofIntType("p")};
        String[] programArgs = {"-p", "NOT_AN_INT"};

        assertThrows(IllegalArgumentException.class, () -> new ArgsParser(argsSchema, programArgs));
    }

}
