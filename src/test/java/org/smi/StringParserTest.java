package org.smi;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class StringParserTest {
    @ParameterizedTest
    @MethodSource("argsPositiveProviderFactory")
    void testParseStringPositive(String argument) {
        boolean result = StringParser.parseString(argument);
        assertTrue(result);
    }

    static Stream<String> argsPositiveProviderFactory() {
        return Stream.of("()", "(())", "()[]{}", "(()[])");
    }

    @ParameterizedTest
    @MethodSource("argsNegativeProviderFactory")
    void testParseStringNegative(String argument) {
        boolean result = StringParser.parseString(argument);
        assertFalse(result);
    }

    static Stream<String> argsNegativeProviderFactory() {
        return Stream.of(")(", "(()", "([)]");
    }

    @ParameterizedTest
    @MethodSource("argsExceptionProviderFactory")
    void testParseStringWithException(String argument) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    StringParser.parseString(argument);
                }
        );
        assertNotNull(exception.getMessage());
    }

    static Stream<String> argsExceptionProviderFactory() {
        return Stream.of("", "aaa");
    }


}
