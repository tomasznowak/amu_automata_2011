package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;

/**
 *
 * @author marcinwacho
 */
public class TestCharClassTransitionLabel extends TestCase {

    /**
     * Testujemy metody addChar, canBeEpsilon,
     * canAcceptCharacter, isEmpty, toString.
     */
    public final void testCharClassTransitionLabel() {

        //budujemy
        TransitionLabel test1 = new CharClassTransitionLabel("0-9");

        //testujemy
        assertTrue(test1.canAcceptCharacter('0'));
        assertTrue(test1.canAcceptCharacter('5'));
        assertFalse(test1.canAcceptCharacter('a'));
        assertTrue(test1.canAcceptCharacter('9'));
        assertEquals(test1.toString(), "[0-9]");
        assertFalse(test1.canBeEpsilon());
        assertFalse(test1.isEmpty());
        assertFalse(test1.canAcceptCharacter('b'));

        //budujemy
        TransitionLabel test2 = new CharClassTransitionLabel("6a-fA-Zq");

        //testujemy
        assertTrue(test2.canAcceptCharacter('6'));
        assertTrue(test2.canAcceptCharacter('a'));
        assertTrue(test2.canAcceptCharacter('b'));
        assertTrue(test2.canAcceptCharacter('f'));
        assertTrue(test2.canAcceptCharacter('A'));
        assertTrue(test2.canAcceptCharacter('C'));
        assertFalse(test2.canAcceptCharacter('7'));
        assertTrue(test2.canAcceptCharacter('G'));
        assertTrue(test2.canAcceptCharacter('Y'));
        assertTrue(test2.canAcceptCharacter('6'));
        assertFalse(test2.canAcceptCharacter('g'));
        assertTrue(test2.canAcceptCharacter('q'));
        assertEquals(test2.toString(), "[6a-fA-Zq]");
        assertFalse(test2.canBeEpsilon());
        assertFalse(test2.isEmpty());
        assertFalse(test2.canAcceptCharacter('h'));
        assertFalse(test2.canAcceptCharacter('5'));
        assertEquals(test2.toString(), "[6a-fA-Zq]");

        //budujemy
        TransitionLabel test3 = new CharClassTransitionLabel("-4-9");

        //testujemy
        assertTrue(test3.canAcceptCharacter('-'));
        assertTrue(test3.canAcceptCharacter('4'));
        assertTrue(test3.canAcceptCharacter('5'));
        assertFalse(test3.canAcceptCharacter('a'));
        assertTrue(test3.canAcceptCharacter('9'));
        assertEquals(test3.toString(), "[-4-9]");
        assertFalse(test3.canBeEpsilon());
        assertFalse(test3.isEmpty());
        assertFalse(test3.canAcceptCharacter('b'));

        //budujemy
        TransitionLabel test4 = new CharClassTransitionLabel("3a-fA-Zq-");

        //testujemy
        assertTrue(test4.canAcceptCharacter('-'));
        assertTrue(test4.canAcceptCharacter('3'));
        assertTrue(test4.canAcceptCharacter('a'));
        assertTrue(test4.canAcceptCharacter('b'));
        assertTrue(test4.canAcceptCharacter('f'));
        assertTrue(test4.canAcceptCharacter('A'));
        assertTrue(test4.canAcceptCharacter('C'));
        assertTrue(test4.canAcceptCharacter('G'));
        assertTrue(test4.canAcceptCharacter('Y'));
        assertTrue(test4.canAcceptCharacter('3'));
        assertFalse(test4.canAcceptCharacter('g'));
        assertTrue(test4.canAcceptCharacter('q'));
        assertEquals(test4.toString(), "[3a-fA-Zq-]");
        assertFalse(test4.canBeEpsilon());
        assertFalse(test4.isEmpty());
        assertFalse(test4.canAcceptCharacter('h'));
        assertFalse(test4.canAcceptCharacter('5'));
        assertEquals(test4.toString(), "[3a-fA-Zq-]");
    }

    /**
     * Testujemy intersectWith.
     */
    public final void testIntersectWith() {

        //budujemy
        TransitionLabel test1 = new CharClassTransitionLabel("a-c");
        TransitionLabel test2 = new CharClassTransitionLabel("b-f");

        //testujemy
        TransitionLabel inter = test1.intersect(test2);

        assertTrue(inter.canAcceptCharacter('b'));
        assertTrue(inter.canAcceptCharacter('c'));
        assertFalse(inter.canAcceptCharacter('a'));
        assertFalse(inter.canAcceptCharacter('d'));
        assertFalse(inter.canAcceptCharacter('f'));
        assertFalse(inter.canBeEpsilon());
        assertFalse(inter.isEmpty());
        assertEquals(inter.toString(), "[bc]");
        assertFalse(inter.canBeEpsilon());
        assertTrue(inter.canAcceptCharacter('c'));
    }
}
