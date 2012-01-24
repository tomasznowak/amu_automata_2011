package pl.edu.amu.wmi.daut.re;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import junit.framework.TestCase;

/**
 * @author dyskograf
 * Test klasy ExtendedPosixOperatorManager.
 */
public class TestExtendedPosixRegexpOperatorManager extends TestCase {

    /**
     * Test metody getPriority.
     */
    public final void testGetPriority() {

        ExtendedPosixRegexpOperatorManager manager = new ExtendedPosixRegexpOperatorManager();

        assertTrue(manager.getPriority("*") < manager.getPriority("()"));
        assertTrue(manager.getPriority("+") < manager.getPriority("()"));
        assertTrue(manager.getPriority("?") < manager.getPriority("()"));
        assertTrue(manager.getPriority("*") == manager.getPriority("+"));
    }

    /**
     * Test metody getFactory.
     */
    public final void testGetFactory() {

        ExtendedPosixRegexpOperatorManager manager = new ExtendedPosixRegexpOperatorManager();

        assertNotNull(manager);
        assertNotNull(manager.getFactory("*"));
        assertNotNull(manager.getFactory("+"));
        assertNotNull(manager.getFactory("?"));
        assertNotNull(manager.getFactory("."));
        assertNotNull(manager.getFactory("()"));
        assertNotNull(manager.getFactory("[::]"));
        assertNotNull(manager.getFactory("{m}"));

    }

    /**
     * Test metody getOperatorsForStringPrefix.
     */
    public final void testGetOperatorsForStringPrefix() {

        ExtendedPosixRegexpOperatorManager manager = new ExtendedPosixRegexpOperatorManager();
        List<String> string = new ArrayList<String>();

        string = manager.getOperatorsForStringPrefix("");
        assertEquals(Arrays.<String>asList("*", "+", "?", "{m}", "{m,n}", "|"), string);
        string = manager.getOperatorsForStringPrefix("()");
        assertEquals(Arrays.<String>asList("()"), string);
        string = manager.getOperatorsForStringPrefix("ss*");
        assertEquals(Arrays.<String>asList("*", "+", "?", "{m}", "{m,n}", "|"), string);
        string = manager.getOperatorsForStringPrefix(".");
        assertEquals(Arrays.<String>asList("."), string);
        string = manager.getOperatorsForStringPrefix("[:blabla:]");
        assertEquals(Arrays.<String>asList("[::]"), string);

    }

}
