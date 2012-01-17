package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;

/**
 *
 * @author szaku.
 */
public class TestDeterministicAutomaton extends TestCase {

    /**
     * prosty test automatu deterministycznego.
     */
    public final void testSimpleDeterministicAutomaton() {
        /**
         * st-> simple test.
         */
        DeterministicAutomatonSpecification st = new NaiveDeterministicAutomatonSpecification();
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
        DeterministicAutomatonSpecification os = new NaiveDeterministicAutomatonSpecification();
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
    public final void testDeterministicAutomatonParityCheck() {
        /**
         * pch-> parity check.
         */
        DeterministicAutomatonSpecification pch = new NaiveDeterministicAutomatonSpecification();
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
     * test metody minimalizujacej automat.
     */
    public final void testMakeMinimal() {

        DeterministicAutomatonSpecification automaton =
                new NaiveDeterministicAutomatonSpecification();
        DeterministicAutomatonSpecification automaton2 =
                new NaiveDeterministicAutomatonSpecification();
        DeterministicAutomatonSpecification automaton4 =
                new NaiveDeterministicAutomatonSpecification();
        DeterministicAutomatonSpecification automaton5 =
                new NaiveDeterministicAutomatonSpecification();
        DeterministicAutomatonSpecification automaton7 =
                new NaiveDeterministicAutomatonSpecification();
        DeterministicAutomatonSpecification automaton8 =
                new NaiveDeterministicAutomatonSpecification();

        //---------------------------------------------------------
        State state1 = automaton.addState();
        State state2 = automaton.addState();
        State state3 = automaton.addState();
        State state4 = automaton.addState();
        State state5 = automaton.addState();
        State state6 = automaton.addState();

        automaton.markAsInitial(state1);
        automaton.markAsFinal(state5);
        automaton.markAsFinal(state4);
        automaton.addTransition(state1, state2, new CharTransitionLabel('a'));
        automaton.addTransition(state1, state3, new CharTransitionLabel('b'));
        automaton.addTransition(state2, state4, new CharTransitionLabel('a'));
        automaton.addTransition(state3, state5, new CharTransitionLabel('a'));

        automaton2 = automaton.makeMinimal();
        int states = automaton2.countStates();

        assertEquals(4, states);

        AutomatonByRecursion automaton3 = new AutomatonByRecursion(automaton2);

        assertTrue(automaton3.accepts("aa"));
        assertTrue(automaton3.accepts("ba"));
        assertFalse(automaton3.accepts("ab"));


        //---------------------------------------------------------
        State states1 = automaton4.addState();
        State states2 = automaton4.addState();
        State states3 = automaton4.addState();
        State states4 = automaton4.addState();


        automaton4.markAsInitial(states1);
        automaton4.markAsFinal(states2);
        automaton4.markAsFinal(states3);

        automaton4.addTransition(states1, states2, new CharTransitionLabel('a'));
        automaton4.addTransition(states1, states4, new CharTransitionLabel('b'));
        automaton4.addTransition(states2, states3, new CharTransitionLabel('a'));
        automaton4.addTransition(states2, states4, new CharTransitionLabel('b'));
        automaton4.addTransition(states3, states4, new CharTransitionLabel('b'));
        automaton4.addLoop(states3, new CharTransitionLabel('a'));
        automaton4.addTransition(states4, states1, new CharTransitionLabel('a'));
        automaton4.addLoop(states4, new CharTransitionLabel('b'));

        automaton5 = automaton4.makeMinimal();

        AutomatonByRecursion automaton6 = new AutomatonByRecursion(automaton5);


        assertTrue(automaton6.accepts("bbbbbbbbbaaaa"));
        assertTrue(automaton6.accepts("aaaaaaaa"));
        assertTrue(automaton6.accepts("bbbaa"));
        assertTrue(automaton6.accepts("babababababaa"));
        assertEquals(automaton5.countStates(), 3);
        //---------------------------------------------------------

        State statez1 = automaton7.addState();
        State statez2 = automaton7.addState();
        State statez3 = automaton7.addState();
        State statez4 = automaton7.addState();
        State statez5 = automaton7.addState();
        State statez6 = automaton7.addState();

        automaton7.markAsInitial(statez1);
        automaton7.markAsFinal(statez4);

        automaton7.addTransition(statez1, statez2, new CharTransitionLabel('a'));
        automaton7.addTransition(statez1, statez5, new CharTransitionLabel('b'));
        automaton7.addTransition(statez2, statez3, new CharTransitionLabel('b'));
        automaton7.addLoop(statez2, new CharTransitionLabel('a'));
        automaton7.addTransition(statez3, statez4, new CharTransitionLabel('a'));
        automaton7.addTransition(statez3, statez5, new CharTransitionLabel('b'));
        automaton7.addLoop(statez4, new CharTransitionLabel('a'));
        automaton7.addLoop(statez4, new CharTransitionLabel('b'));
        automaton7.addTransition(statez5, statez2, new CharTransitionLabel('a'));
        //automaton7.addLoop(statez5, new CharTransitionLabel('b'));
        automaton7.addTransition(statez5, statez6, new CharTransitionLabel('b'));
        automaton7.addTransition(statez6, statez2, new CharTransitionLabel('a'));
        automaton7.addLoop(statez6, new CharTransitionLabel('b'));

        automaton8 = automaton7.makeMinimal();

        AutomatonByRecursion automaton9 = new AutomatonByRecursion(automaton8);



        assertTrue(automaton9.accepts("aba"));
        assertTrue(automaton9.accepts("aaaaaababbbbbbbabbb"));
        assertTrue(automaton9.accepts("baaba"));
        assertTrue(automaton9.accepts("ababbb"));
        assertEquals(4, automaton8.countStates());
    }
    /**
     * Test na automacie akceptującym język skladajacy sie z 0 i 1,
     * gdzie liczba zer jest podzielna przez 5.
     */
    public final void testAutomatonAcceptingFiveZeros() {
        DeterministicAutomatonSpecification spec =
                        new NaiveDeterministicAutomatonSpecification();
        State q0 = spec.addState();
        State q1 = spec.addState();
        State q2 = spec.addState();
        State q3 = spec.addState();
        State q4 = spec.addState();
        State q5 = spec.addState();
        
        spec.addLoop(q0, new CharTransitionLabel('1'));
        spec.addTransition(q0, q1, new CharTransitionLabel('0'));
        spec.addTransition(q1, q2, new CharTransitionLabel('0'));
        spec.addTransition(q2, q3, new CharTransitionLabel('0'));
        spec.addTransition(q3, q4, new CharTransitionLabel('0'));
        spec.addTransition(q4, q5, new CharTransitionLabel('0'));
        spec.addTransition(q5, q1, new CharTransitionLabel('0'));
        spec.addLoop(q1, new CharTransitionLabel('1'));
        spec.addLoop(q2, new CharTransitionLabel('1'));
        spec.addLoop(q3, new CharTransitionLabel('1'));
        spec.addLoop(q4, new CharTransitionLabel('1'));
        spec.addLoop(q5, new CharTransitionLabel('1'));
        
        
        spec.markAsInitial(q0);
        spec.markAsFinal(q5);
        
        AutomatonByRecursion automaton = new AutomatonByRecursion(spec);
        assertTrue(automaton.accepts("00000"));
        assertTrue(automaton.accepts("100000"));
        assertTrue(automaton.accepts("000001"));
        assertTrue(automaton.accepts("010101001"));
        assertTrue(automaton.accepts("010101001010101001010101001"));
        assertFalse(automaton.accepts("0101010012"));
        assertFalse(automaton.accepts("000000"));
        assertFalse(automaton.accepts("00011101011111000"));
        assertFalse(automaton.accepts(""));
        assertFalse(automaton.accepts("bdaasrweewrgsdf"));
        assertFalse(automaton.accepts("$@%%@#$@#!@"));
        assertFalse(automaton.accepts("章"));
    }
}

