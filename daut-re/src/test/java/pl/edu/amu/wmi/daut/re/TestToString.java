package pl.edu.amu.wmi.daut.re;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;

/**
 * Testuje działanie metody toString().
 */
public class TestToString extends TestCase {

    /**
     * Test sprawdzający AlternativeOperator, SingleCharacterOperator i
     * BellCharacterOperator.
     */
    public final void testToString1() throws Exception {
        List<RegexpOperatorTree> subtrees = new ArrayList<RegexpOperatorTree>();
        RegexpOperator root = new AlternativeOperator();
        RegexpOperator s1 = new SingleCharacterOperator('a');
        RegexpOperator s2 = new BellCharacterOperator();
        RegexpOperatorTree tree0 = new RegexpOperatorTree(s1, subtrees);
        RegexpOperatorTree tree1 = new RegexpOperatorTree(s2, subtrees);
        subtrees.add(tree0);
        subtrees.add(tree1);
        RegexpOperatorTree tree = new RegexpOperatorTree(root, subtrees);
        assertEquals(tree.getHumanReadableFormat(),
                "ALTERNATIVE\n|_SINGLE_OPERATOR_a\n|_BELL\n");
    }

    /**
     * Test sprawdzający ConcatenationOperator, AnyCharOperator i AnyStringOperator.
     */
    public final void testToString2() throws Exception {
        List<RegexpOperatorTree> subtrees = new ArrayList<RegexpOperatorTree>();
        RegexpOperator root = new ConcatenationOperator();
        RegexpOperator s1 = new AnyCharOperator();
        RegexpOperator s2 = new AnyStringOperator();
        RegexpOperatorTree tree0 = new RegexpOperatorTree(s1, subtrees);
        RegexpOperatorTree tree1 = new RegexpOperatorTree(s2, subtrees);
        subtrees.add(tree0);
        subtrees.add(tree1);
        RegexpOperatorTree tree = new RegexpOperatorTree(root, subtrees);
        assertEquals(tree.getHumanReadableFormat(),
                "CONCATENATION\n|_ANY_CHAR\n|_ANY_STRING\n");
    }

    /**
     * Test sprawdzający DigitOperator i DoNothingOperator.
     */
    public final void testToString3() throws Exception {
        List<RegexpOperatorTree> subtrees = new ArrayList<RegexpOperatorTree>();
        RegexpOperator root = new AlternativeOperator();
        RegexpOperator s1 = new DigitOperator();
        RegexpOperator s2 = new NoDigitOperator();
        RegexpOperatorTree tree0 = new RegexpOperatorTree(s1, subtrees);
        RegexpOperatorTree tree1 = new RegexpOperatorTree(s2, subtrees);
        subtrees.add(tree0);
        subtrees.add(tree1);
        RegexpOperatorTree tree = new RegexpOperatorTree(root, subtrees);
        assertEquals(tree.getHumanReadableFormat(),
                "ALTERNATIVE\n|_DIGIT\n|_NO_DIGIT\n");
    }

    /**
     * Test sprawdzający FixedNumberOfOccurrencesOperator, NewLineOperator i
     * FixedNumberOfOccurrencesOperator.
     */
    public final void testToString4() throws Exception {
        List<RegexpOperatorTree> subtrees = new ArrayList<RegexpOperatorTree>();
        List<RegexpOperatorTree> subtrees2 = new ArrayList<RegexpOperatorTree>();
        RegexpOperator s1 = new NewLineOperator();
        RegexpOperator s2 = new FixedNumberOfOccurrencesOperator(5);
        RegexpOperator s3 = new NumericalRangeOperator(4, 7);
        RegexpOperator root = new AlternativeOperator();
        RegexpOperatorTree tree0 = new RegexpOperatorTree(s1, subtrees);
        subtrees.add(tree0);
        RegexpOperatorTree tree1 = new RegexpOperatorTree(s2, subtrees);
        RegexpOperatorTree tree2 = new RegexpOperatorTree(s3, subtrees2);
        subtrees2.add(tree1);
        subtrees2.add(tree2);
        RegexpOperatorTree tree = new RegexpOperatorTree(root, subtrees2);
        assertEquals(tree.getHumanReadableFormat(),
                "ALTERNATIVE\n|_FIXED_5_TIMES\n|  |_NEW_LINE\n|_NUMERICAL_FROM_4_TO_7\n");
    }

