package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;
import java.util.List;

/**
 * Przykładowe testy przykładowej klasy NaiveDeterministicAutomatonSpecification.
 */
public class TestNaiveDeterministicAutomatonSpecification extends TestCase {

    /**
     * Test prostego automatu.
     */
    public final void testNaiveDeterministicAutomaton() {
        NaiveDeterministicAutomatonSpecification automat =
                new NaiveDeterministicAutomatonSpecification();

        State s1 = automat.addState();
        State s2 = automat.addState();
        automat.addTransition(s1, s2, new CharTransitionLabel('a'));
        State s3 = automat.addState();
        automat.addTransition(s2, s3, new CharTransitionLabel('b'));
        State s4 = automat.addState();
        automat.addTransition(s3, s4, new CharTransitionLabel('c'));

        automat.markAsInitial(s1);
        automat.markAsFinal(s4);
        State s0 = automat.getInitialState();

        List<OutgoingTransition> s0Out = automat.allOutgoingTransitions(s0);
        List<State> states = automat.allStates();

        assertEquals(s0Out.size(), 1);
        assertEquals(states.size(), 4);
        assertFalse(automat.isFinal(s0));
        assertEquals(
                ((CharTransitionLabel) s0Out.get(0).getTransitionLabel()).getChar(), 'a');
        assertTrue(
                ((CharTransitionLabel) s0Out.get(0).getTransitionLabel()).canAcceptCharacter('a'));
        assertFalse(
                ((CharTransitionLabel) s0Out.get(0).getTransitionLabel()).canAcceptCharacter('b'));
        State r0 = automat.targetState(s2, 'b');
        assertSame(r0, s3);
        assertNotSame(r0, s1);
    }

    /**
     * Test metody targetState.
     */
    public final void testTargetState() {
        NaiveDeterministicAutomatonSpecification automat =
                new NaiveDeterministicAutomatonSpecification();

        State r1 = automat.addState();
        State r2 = automat.addState();
        automat.addTransition(r1, r2, new CharTransitionLabel('a'));
        State r3 = automat.addState();
        automat.addTransition(r2, r3, new CharTransitionLabel('b'));
        State r4 = automat.addState();
        automat.addTransition(r2, r4, new CharTransitionLabel('c'));
        State r5 = automat.addState();
        automat.addTransition(r2, r5, new CharTransitionLabel('d'));

        State test0 = automat.targetState(r2, 'b');
        State test1 = automat.targetState(r2, 'c');
        State test2 = automat.targetState(r2, 'd');
        State test3 = automat.targetState(r1, 'a');
        State test4 = automat.targetState(r5, 'a');

        assertSame(test0, r3);
        assertSame(test1, r4);
        assertSame(test2, r5);
        assertSame(test3, r2);
        assertNotSame(test0, r4);
        assertNotSame(test1, r5);
        assertNotSame(test4, r1);
    }

     /**
     * Test metody testUnmark.
     */
    public final void testUnmark() {
        final AutomatonSpecification spec = new NaiveAutomatonSpecification();

        //Test 1
        State q0a = spec.addState();
        State q1a = spec.addState();

        spec.addTransition(q1a, q0a, new CharTransitionLabel(' '));

        spec.markAsFinal(q1a);
        spec.markAsInitial(q0a);

        spec.unmarkAsFinalState(q1a);
        assertFalse(spec.isFinal(q1a));

        //test 2
        State q0b = spec.addState();
        State q1b = spec.addState();

        spec.addTransition(q1b, q0b, new CharTransitionLabel(' '));

        spec.markAsFinal(q0b);
        spec.markAsInitial(q1b);

        spec.unmarkAsFinalState(q1b);
        assertTrue(spec.isFinal(q0b));

        //test 3
        State q0c = spec.addState();
        State q1c = spec.addState();
        State q2c = spec.addState();
        State q3c = spec.addState();
        State q4c = spec.addState();
        State q5c = spec.addState();

        spec.addTransition(q0c, q2c, new CharTransitionLabel('a'));
        spec.addTransition(q3c, q4c, new CharTransitionLabel('a'));
        spec.addTransition(q1c, q5c, new CharTransitionLabel('a'));


        spec.markAsFinal(q5c);
        spec.markAsInitial(q0c);

        spec.unmarkAsFinalState(q5c);
        assertFalse(spec.isFinal(q0c));

        spec.markAsFinal(q1c);
        spec.markAsInitial(q3c);

        spec.unmarkAsFinalState(q3c);
        assertTrue(spec.isFinal(q1c));
    }
}
