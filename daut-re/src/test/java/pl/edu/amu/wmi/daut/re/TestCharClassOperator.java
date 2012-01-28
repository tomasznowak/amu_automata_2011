package pl.edu.amu.wmi.daut.re;

import java.util.ArrayList;
import pl.edu.amu.wmi.daut.base.AutomatonByRecursion;
import junit.framework.TestCase;

/**
 * Klasa, testuje klasę CharClassOperator.
 */
public class TestCharClassOperator extends TestCase {

    /**
     * Konstruuje przykładowy automat, a następnie go testuje.
     */
    public void testAutomatonFirst() {

        AutomatonByRecursion tmp = new AutomatonByRecursion(
            new CharClassOperator("1-3").createFixedAutomaton());

        assertTrue(tmp.accepts("2"));
        assertFalse(tmp.accepts("4"));
        assertTrue(tmp.accepts("3"));
    }

    /**
     * Konstruuje przykładowy automat, a następnie go testuje.
     */
    public void testAutomatonSecond() {

        AutomatonByRecursion tmp = new AutomatonByRecursion(
            new CharClassOperator("1-3a-c-").createFixedAutomaton());

        assertTrue(tmp.accepts("1"));
        assertFalse(tmp.accepts("4"));
        assertTrue(tmp.accepts("-"));
        assertFalse(tmp.accepts("d"));
        assertFalse(tmp.accepts("ą"));
        assertTrue(tmp.accepts("b"));
    }

    /**
     * Test fabryki operatora.
     */
    public void testFactory() {

        RegexpOperatorFactory factory = new CharClassOperator.Factory();
        ArrayList<String> params = new ArrayList<String>();
        params.add("1-3");
        assertEquals(0, factory.arity());
        assertEquals(1, factory.numberOfParams());
        assertEquals(factory.createOperator(params).getClass(),
            new CharClassOperator(params.get(0)).getClass());
    }
}
