package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;

/**
 * Test klasy NondeterministicAutomatonByThompsonApproach.
 */
public class TestNondeterministicAutomatonByThompsonApproach extends TestCase {

    /**
     * Definicja metody testAutomaton.
     */
    public final void testAutomaton() {
        final AutomatonSpecification spec = new NaiveAutomatonSpecification();

        State q0 = spec.addState();
        State q1 = spec.addState();
        State q2 = spec.addState();
        State q3 = spec.addState();
        State q4 = spec.addState();
        State q5 = spec.addState();
        State q6 = spec.addState();
        State q7 = spec.addState();

        spec.addTransition(q0, q1, new CharTransitionLabel('N'));
        spec.addTransition(q1, q2, new CharTransitionLabel('O'));
        spec.addTransition(q2, q3, new CharTransitionLabel('O'));
        spec.addTransition(q3, q4, new CharTransitionLabel('O'));
        spec.addTransition(q4, q5, new CharTransitionLabel('O'));
        spec.addTransition(q5, q6, new CharTransitionLabel('O'));
        spec.addTransition(q6, q7, new CharTransitionLabel('!'));
        spec.addLoop(q6, new CharTransitionLabel('O'));
        spec.addLoop(q7, new CharTransitionLabel('!'));

        spec.markAsInitial(q0);
        spec.markAsFinal(q7);

        final NondeterministicAutomatonByThompsonApproach automaton =
                new NondeterministicAutomatonByThompsonApproach(spec);

        assertFalse(automaton.accepts("N"));
        assertFalse(automaton.accepts("NO"));
        assertFalse(automaton.accepts("NOO"));
        assertFalse(automaton.accepts("NOOO"));
        assertFalse(automaton.accepts("NOOOO"));
        assertFalse(automaton.accepts("NOOOOO"));
        assertFalse(automaton.accepts("NOOOO!"));
        assertTrue(automaton.accepts("NOOOOO!"));
        assertTrue(automaton.accepts("NOOOOOO!"));
        assertTrue(automaton.accepts("NOOOOO!!"));
        assertTrue(automaton.accepts("NOOOOOOO!!!"));
    }
}
