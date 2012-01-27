package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;

/**
 * Klasa testujaca klase EndOfTextTransitionLabel.
 */
public class TestEndOfTextTransitionLabel extends TestCase {

    /**
     * Test metody doCheckContext.
     */
    public final void testDoCheckContext() {
        EndOfTextTransitionLabel trans = new EndOfTextTransitionLabel();

        assertTrue(trans.doCheckContext("trololololo", 11));
        assertTrue(trans.doCheckContext("masa", 4));

        assertFalse(trans.doCheckContext("cojapacze", 4));
        assertFalse(trans.doCheckContext("jestemhardcorem", 7));

        try {
            trans.doCheckContext("lubieryz", 10);
            fail();
        } catch (PositionOutOfStringBordersException e) {
            assertTrue(true);
        }

        try {
            trans.doCheckContext("jemzupe", 27);
            fail();
        } catch (PositionOutOfStringBordersException e) {
            assertTrue(true);
        }

        /**
         * Test atrybutów.
         */

        assertEquals(trans.toString(), "EndOfText");

        assertFalse(trans.isEmpty());
        assertFalse(trans.canAcceptCharacter('s'));

    }

}