    /**
     * Test sprawdzający KleeneStarOperator, OptionalityOperator, WhitespaceOperator,
     * RangeNumberOfOccurrencesOperator i TabOperator.
     */
    public final void testToString5() throws Exception {
        List<RegexpOperatorTree> subtrees = new ArrayList<RegexpOperatorTree>();
        List<RegexpOperatorTree> subtrees1 = new ArrayList<RegexpOperatorTree>();
        List<RegexpOperatorTree> subtrees2 = new ArrayList<RegexpOperatorTree>();
        List<RegexpOperatorTree> subtrees3 = new ArrayList<RegexpOperatorTree>();
        RegexpOperator root = new AlternativeOperator();
        RegexpOperator s1 = new KleeneStarOperator();
        RegexpOperator s2 = new OptionalityOperator();
        RegexpOperator s3 = new WhitespaceOperator();
        RegexpOperator s4 = new RangeNumberOfOccurrencesOperator(4, 7);
        RegexpOperator s5 = new TabOperator();
        RegexpOperatorTree tree4 = new RegexpOperatorTree(s5, subtrees2);
        subtrees2.add(tree4);
        RegexpOperatorTree tree3 = new RegexpOperatorTree(s4, subtrees2);
        subtrees3.add(tree3);
        RegexpOperatorTree tree2 = new RegexpOperatorTree(s3, subtrees);
        subtrees.add(tree2);
        RegexpOperatorTree tree1 = new RegexpOperatorTree(s2, subtrees);
        subtrees1.add(tree1);
        RegexpOperatorTree tree0 = new RegexpOperatorTree(s1, subtrees1);
        subtrees3.add(tree0);
        RegexpOperatorTree tree = new RegexpOperatorTree(root, subtrees3);
        assertEquals(tree.getHumanReadableFormat(),
                "ALTERNATIVE\n|_RANGE_NUMBER_OF_OCCURRENCES_FROM_4_TO_7\n"
                + "|  |_TAB\n|_KLEENE_STAR\n|  |_OPTIONALITY\n|    |_WHITESPACE\n");
    }

    /**
     * Test sprawdzający AnyOrderOperator, AtLeastOneOperator,
     * AsciiCharacterClassOperator i DoNothingOperator.
     */
    public final void testToString6() throws Exception {
        List<RegexpOperatorTree> subtrees = new ArrayList<RegexpOperatorTree>();
        List<RegexpOperatorTree> subtrees3 = new ArrayList<RegexpOperatorTree>();
        RegexpOperator root = new AnyOrderOperator();
        RegexpOperator s1 = new AtLeastOneOperator();
        RegexpOperator s2 = new DoNothingOperator();
        RegexpOperator s3 = new AsciiCharacterClassOperator("alnum");
        RegexpOperatorTree tree3 = new RegexpOperatorTree(s3, subtrees3);
        subtrees3.add(tree3);
        RegexpOperatorTree tree2 = new RegexpOperatorTree(s1, subtrees3);
        subtrees.add(tree2);
        RegexpOperatorTree tree1 = new RegexpOperatorTree(s2, subtrees);
        subtrees.add(tree1);
        RegexpOperatorTree tree = new RegexpOperatorTree(root, subtrees);
        assertEquals(tree.getHumanReadableFormat(),
                "ANY_ORDER\n|_AT_LEAST_ONE\n"
                + "|  |_ASCII\n|_DO_NOTHING\n|  |_AT_LEAST_ONE\n|    |_ASCII\n");
    }
}
