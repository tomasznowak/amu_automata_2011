package pl.edu.amu.wmi.daut.re;

import java.util.Arrays;

/**
 * Zarzadca operatorow podstawowych
 * wyrazen regularnych POSIX.
 */
public class BasicPosixRegexpOperatorManager extends PosixRegexpOperatorManager {

    /**
     * Konstruktor klasy.
     */
    public BasicPosixRegexpOperatorManager() {
        addOperator("*", new KleeneStarOperator.Factory(),
                    Arrays.<String>asList("", "*"), PRIORITY_3);
        addOperator(".", new AnyCharOperator.Factory(),
                    Arrays.<String>asList("."), PRIORITY_4);
        addOperator("\\(\\)", new DoNothingOperator.Factory(),
                    Arrays.<String>asList("\\(", "\\)"), PRIORITY_4);
        addOperator("\\{m,n\\}", new RangeNumberOfOccurrencesOperator.Factory(),
                    Arrays.<String>asList("", "\\{", ",", "\\}"), PRIORITY_3);
        addOperator("\\{m\\}", new FixedNumberOfOccurrencesOperator.Factory(),
                    Arrays.<String>asList("", "\\{", "\\}"), PRIORITY_3);
    }
}
