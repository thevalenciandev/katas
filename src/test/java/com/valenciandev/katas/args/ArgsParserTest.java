package com.valenciandev.katas.args;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ArgsParserTest {

    @Test
    void parsesBooleanValues() {
        Arg[] argsSchema = new Arg[]{Arg.ofBooleanType("l")};
        ArgsParser argsParser = new ArgsParser(argsSchema);

        String[] programArgs = {"-l"};
        argsParser.loadProgramArgs(programArgs);

        assertThat(argsParser.valueFor("l"), is(true));
    }

}
