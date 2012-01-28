package pl.edu.amu.wmi.daut.re;

import java.util.ArrayList;
import junit.framework.TestCase;
import pl.edu.amu.wmi.daut.base.AutomatonByRecursion;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.re.CarriageReturnOperator.Factory;

/**
 *
 * Test klasy CarriageReturnOperator.
 */
public class TestCarriageReturnOperator extends TestCase {

    /**
     * Test metody CreateFixedAutomaton.
     */

    public void testCreateFixedAutomaton() {

        CarriageReturnOperator operator = new CarriageReturnOperator();
        AutomatonSpecification spec = operator.createFixedAutomaton();
        assertFalse(spec.isEmpty());
        assertFalse(spec.acceptEmptyWord());

        AutomatonByRecursion automaton = new AutomatonByRecursion(spec);
        assertTrue(automaton.accepts("\13"));
        assertFalse(automaton.accepts("a"));
        assertFalse(automaton.accepts("%"));
        assertFalse(automaton.accepts("\t"));
        assertFalse(automaton.accepts(" "));
        assertFalse(automaton.accepts(""));
        assertFalse(automaton.accepts("\n"));
    }

    /**
     * Test fabryki.
     */

    public void testFactory() {

        Factory factory = new Factory();
        RegexpOperator operator2 = factory.createOperator(new ArrayList<String>());
        int arity = operator2.arity();
        assertEquals(arity, 0);

    }
}
