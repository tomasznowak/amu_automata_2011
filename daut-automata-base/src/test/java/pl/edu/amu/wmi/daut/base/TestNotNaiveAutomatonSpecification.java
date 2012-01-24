package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;
import java.util.List;

/**
 * Przykładowe testy przykładowej klasy NaiveAutomatonSpecification.
 */
public class TestNotNaiveAutomatonSpecification extends TestCase {

    /**
     * Tworzymy prosty 3 stanowy automat.
     * Testujemy na nim wszystkie polecenia podobnie
     * jak na NaiveAutomaton
     */
    public final void testSimpleAutomaton() {
        NotNaiveAutomatonSpecification spec = new NotNaiveAutomatonSpecification();

        State s0 = spec.addState();
        State s1 = spec.addState();
        spec.addTransition(s0, s1, new CharTransitionLabel('a'));
        State s2 = spec.addState();
        spec.addTransition(s0, s2, new CharTransitionLabel('b'));
        spec.addTransition(s1, s2, new CharTransitionLabel('c'));

        spec.markAsInitial(s0);
        spec.markAsFinal(s2);

        State r0 = spec.getInitialState();

        assertFalse(spec.isFinal(r0));

        List<OutgoingTransition> r0Outs = spec.allOutgoingTransitions(r0);

        assertEquals(r0Outs.size(), 2);

        State r1;
        State r2;

        if (((CharTransitionLabel) r0Outs.get(0).getTransitionLabel()).getChar() == 'a') {
            r1 = r0Outs.get(0).getTargetState();
            r2 = r0Outs.get(1).getTargetState();
            assertEquals(((CharTransitionLabel)
                    r0Outs.get(1).getTransitionLabel()).getChar(), 'b');
            assertTrue(
                    ((CharTransitionLabel)
                    r0Outs.get(1).getTransitionLabel()).canAcceptCharacter('b'));
            assertFalse(
                    ((CharTransitionLabel)
                    r0Outs.get(1).getTransitionLabel()).canAcceptCharacter('c'));
            assertFalse(((CharTransitionLabel) r0Outs.get(1).getTransitionLabel()).canBeEpsilon());
        } else {
            r1 = r0Outs.get(1).getTargetState();
            r2 = r0Outs.get(0).getTargetState();
            assertEquals(((CharTransitionLabel) r0Outs.get(0).getTransitionLabel()).getChar(), 'b');
        }

        assertFalse(spec.isFinal(r1));
        assertTrue(spec.isFinal(r2));
        assertSame(r0, spec.getInitialState());
        assertNotSame(r0, r1);
        assertNotSame(r0, r2);
        assertNotSame(r1, r2);

        List<State> states = spec.allStates();

        assertEquals(states.size(), 3);

        State x0 = spec.getInitialState();


        List<OutgoingTransition> x0Outs = spec.getOutReturnOutgoingTransitions(x0);

        assertEquals(x0Outs.size(), 2);

    }
    /**
     * Test metody dopełniającej automat na automacie, któremu brakuje jednego przejścia.
     */
    public final void testMakeFullAlmostFull() {
        NotNaiveAutomatonSpecification spec = new NotNaiveAutomatonSpecification();

        State s0 = spec.addState();
        State s1 = spec.addState();
        State s2 = spec.addState();

        spec.addTransition(s0, s1, new CharTransitionLabel('a'));
        spec.addLoop(s1, new CharTransitionLabel('a'));
        spec.addLoop(s1, new CharTransitionLabel('b'));
        spec.addLoop(s2, new CharTransitionLabel('a'));
        spec.addLoop(s2, new CharTransitionLabel('b'));

        spec.makeFull("ab");
        assertEquals(spec.countStates(), 4);
        assertTrue(spec.isFull("ab"));
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
