package pl.edu.amu.wmi.daut.re;

import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NondeterministicAutomatonByThompsonApproach;
import pl.edu.amu.wmi.daut.base.State;
import pl.edu.amu.wmi.daut.base.CharTransitionLabel;
import pl.edu.amu.wmi.daut.base.EpsilonTransitionLabel;
import pl.edu.amu.wmi.daut.re.AnyOrderOperator.Factory;

import junit.framework.TestCase;
import java.util.ArrayList;

/**
 * Test klasy AnyOrderOperator.
 */
public class TestAnyOrderOperator extends TestCase {

    /**
     * Test konstruktora AnyOrderOperator.
     */
    public void testAnyOrderOperator() {
        AnyOrderOperator operator = new AnyOrderOperator();
    }

    /**
     * Test metody createAutomatonFromTwoAutomaton.
     */
    public final void testCreateAutomatonFromTwoAutomata() {

        AutomatonSpecification automaton1 = new NaiveAutomatonSpecification();
        State q0 = automaton1.addState();
        State q1 = automaton1.addState();
        automaton1.addTransition(q0, q1, new CharTransitionLabel('a'));
        automaton1.markAsInitial(q0);
        automaton1.markAsFinal(q1);

        AutomatonSpecification automaton2 = new NaiveAutomatonSpecification();
        State q2 = automaton2.addState();
        State q3 = automaton2.addState();
        automaton2.addTransition(q2, q3, new CharTransitionLabel('k'));
        automaton2.markAsInitial(q2);
        automaton2.markAsFinal(q3);

        AnyOrderOperator operator = new AnyOrderOperator();
        NondeterministicAutomatonByThompsonApproach result =
                new NondeterministicAutomatonByThompsonApproach(
                operator.createAutomatonFromTwoAutomata(automaton1, automaton2));

        assertTrue(result.accepts("ak"));
        assertTrue(result.accepts("ka"));
        assertFalse(result.accepts("a"));
        assertFalse(result.accepts("k"));
        assertFalse(result.accepts(""));

        AutomatonSpecification automaton3 = new NaiveAutomatonSpecification();
        State q4 = automaton3.addState();
        State q5 = automaton3.addState();
        automaton3.addTransition(q4, q5, new EpsilonTransitionLabel());
        automaton3.markAsInitial(q4);
        automaton3.markAsFinal(q5);

        AutomatonSpecification automaton4 = new NaiveAutomatonSpecification();
        State q6 = automaton2.addState();
        State q7 = automaton2.addState();
        automaton2.addTransition(q6, q7, new EpsilonTransitionLabel());
        automaton2.markAsInitial(q6);
        automaton2.markAsFinal(q7);

        AnyOrderOperator operator1 = new AnyOrderOperator();
        NondeterministicAutomatonByThompsonApproach result1 =
                new NondeterministicAutomatonByThompsonApproach(
                operator1.createAutomatonFromTwoAutomata(automaton3, automaton4));

        assertFalse(result1.accepts(""));
        assertFalse(result1.accepts(" "));
        assertFalse(result1.accepts("asd"));
        assertFalse(result1.accepts("epsilon"));
    }

    /**
     * Test fabryki.
     */
    public final void testFactory() {
        Factory factory = new Factory();
        ArrayList<String> params = new ArrayList<String>();
        assertEquals(factory.createOperator(params).getClass(),
                new AnyOrderOperator().getClass());
    }
}
