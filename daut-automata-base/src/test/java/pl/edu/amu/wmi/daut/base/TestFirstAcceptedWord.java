package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;

/**
 * test metody FirstAcceptedWord.
 */
public class TestFirstAcceptedWord extends TestCase {

    /**
     * automatPusty.
     */
    public final void testFirstAcceptedWordEmpty() {
        AutomatonSpecification a = new NaiveAutomatonSpecification();
        State s0 = a.addState();
        a.markAsInitial(s0);
        a.markAsFinal(s0);
        assertEquals(a.firstAcceptedWord(""), "");
    }

    /**
     * automatA. Nie pusty ale akceptuje slowo puste.
     */
    public final void testFirstAcceptedWordA() {
        AutomatonSpecification a = new NaiveAutomatonSpecification();
        State s0 = a.addState();
        State s1 = a.addState();
        State s2 = a.addState();
        a.markAsInitial(s0);
        a.markAsFinal(s0);
        a.markAsFinal(s1);
        a.markAsFinal(s2);
        a.addTransition(s0, s1, new CharTransitionLabel('a'));
        a.addTransition(s0, s2, new CharTransitionLabel('b'));
        a.addTransition(s1, s2, new CharTransitionLabel('a'));
        a.addTransition(s1, s0, new CharTransitionLabel('b'));
        a.addTransition(s2, s1, new CharTransitionLabel('b'));
        a.addTransition(s2, s0, new CharTransitionLabel('a'));
        assertEquals(a.firstAcceptedWord("ab"), "");
    }

    /**
     * automatB. nic ciekawego.
     */
    public final void testFirstAcceptedWordB() {
        AutomatonSpecification a = new NaiveAutomatonSpecification();
        State s0 = a.addState();
        State s1 = a.addState();
        State s2 = a.addState();
        State s3 = a.addState();
        State s4 = a.addState();
        State s5 = a.addState();
        State s6 = a.addState();
        a.markAsInitial(s0);
        a.markAsFinal(s6);
        a.addTransition(s0, s2, new CharTransitionLabel('a'));
        a.addTransition(s2, s3, new CharTransitionLabel('a'));
        a.addTransition(s3, s4, new CharTransitionLabel('a'));
        a.addTransition(s4, s5, new CharTransitionLabel('a'));
        a.addTransition(s5, s6, new CharTransitionLabel('a'));
        a.addTransition(s0, s1, new CharTransitionLabel('b'));
        assertEquals(a.firstAcceptedWord("ab"), "aaaaa");
    }

    /**
     * automatC. nie ma pierwszego slowa.
     */
    public final void testFirstAcceptedWordC() {
        AutomatonSpecification a = new NaiveAutomatonSpecification();
        State s0 = a.addState();
        State s1 = a.addState();
        State s2 = a.addState();
        State s3 = a.addState();
        a.markAsInitial(s0);
        a.markAsFinal(s1);
        a.addTransition(s0, s1, new CharTransitionLabel('c'));
        a.addTransition(s0, s3, new CharTransitionLabel('a'));
        a.addTransition(s2, s1, new CharTransitionLabel('c'));
        a.addTransition(s2, s3, new CharTransitionLabel('a'));
        a.addTransition(s3, s0, new CharTransitionLabel('b'));
        a.addTransition(s3, s2, new CharTransitionLabel('a'));
        a.addTransition(s1, s0, new CharTransitionLabel('a'));
        a.addTransition(s1, s2, new CharTransitionLabel('a'));
        assertEquals(a.firstAcceptedWord("abc"), "c");
    }

    /**
     * automatD. jezyk akceptowany przezen jest nieskonczony
     */
    public final void testFirstAcceptedWordD() {
        AutomatonSpecification a = new NaiveAutomatonSpecification();
        State s0 = a.addState();
        State s1 = a.addState();
        State s2 = a.addState();
        State s3 = a.addState();
        a.markAsInitial(s0);
        a.markAsFinal(s1);
        a.addTransition(s0, s1, new CharTransitionLabel('b'));
        a.addTransition(s0, s3, new CharTransitionLabel('a'));
        a.addTransition(s2, s1, new CharTransitionLabel('a'));
        a.addTransition(s2, s3, new CharTransitionLabel('b'));
        a.addTransition(s3, s0, new CharTransitionLabel('b'));
        a.addTransition(s3, s2, new CharTransitionLabel('a'));
        a.addTransition(s1, s0, new CharTransitionLabel('b'));
        a.addTransition(s1, s2, new CharTransitionLabel('b'));
        assertEquals(a.firstAcceptedWord("ba"), "b");
    }

