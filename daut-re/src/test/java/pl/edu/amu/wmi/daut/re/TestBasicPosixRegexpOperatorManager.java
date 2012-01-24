package pl.edu.amu.wmi.daut.re;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import junit.framework.TestCase;

/**
 * Test klasy BasicPosixRegexpOperatorManager.
 * Testuje wszystkie operatory wyrazen regularnych POSIX na metodach klasy RegexpOperatorManager.
 */
public class TestBasicPosixRegexpOperatorManager extends TestCase {

    /**
     * Test metody GetFactory.
     */
    public final void testGetFactory() {

        RegexpOperatorManager manager = new BasicPosixRegexpOperatorManager();

        assertNotNull(manager);
        assertNull(manager.getFactory("^"));
        assertNotNull(manager.getFactory("*"));
        assertNotNull(manager.getFactory("."));
        assertNotNull(manager.getFactory("\\(\\)"));
        assertNotNull(manager.getFactory("\\{m,n\\}"));
        assertNotNull(manager.getFactory("\\{m\\}"));
    }

    /**
     * Test metody addOperator z priorytetami.
     */
    public final void testAddOperatorWithPiority() {

        RegexpOperatorManager manager = new BasicPosixRegexpOperatorManager();

        assertEquals(3, manager.getPriority("*"));
        assertEquals(4, manager.getPriority("."));
        assertEquals(4, manager.getPriority("\\(\\)"));
        assertEquals(3, manager.getPriority("\\{m,n\\}"));
        assertEquals(3, manager.getPriority("\\{m\\}"));
    }

    /**
     * Test metody getSeparators.
     */
    public final void testGetSeparators() {

        RegexpOperatorManager manager = new BasicPosixRegexpOperatorManager();

        List<String> kleene = new ArrayList<String>();
        kleene.add("");
        kleene.add("*");
        assertEquals(kleene, manager.getSeparators("*"));

        List<String> anyChar = new ArrayList<String>();
        anyChar.add(".");
        assertEquals(anyChar, manager.getSeparators("."));

        List<String> doNothing = new ArrayList<String>();
        doNothing.add("\\(");
        doNothing.add("\\)");
        assertEquals(doNothing, manager.getSeparators("\\(\\)"));

        List<String> rangeNumber = new ArrayList<String>();
        rangeNumber.add("");
        rangeNumber.add("\\{");
        rangeNumber.add(",");
        rangeNumber.add("\\}");
        assertEquals(rangeNumber, manager.getSeparators("\\{m,n\\}"));

        List<String> fixedNumber = new ArrayList<String>();
        fixedNumber.add("");
        fixedNumber.add("\\{");
        fixedNumber.add("\\}");
        assertEquals(fixedNumber, manager.getSeparators("\\{m\\}"));
    }

    /**
     * Test metody getOperatorsForStringPrefix.
     */
    public final void testGetOperatorsForStringPrefix() {

        RegexpOperatorManager manager = new BasicPosixRegexpOperatorManager();

        List<String> string = new ArrayList<String>();

        string = manager.getOperatorsForStringPrefix("");
        assertEquals(Arrays.<String>asList("*", "*", "\\{m,n\\}", "\\{m\\}"), string);

        string = manager.getOperatorsForStringPrefix(".");
        assertEquals(Arrays.<String>asList(".", "."), string);

        string = manager.getOperatorsForStringPrefix("\\(");
        assertEquals(Arrays.<String>asList("\\(\\)"), string);

        string = manager.getOperatorsForStringPrefix("\\{m\\}\\{m,n\\}");
        assertEquals(Arrays.<String>asList("*", "*", "\\{m,n\\}", "\\{m\\}"), string);
    }
}
