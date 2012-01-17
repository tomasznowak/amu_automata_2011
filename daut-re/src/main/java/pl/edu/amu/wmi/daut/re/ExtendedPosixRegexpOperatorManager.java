package pl.edu.amu.wmi.daut.re;

import java.util.Arrays;

/**
 * Zarzadca operatorow rozszerzonych
 * wyrazen regularnych POSIX.
 */
class ExtendedPosixRegexpOperatorManager extends PosixRegexpOperatorManager {

    public static final int PRIORITY_1 = 1;

    public ExtendedPosixRegexpOperatorManager() {
        addOperator("*", new KleeneStarOperator.Factory(),
                    Arrays.<String>asList("", "*"), PRIORITY_3);
        addOperator(".", new AnyCharOperator.Factory(),
                    Arrays.<String>asList("."), PRIORITY_4);
        addOperator("()", new DoNothingOperator.Factory(),
                    Arrays.<String>asList("(", ")"), PRIORITY_4);
        addOperator("[::]", new AsciiCharacterClassOperator.Factory(),
                    Arrays.<String>asList("[:", ":]"), PRIORITY_4);
        addOperator("+", new AtLeastOneOperator.Factory(),
                    Arrays.<String>asList("", "+"), PRIORITY_3);
        addOperator("?", new OptionalityOperator.Factory(),
                    Arrays.<String>asList("", "?"), PRIORITY_3);
        addOperator("{m}", new FixedNumberOfOccurrencesOperator.Factory(),
                    Arrays.<String>asList("", "{", "}"), PRIORITY_3);
        addOperator("{m,n}", new RangeNumberOfOccurrencesOperator.Factory(),
                    Arrays.<String>asList("", "{", ",", "}"), PRIORITY_3);
        addOperator("|", new AlternativeOperator.Factory(),
                    Arrays.<String>asList("", "|", ""), PRIORITY_1);
    }
}