    /**
     * automatE. "Tasiemiec".
     */
    public final void testFirstAcceptedWordE() {
        AutomatonSpecification a = new NaiveAutomatonSpecification();
        State s0 = a.addState();
        State s1 = a.addState();
        State s2 = a.addState();
        State s3 = a.addState();
        State s4 = a.addState();
        State s5 = a.addState();
        State s6 = a.addState();
        a.markAsInitial(s0);
        a.markAsFinal(s6);
        a.addTransition(s0, s1, new CharTransitionLabel('s'));
        a.addTransition(s0, s2, new CharTransitionLabel('d'));
        a.addTransition(s1, s2, new CharTransitionLabel('f'));
        a.addTransition(s1, s3, new CharTransitionLabel('g'));
        a.addTransition(s2, s3, new CharTransitionLabel('s'));
        a.addTransition(s2, s4, new CharTransitionLabel('c'));
        a.addTransition(s3, s4, new CharTransitionLabel('z'));
        a.addTransition(s3, s5, new CharTransitionLabel('b'));
        a.addTransition(s4, s5, new CharTransitionLabel('f'));
        a.addTransition(s4, s6, new CharTransitionLabel('v'));
        a.addTransition(s5, s6, new CharTransitionLabel('k'));
        assertEquals(a.firstAcceptedWord("sdfgczbvk"), "dcv");
    }

    /**
     * automatF. tez nieskonczony.
     */
    public final void testFirstAcceptedWordF() {
        AutomatonSpecification a = new NaiveAutomatonSpecification();
        State s0 = a.addState();
        State s1 = a.addState();
        State s2 = a.addState();
        State s3 = a.addState();
        State s4 = a.addState();
        State s5 = a.addState();
        State s6 = a.addState();
        a.markAsInitial(s0);
        a.markAsFinal(s6);
        a.addTransition(s0, s1, new CharTransitionLabel('s'));
        a.addTransition(s1, s0, new CharTransitionLabel('s'));
        a.addTransition(s0, s2, new CharTransitionLabel('d'));
        a.addTransition(s1, s2, new CharTransitionLabel('f'));
        a.addTransition(s1, s3, new CharTransitionLabel('g'));
        a.addTransition(s2, s3, new CharTransitionLabel('s'));
        a.addTransition(s2, s4, new CharTransitionLabel('c'));
        a.addTransition(s3, s4, new CharTransitionLabel('z'));
        a.addTransition(s3, s5, new CharTransitionLabel('b'));
        a.addTransition(s4, s5, new CharTransitionLabel('f'));
        a.addTransition(s4, s6, new CharTransitionLabel('v'));
        a.addTransition(s5, s6, new CharTransitionLabel('k'));
        assertEquals(a.firstAcceptedWord("sdfgczbvk"), "dcv");
    }

    /**
     * automat akceptujacy tylko jedno slowo.
     */
    public final void testFirstAcceptedWordG() {
        AutomatonSpecification spec = new NaiveAutomatonSpecification();

        State q0 = spec.addState();
        State q1 = spec.addState();
        State q2 = spec.addState();
        State q3 = spec.addState();
        State q4 = spec.addState();
        State q5 = spec.addState();
        State q6 = spec.addState();

        spec.addTransition(q0, q1, new CharTransitionLabel('\u017A'));
        spec.addTransition(q1, q2, new CharTransitionLabel('d'));
        spec.addTransition(q2, q3, new CharTransitionLabel('\u017A'));
        spec.addTransition(q3, q4, new CharTransitionLabel('b'));
        spec.addTransition(q4, q5, new CharTransitionLabel('\u0142'));
        spec.addTransition(q5, q6, new CharTransitionLabel('o'));

        spec.markAsInitial(q0);
        spec.markAsFinal(q6);

        assertTrue(spec.firstAcceptedWord("\u017Adb\u0142o").equals("\u017Ad\u017Ab\u0142o"));
        assertFalse(spec.firstAcceptedWord("\u017Adb\u0142o").equals("\u017Adb\u0142o"));
        assertFalse(spec.firstAcceptedWord("\u017Adb\u0142o").equals("o\u017Ad\u017Ab\u0142"));
        assertFalse(spec.firstAcceptedWord("\u017Adb\u0142o").equals("zdzblo"));
        assertFalse(spec.firstAcceptedWord("\u017Adb\u0142o").equals(""));
        assertFalse(spec.firstAcceptedWord("\u017Adb\u0142o").equals("123"));
        assertFalse(spec.firstAcceptedWord("\u017Adb\u0142o").equals("\u017Ad\u017Ab\u0142"));
        assertFalse(spec.firstAcceptedWord("\u017Adb\u0142o").equals("???"));
//zapetlenie sie programu;
//assertFalse(spec.firstAcceptedWord("tes").equals("\u017Ad\u017Ab\u0142o"));
    }
}
