package org.smi;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class StringParser {

    public static boolean parseString(String string) {
        System.out.println("Input string: " + string);
        checkInputString(string);
        char[] chars = string.toCharArray();
        if (chars.length % 2 != 0)
            return false;
        Map<Character, Character> validPairSymbolsMap = new HashMap<>();
        validPairSymbolsMap.put(')', '(');
        validPairSymbolsMap.put('}', '{');
        validPairSymbolsMap.put(']', '[');
        Deque<Character> stackForCheck = new ArrayDeque<>();
        for (Character symbol : chars
        ) {
            if (validPairSymbolsMap.containsValue(symbol))
                stackForCheck.push(symbol);
            else {
                if (stackForCheck.isEmpty())
                    return false;
                else {
                    if (stackForCheck.pop() != validPairSymbolsMap.get(symbol))
                        return false;
                }
            }
        }
        return stackForCheck.isEmpty();
    }

    private static void checkInputString(String string) {
        if (string == null || string.isEmpty()) {
            throw new IllegalArgumentException("String can't be null or empty.");
        } else {
            if (!string.matches("[(){}\\[\\]]+")) {
                throw new IllegalArgumentException("Incorrect string. String can contain only {[()]} symbols.");

            }
        }
    }


    public static void main(String[] args) {
        System.out.println(parseString("()"));
    }
}
