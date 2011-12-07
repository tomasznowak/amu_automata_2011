package pl.edu.amu.wmi.daut.base;

import java.util.HashSet;
import junit.framework.TestCase;

/**
 *
 * @author szaku.
 */
public class TestEffectiveDeterministicAutomaton extends TestCase {

    /**
     * prosty test automatu deterministycznego.
     */
    public final void testSimpleEffectiveDeterministicAutomaton() {
        /**
         * st-> simple test.
         */
        DeterministicAutomatonSpecification st = new EffectiveDeterministicAutomaton();
        State q1 = st.addState();
        State q2 = st.addState();
        st.addTransition(q1, q2, new CharTransitionLabel('a'));
        st.addLoop(q2, new CharTransitionLabel('a'));
        st.addLoop(q1, new CharTransitionLabel('b'));
        st.markAsInitial(q1);
        st.markAsFinal(q2);
        /**
         * sat -> simple automaton test.
         */
        DeterministicAutomaton sat = new DeterministicAutomaton(st);
        assertTrue(sat.accepts("a"));
        assertFalse(sat.accepts("b"));
        assertFalse(sat.accepts(""));
        assertFalse(sat.accepts("abx"));
        assertTrue(sat.accepts("aaaa"));
        assertTrue(sat.accepts("baaaa"));
        String s = new String();
        for (int i = 1; i < 1000; i++) {
            s = s + 'a';
        }
        assertTrue(sat.accepts(s));
        String a = new String();
        for (int i = 1; i < 1000; i++) {
            a = a + "ab";
        }
        assertFalse(sat.accepts(a));
    }

    /**
     * automat posiada stan, ktory jest zarowno poczatkowym i akceptujacym.
     */
    public final void testOneInitialFinalState() {
        /**
         * os-> one state.
         */
        DeterministicAutomatonSpecification os = new EffectiveDeterministicAutomaton();
        State q1 = os.addState();
        State q2 = os.addState();
        os.addTransition(q1, q2, new CharTransitionLabel('a'));
        os.addTransition(q1, q2, new CharTransitionLabel('b'));
        os.addTransition(q2, q1, new CharTransitionLabel('a'));
        os.addLoop(q2, new CharTransitionLabel('b'));
        os.markAsInitial(q1);
        os.markAsFinal(q1);
        /**
         * ost -> one state test.
         */
        DeterministicAutomaton ost = new DeterministicAutomaton(os);
        assertTrue(ost.accepts("aa"));
        assertFalse(ost.accepts("aaa"));
        assertFalse(ost.accepts("a"));
        assertFalse(ost.accepts("baa"));
    }

    /**
     * automat z wykladu, sprawdza czy a i b sa parzyste.
     */
    public final void testEffectiveDeterministicAutomatonParityCheck() {
        /**
         * pch-> parity check.
         */
        DeterministicAutomatonSpecification pch = new EffectiveDeterministicAutomaton();
        State qpp = pch.addState();
        State qnp = pch.addState();
        State qnn = pch.addState();
        State qpn = pch.addState();
        pch.addTransition(qpp, qnp, new CharTransitionLabel('a'));
        pch.addTransition(qnp, qpp, new CharTransitionLabel('a'));
        pch.addTransition(qnp, qnn, new CharTransitionLabel('b'));
        pch.addTransition(qnn, qnp, new CharTransitionLabel('b'));
        pch.addTransition(qnn, qpn, new CharTransitionLabel('a'));
        pch.addTransition(qpn, qnn, new CharTransitionLabel('a'));
        pch.addTransition(qpp, qpn, new CharTransitionLabel('b'));
        pch.addTransition(qpn, qpp, new CharTransitionLabel('b'));
        pch.markAsInitial(qpp);
        pch.markAsFinal(qpp);
        /**
         * pct-> parity check test.
         */
        DeterministicAutomaton pct = new DeterministicAutomaton(pch);
        assertTrue(pct.accepts("aabb"));
        assertFalse(pct.accepts("aab"));
        assertTrue(pct.accepts("aa"));
        assertTrue(pct.accepts("bb"));
        assertFalse(pct.accepts("abb"));
        assertFalse(pct.accepts("vabb"));
        assertFalse(pct.accepts("aabbh"));
    }

    /**
     * automat z wykladu, sprawdza czy a i b sa parzyste.
     */
    public final void testThrowableEffectiveDeterministicAutomatons() {
        EffectiveDeterministicAutomaton auto = new EffectiveDeterministicAutomaton();
        State q0 = auto.addState();
        auto.markAsInitial(q0);
        try {
            State qNull = null;
            auto.isFinal(qNull);
            fail();
        } catch (NullPointerException e) {
            assertTrue(true);
        }
        try {
            NaiveDeterministicAutomatonSpecification wrongOne =
                    new NaiveDeterministicAutomatonSpecification();
            State qIllegal = wrongOne.addState();
            auto.isFinal(qIllegal);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
        auto.addLoop(q0, new EmptyTransitionLabel());
        State q1 = auto.addState();
        auto.addTransition(q0, q1, new CharRangeTransitionLabel('a', 'c'));
        HashSet mySet = new HashSet();
        mySet.add('e');
        mySet.add('g');
        auto.addTransition(q0, q1, new CharSetTransitionLabel(mySet));
        State q2 = auto.addState();
        auto.addTransition(q1, q2, new AnyTransitionLabel());
        try {
            auto.addTransition(q1, q0, new EpsilonTransitionLabel());
            fail();
        } catch (UnsupportedOperationException e) {
            assertTrue(true);
        }
        TransitionLabel epsilonik = new EpsilonTransitionLabel();
        auto.addTransition(q2, q0, epsilonik);
        auto.markAsFinal(q2);
        assertTrue(auto.isFinal(q2));
        auto.unmarkAsFinalState(q2);
        assertFalse(auto.isFinal(q2));
        assertEquals(auto.allStates().size(), 3);
        assertEquals(auto.allOutgoingTransitions(q2).size(), 1);
        assertTrue(((EffectiveDeterministicAutomaton.MyState) q2).hasEpsilonTransition());
        assertTrue(((EffectiveDeterministicAutomaton.MyState) q2).getEpsilonTargetState()
                .equals((EffectiveDeterministicAutomaton.MyState) q0));
    }
}

