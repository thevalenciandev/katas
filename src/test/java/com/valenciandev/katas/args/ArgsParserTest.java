package com.valenciandev.katas.args;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ArgsParserTest {

    @Test
    void parsesBooleanValues() {
        Arg[] argsSchema = new Arg[]{Arg.ofBooleanType("l")};
        ArgsParser argsParser = new ArgsParser(argsSchema);

        String[] programArgs = {"-l"};
        argsParser.loadProgramArgs(programArgs);

        assertThat(argsParser.valueFor("l"), is(true));
    }

    @Test
    void defaultsBooleanValuesToFalse() {
        Arg[] argsSchema = new Arg[]{Arg.ofBooleanType("l")};
        ArgsParser argsParser = new ArgsParser(argsSchema);

        argsParser.loadProgramArgs(new String[0]);

        assertThat(argsParser.valueFor("l"), is(false));
    }

    @Test
    void blowsUpIfBooleanArgIsUnknown() {
        Arg[] argsSchema = new Arg[]{Arg.ofBooleanType("l")};
        ArgsParser argsParser = new ArgsParser(argsSchema);

        argsParser.loadProgramArgs(new String[0]);

        assertThrows(IllegalArgumentException.class, () -> argsParser.valueFor("UNKNOWN"));
    }

}
