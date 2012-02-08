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
    public final void testMakeMinimal1() {

        DeterministicAutomatonSpecification automaton =
                new NaiveDeterministicAutomatonSpecification();
        DeterministicAutomatonSpecification automaton2 =
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

        automaton2.makeMinimal(automaton, "ab");
        int states = automaton2.countStates();

       assertEquals(4, states);

        AutomatonByRecursion automaton3 = new AutomatonByRecursion(automaton2);

        assertTrue(automaton3.accepts("aa"));
        assertTrue(automaton3.accepts("ba"));
        assertFalse(automaton3.accepts("ab"));
    }

    /**
     * test2 metody minimalizujacej automat.
     */
    public final void testMakeMinimal2() {
        DeterministicAutomatonSpecification automaton1 =
                new NaiveDeterministicAutomatonSpecification();
        DeterministicAutomatonSpecification automaton2 =
                new NaiveDeterministicAutomatonSpecification();

        //---------------------------------------------------------
        State states1 = automaton1.addState();
        State states2 = automaton1.addState();
        State states3 = automaton1.addState();
        State states4 = automaton1.addState();


        automaton1.markAsInitial(states1);
        automaton1.markAsFinal(states2);
        automaton1.markAsFinal(states3);

        automaton1.addTransition(states1, states2, new CharTransitionLabel('a'));
        automaton1.addTransition(states1, states4, new CharTransitionLabel('b'));
        automaton1.addTransition(states2, states3, new CharTransitionLabel('a'));
        automaton1.addTransition(states2, states4, new CharTransitionLabel('b'));
        automaton1.addTransition(states3, states4, new CharTransitionLabel('b'));
        automaton1.addLoop(states3, new CharTransitionLabel('a'));
        automaton1.addTransition(states4, states1, new CharTransitionLabel('a'));
        automaton1.addLoop(states4, new CharTransitionLabel('b'));

        automaton2.makeMinimal(automaton1, "ab");

        AutomatonByRecursion automaton3 = new AutomatonByRecursion(automaton2);



        assertTrue(automaton3.accepts("bbbbbbbbbaaa"));
        assertTrue(automaton3.accepts("aaaaaaaaaaaaaaaaa"));
        assertTrue(automaton3.accepts("bbbaa"));
        assertTrue(automaton3.accepts("babababababaaaaaaaaaaaaaaaaaaaaa"));
        assertEquals(automaton2.countStates(), 3);
    }

    /**
     * test3 metody minimalizujacej automat.
     */
    public final void testMakeMinimal3() {
        DeterministicAutomatonSpecification automaton1 =
                new NaiveDeterministicAutomatonSpecification();
        DeterministicAutomatonSpecification automaton2 =
                new NaiveDeterministicAutomatonSpecification();
        //---------------------------------------------------------

        State statez1 = automaton1.addState();
        State statez2 = automaton1.addState();
        State statez3 = automaton1.addState();
        State statez7 = automaton1.addState();
        State statez4 = automaton1.addState();
        State statez5 = automaton1.addState();
        State statez6 = automaton1.addState();


        automaton1.markAsInitial(statez1);
        automaton1.markAsFinal(statez4);

        automaton1.addTransition(statez1, statez2, new CharTransitionLabel('a'));
        automaton1.addTransition(statez1, statez5, new CharTransitionLabel('b'));
        automaton1.addTransition(statez2, statez3, new CharTransitionLabel('b'));
        automaton1.addLoop(statez2, new CharTransitionLabel('a'));
        automaton1.addTransition(statez3, statez4, new CharTransitionLabel('a'));
        automaton1.addTransition(statez3, statez5, new CharTransitionLabel('b'));
        automaton1.addLoop(statez4, new CharTransitionLabel('a'));
        automaton1.addLoop(statez4, new CharTransitionLabel('b'));
        automaton1.addTransition(statez5, statez2, new CharTransitionLabel('a'));
        automaton1.addTransition(statez5, statez6, new CharTransitionLabel('b'));
        automaton1.addTransition(statez6, statez2, new CharTransitionLabel('a'));
        automaton1.addLoop(statez6, new CharTransitionLabel('b'));
        automaton1.addTransition(statez6, statez7, new CharTransitionLabel('b'));
        automaton1.addTransition(statez7, statez2, new CharTransitionLabel('a'));
        automaton1.addLoop(statez7, new CharTransitionLabel('b'));

        automaton2.makeMinimal(automaton1, "ab");

        AutomatonByRecursion automaton3 = new AutomatonByRecursion(automaton2);



        assertTrue(automaton3.accepts("aba"));
        assertTrue(automaton3.accepts("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbaba"));
        assertTrue(automaton3.accepts("aaaaaababbbbbbbabbb"));
        assertTrue(automaton3.accepts("baaba"));
        assertTrue(automaton3.accepts("ababbb"));
        assertFalse(automaton3.accepts("aaaaaaaaaaaaaa"));
        assertFalse(automaton3.accepts("bbbb"));
        assertFalse(automaton3.accepts("aaaab"));
        assertEquals(4, automaton2.countStates());
    }
    /**
     * Test minimalizacji na prostym automacie 4-stanowym ("diament").
     */
    public final void testMakeMinimalOnDiamond() {

        DeterministicAutomatonSpecification spec = new NaiveDeterministicAutomatonSpecification();
        DeterministicAutomatonSpecification spec2 = new NaiveDeterministicAutomatonSpecification();

        State q0 = spec.addState();
        State q1 = spec.addState();
        State q2 = spec.addState();
        State q3 = spec.addState();

        spec.markAsInitial(q0);
        spec.markAsFinal(q3);

        spec.addTransition(q0, q1, new CharTransitionLabel('a'));
        spec.addTransition(q0, q2, new CharTransitionLabel('b'));
        spec.addTransition(q1, q3, new CharTransitionLabel('c'));
        spec.addTransition(q2, q3, new CharTransitionLabel('d'));


        // dla pewności sprawdzamy jeszcze pierwotny automat
        AutomatonByRecursion originalAutomaton = new AutomatonByRecursion(spec);
        assertTrue(originalAutomaton.accepts("ac"));
        assertTrue(originalAutomaton.accepts("bd"));
        assertFalse(originalAutomaton.accepts("ad"));
        assertFalse(originalAutomaton.accepts("bc"));

        // tu właściwy test
        spec2.makeMinimal(spec, "abcd");

        AutomatonByRecursion automaton = new AutomatonByRecursion(spec2);
        assertTrue(automaton.accepts("ac"));
        assertTrue(automaton.accepts("bd"));
        assertFalse(automaton.accepts("ad"));
        assertFalse(automaton.accepts("bc"));

        assertEquals(spec.countStates(), 5);
    }

    /**
     * Test na automacie akceptującym język a+.
     */
    public final void testMakeMinimalOnSimple() {

        DeterministicAutomatonSpecification spec = new NaiveDeterministicAutomatonSpecification();
        DeterministicAutomatonSpecification spec2 = new NaiveDeterministicAutomatonSpecification();

        State q0 = spec.addState();
        State q1 = spec.addState();

        spec.markAsInitial(q0);
        spec.markAsFinal(q1);

        spec.addTransition(q0, q1, new CharTransitionLabel('a'));
        spec.addLoop(q1, new CharTransitionLabel('a'));

        // dla pewności sprawdzamy jeszcze pierwotny automat
        AutomatonByRecursion originalAutomaton = new AutomatonByRecursion(spec);
        assertTrue(originalAutomaton.accepts("a"));
        assertTrue(originalAutomaton.accepts("aa"));
        assertFalse(originalAutomaton.accepts(""));
        assertFalse(originalAutomaton.accepts("b"));

        // tu właściwy test
        spec2.makeMinimal(spec, "a");

        AutomatonByRecursion automaton = new AutomatonByRecursion(spec2);
        assertTrue(automaton.accepts("a"));
        assertTrue(automaton.accepts("aa"));
        assertTrue(automaton.accepts("aaa"));
        assertTrue(automaton.accepts("aaaaaaaaaaaaaaaaaaaaaaa"));
        assertFalse(automaton.accepts(""));
        assertFalse(automaton.accepts("b"));

        assertEquals(spec.countStates(), 2);
    }

    /**
     * Test dla metody minimalizującej automat.
     */
    public final void testMakeMinimal5() {
        DeterministicAutomatonSpecification spec = new NaiveDeterministicAutomatonSpecification();
        DeterministicAutomatonSpecification spec2 = new NaiveDeterministicAutomatonSpecification();

        State s0 = spec.addState();
        State s1 = spec.addState();
        State s2 = spec.addState();

        spec.markAsInitial(s0);
        spec.markAsFinal(s2);

        spec.addTransition(s0, s1, new CharTransitionLabel('b'));
        spec.addLoop(s1, new CharTransitionLabel('b'));
        spec.addTransition(s0, s2, new CharTransitionLabel('a'));
        spec.addTransition(s1, s2, new CharTransitionLabel('a'));

        spec2.makeMinimal(spec, "ab");

        AutomatonByRecursion automaton = new AutomatonByRecursion(spec2);

        assertTrue(automaton.accepts("bbbba"));
        assertTrue(automaton.accepts("a"));

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
    /**
    ** Test ABC.
    */
    public void testAutomatonABC() {
        DeterministicAutomatonSpecification spec = new NaiveDeterministicAutomatonSpecification();
        State q0 = spec.addState();
        State q1 = spec.addState();
        State q2 = spec.addState();
        spec.addTransition(q0, q0, new CharTransitionLabel('a'));
        spec.addTransition(q0, q1, new CharTransitionLabel('b'));
        spec.addTransition(q1, q1, new CharTransitionLabel('b'));
        spec.addTransition(q1, q2, new CharTransitionLabel('c'));
        spec.addTransition(q2, q2, new CharTransitionLabel('c'));
        spec.markAsInitial(q0);
        spec.markAsFinal(q1);
        spec.markAsFinal(q2);
        DeterministicAutomaton automaton = new DeterministicAutomaton(spec);
        assertTrue(automaton.accepts("bc"));
        assertTrue(automaton.accepts("abc"));
        assertTrue(automaton.accepts("abbcc"));
        assertTrue(automaton.accepts("aaaabc"));
        assertTrue(automaton.accepts("abbcccccccccccccccccccccccccccccccccccccccc"));
        assertTrue(automaton.accepts("aaaab"));
        assertTrue(automaton.accepts("aaaaaaaaaabbc"));
        assertTrue(automaton.accepts("bbcc"));
        assertTrue(automaton.accepts("aaaabbbbbbbbbbbbbbbbbbbbb"));
        assertTrue(automaton.accepts("aaaabbbbbb"));
        assertFalse(automaton.accepts("ccccccccabbbbbbc"));
        assertFalse(automaton.accepts("aaaaaaaaaaaaaaaaaaa"));
        assertFalse(automaton.accepts("c"));
        assertFalse(automaton.accepts("cccccccccac"));
        assertFalse(automaton.accepts("abcabc"));
        assertFalse(automaton.accepts("123"));
        assertFalse(automaton.accepts("c34"));
        assertFalse(automaton.accepts("de"));
        assertFalse(automaton.accepts("cuio"));
        assertFalse(automaton.accepts("z"));
        assertFalse(automaton.accepts("999"));
        assertFalse(automaton.accepts("6n"));
        assertFalse(automaton.accepts(" "));
        assertFalse(automaton.accepts("fabian"));
        assertFalse(automaton.accepts("o2"));
    }

    /**
     * Test na automacie akceptującym język skladajacy sie z 0 i 1
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

    /**
     * Test na {a,b,c} akceptujacy slowa zaczynajace sie na a lub konczace na c.
     */
    public final void testSimpleAutomat() {

        DeterministicAutomatonSpecification spec = new NaiveDeterministicAutomatonSpecification();
        State q0 = spec.addState();
        State q1 = spec.addState();
        State q2 = spec.addState();
        State q3 = spec.addState();

        spec.markAsInitial(q0);
        spec.markAsFinal(q1);
        spec.markAsFinal(q3);

        spec.addTransition(q0, q1, new CharTransitionLabel('a'));
        spec.addLoop(q1, new CharTransitionLabel('a'));
        spec.addLoop(q1, new CharTransitionLabel('b'));
        spec.addLoop(q1, new CharTransitionLabel('c'));
        spec.addTransition(q0, q2, new CharTransitionLabel('b'));
        spec.addLoop(q2, new CharTransitionLabel('a'));
        spec.addLoop(q2, new CharTransitionLabel('b'));
        spec.addTransition(q2, q3, new CharTransitionLabel('c'));
        spec.addTransition(q0, q3, new CharTransitionLabel('c'));

        AutomatonByRecursion automaton = new AutomatonByRecursion(spec);
        assertTrue(automaton.accepts("c"));
        assertFalse(automaton.accepts("bca"));
        assertTrue(automaton.accepts("aa"));
        assertTrue(automaton.accepts("ab"));
        assertTrue(automaton.accepts("ac"));
        assertFalse(automaton.accepts("b"));
        assertFalse(automaton.accepts("bb"));
        assertFalse(automaton.accepts("bab"));
        assertTrue(automaton.accepts("bababc"));
        assertFalse(automaton.accepts("bababc\n"));
        assertFalse(automaton.accepts("d"));
        assertFalse(automaton.accepts("cba"));
        assertTrue(automaton.accepts("aaaaaaaaac"));
        assertTrue(automaton.accepts("bbbbbbbbbc"));
        assertFalse(automaton.accepts("cccccccccaaaaaa"));
        assertFalse(automaton.accepts("aaaaaaaaaaaaaa bbbbbbbb"));
        assertTrue(automaton.accepts("aaaaaabbbbbbbbbbbbbcccccccccccc"));
    }
}
