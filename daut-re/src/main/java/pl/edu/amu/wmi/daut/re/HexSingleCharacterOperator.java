package pl.edu.amu.wmi.daut.re;

import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification;
import java.util.List;

/**
 * Wyjatki.
 */
class InvalidHexSingleCharacterOperatorException extends RuntimeException {
    public InvalidHexSingleCharacterOperatorException(String message) {
        super(message);
    }
}

/**
 * Klasa reprezentujaca pojedynczy konkretny znak o kodzie Unicode podanym w zapisie szesnastkowym.
 */
public class HexSingleCharacterOperator extends NullaryRegexpOperator {

    private char character;

    /**
     * Jako parametr przyjmuje char, jaki ma zaakceptowac.
     */
    public HexSingleCharacterOperator(char a) {
        character = a;
    }

    /**
     * Funkcja zwracajaca znak akceptowany przez automat.
     */
    public char getCharacter() {
        return character;
    }

    @Override
    public AutomatonSpecification createFixedAutomaton() {
        return new NaiveAutomatonSpecification().makeOneTransitionAutomaton(character);
    }

    /**
     * Fabryka.
     */
    public static class Factory extends NullaryRegexpOperatorFactory {

        static final int BASE = 16;
        static final int BASE3 = 3;
        static final int BASE4 = 4;
        static final int BASE8 = 8;

        @Override
        public int numberOfParams() {
            return 1;
        }

        /**
         * Jako parametr przyjmuje ciag zapisany w systemie szesnastkowym.
         */
        protected RegexpOperator doCreateOperator(List<String> params) {
            String s = params.get(0);
            int length = s.length();
            if (length <= BASE4)
                throw new
                InvalidHexSingleCharacterOperatorException("Argument was too short.");
            if (s.charAt(0) != '\\')
                throw new
                InvalidHexSingleCharacterOperatorException("Backslash was expected here.");
            if (s.charAt(1) != 'x' && s.charAt(1) != 'X')
                throw new
                InvalidHexSingleCharacterOperatorException("\"x\" was expected here.");
            if (s.charAt(2) != '{')
                throw new
                InvalidHexSingleCharacterOperatorException("Left curly brace was expected here.");
            if (s.charAt(length - 1) != '}')
                throw new
                InvalidHexSingleCharacterOperatorException("Right curly brace was expected here.");
            s = s.substring(BASE3, length - 1);
            length = s.length();
            if (length > BASE8)
                throw new
                InvalidHexSingleCharacterOperatorException("Value in braces was too long.");
            for (char c : s.toCharArray())
                if ((c < '0' || c > '9') && (c < 'a' || c > 'f') && (c < 'A' || c > 'F'))
                    throw new
                    InvalidHexSingleCharacterOperatorException("Wrong hexadecimal value.");
            if (length == BASE8 && (s.charAt(0) < '0' || s.charAt(0) > '7'))
                throw new
                InvalidHexSingleCharacterOperatorException("Value in braces was too high.");
            int i = Integer.parseInt(s, BASE);
            char c = (char) i;
            return new SingleCharacterOperator(c);
        }
    }
}
