package pl.edu.amu.wmi.daut.re;

import java.util.ArrayList;
import junit.framework.TestCase;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NondeterministicAutomatonByThompsonApproach;
/**
 *
 * @author adrian
 */
public class TestNegatedAsciiCharacterClass extends TestCase {
    /**
     * Test konstruktora AsciiCharacterClassOperator dla złych wartości parametru.
     */
    public void testAsciiCharacterClassOperatorWrongParams() {

        try {
            ArrayList<String> params = new ArrayList<String>();
            params.add("zlyTest1");
            (new NegatedAsciiCharacterClass.Factory()).createOperator(params);
            fail();
        } catch (UnknownAsciiCharacterClassException e) {
            assertTrue(true);
        }
    }

    /**
     * Test klasy znaków alnum.
     */
    public void testAlnumNegatedAsciiCharacter() {
        ArrayList<String> params = new ArrayList<String>();
        params.add("alnum");
        RegexpOperator spec = (new NegatedAsciiCharacterClass.Factory()).createOperator(params);
        NondeterministicAutomatonByThompsonApproach automaton =
            new NondeterministicAutomatonByThompsonApproach(spec.createAutomaton(
                new ArrayList<AutomatonSpecification>()));
        assertFalse(automaton.accepts("a"));
        assertFalse(automaton.accepts("Z"));
        assertFalse(automaton.accepts("9"));
        assertFalse(automaton.accepts(""));
        assertTrue(automaton.accepts("]"));
        assertTrue(automaton.accepts("$"));
        assertTrue(automaton.accepts("@"));
        assertFalse(automaton.accepts("asdada"));
    }

    /**
     * Test klasy znaków alpha.
     */
    public void testAlphaNegatedAsciiCharacter() {
       ArrayList<String> params = new ArrayList<String>();
        params.add("alpha");
        RegexpOperator spec = (new NegatedAsciiCharacterClass.Factory()).createOperator(params);
        NondeterministicAutomatonByThompsonApproach automaton =
            new NondeterministicAutomatonByThompsonApproach(spec.createAutomaton(
                new ArrayList<AutomatonSpecification>()));
        assertFalse(automaton.accepts("a"));
        assertFalse(automaton.accepts("Z"));
        assertTrue(automaton.accepts("]"));
        assertTrue(automaton.accepts("$"));
        assertTrue(automaton.accepts("5"));
        assertFalse(automaton.accepts("asdada"));
    }

    /**
     * Test klasy znaków blank.
     */
    public void testBlankNegatedAsciiCharacter() {
        ArrayList<String> params = new ArrayList<String>();
        params.add("blank");
        RegexpOperator spec = (new NegatedAsciiCharacterClass.Factory()).createOperator(params);
        NondeterministicAutomatonByThompsonApproach automaton =
            new NondeterministicAutomatonByThompsonApproach(spec.createAutomaton(
                new ArrayList<AutomatonSpecification>()));
        assertFalse(automaton.accepts("\t"));
        assertTrue(automaton.accepts("a"));
        assertTrue(automaton.accepts("Z"));
        assertTrue(automaton.accepts("9"));
        assertFalse(automaton.accepts("asfasd"));
    }

    /**
     * Test klasy znaków cntrl.
     */
    public void testCntrlNegatedAsciiCharacter() {
        ArrayList<String> params = new ArrayList<String>();
        params.add("cntrl");
        RegexpOperator spec = (new NegatedAsciiCharacterClass.Factory()).createOperator(params);
        NondeterministicAutomatonByThompsonApproach automaton =
            new NondeterministicAutomatonByThompsonApproach(spec.createAutomaton(
                new ArrayList<AutomatonSpecification>()));
        assertFalse(automaton.accepts("\u0000"));
        assertFalse(automaton.accepts("\u0001"));
        assertTrue(automaton.accepts("a"));
        assertTrue(automaton.accepts("Z"));
        assertTrue(automaton.accepts("9"));
        assertFalse(automaton.accepts("sadada"));
    }

    /**
     * Test klasy znaków digit.
     */
    public void testDigitNegatedAsciiCharacter() {
        ArrayList<String> params = new ArrayList<String>();
        params.add("digit");
        RegexpOperator spec = (new NegatedAsciiCharacterClass.Factory()).createOperator(params);
        NondeterministicAutomatonByThompsonApproach automaton =
            new NondeterministicAutomatonByThompsonApproach(spec.createAutomaton(
                new ArrayList<AutomatonSpecification>()));
        assertFalse(automaton.accepts("0"));
        assertFalse(automaton.accepts("3"));
        assertFalse(automaton.accepts("4"));
        assertFalse(automaton.accepts("1"));
        assertFalse(automaton.accepts("9"));
        assertTrue(automaton.accepts("a"));
        assertTrue(automaton.accepts("Z"));
        assertTrue(automaton.accepts("$"));
        assertFalse(automaton.accepts("asdasd"));
    }

    /**
     * Test klasy znaków graph.
     */
    public void testGraphNegatedAsciiCharacter() {
        ArrayList<String> params = new ArrayList<String>();
        params.add("graph");
        RegexpOperator spec = (new NegatedAsciiCharacterClass.Factory()).createOperator(params);
        NondeterministicAutomatonByThompsonApproach automaton =
            new NondeterministicAutomatonByThompsonApproach(spec.createAutomaton(
                new ArrayList<AutomatonSpecification>()));
        assertFalse(automaton.accepts("!"));
        assertFalse(automaton.accepts("~"));
        assertFalse(automaton.accepts("-"));
        assertTrue(automaton.accepts("a"));
        assertTrue(automaton.accepts("Z"));
        assertTrue(automaton.accepts("9"));
        assertFalse(automaton.accepts("asdasdg"));
    }

    /**
     * Test klasy znaków lower.
     */
    public void testLowerNegatedAsciiCharacter() {
        ArrayList<String> params = new ArrayList<String>();
        params.add("lower");
        RegexpOperator spec = (new NegatedAsciiCharacterClass.Factory()).createOperator(params);
        NondeterministicAutomatonByThompsonApproach automaton =
            new NondeterministicAutomatonByThompsonApproach(spec.createAutomaton(
                new ArrayList<AutomatonSpecification>()));
        assertFalse(automaton.accepts("z"));
        assertFalse(automaton.accepts("a"));
        assertFalse(automaton.accepts("g"));
        assertTrue(automaton.accepts("A"));
        assertTrue(automaton.accepts("Z"));
        assertTrue(automaton.accepts("9"));
        assertFalse(automaton.accepts("ADdd"));
    }

    /**
     * Test klasy znaków print.
     */
    public void testPrintNegatedAsciiCharacter() {
        ArrayList<String> params = new ArrayList<String>();
        params.add("print");
        RegexpOperator spec = (new NegatedAsciiCharacterClass.Factory()).createOperator(params);
        NondeterministicAutomatonByThompsonApproach automaton =
            new NondeterministicAutomatonByThompsonApproach(spec.createAutomaton(
                new ArrayList<AutomatonSpecification>()));
        assertFalse(automaton.accepts(" "));
        assertFalse(automaton.accepts("a"));
        assertFalse(automaton.accepts("!"));
        assertFalse(automaton.accepts("*"));
        assertFalse(automaton.accepts("$"));
        assertFalse(automaton.accepts("\""));
        assertFalse(automaton.accepts("("));
        assertFalse(automaton.accepts("0"));
        assertFalse(automaton.accepts("sdada"));
    }

    /**
     * Test klasy znaków punct.
     */
    public void testPunctNegatedAsciiCharacter() {
        ArrayList<String> params = new ArrayList<String>();
        params.add("punct");
        RegexpOperator spec = (new NegatedAsciiCharacterClass.Factory()).createOperator(params);
        NondeterministicAutomatonByThompsonApproach automaton =
            new NondeterministicAutomatonByThompsonApproach(spec.createAutomaton(
                new ArrayList<AutomatonSpecification>()));
        assertFalse(automaton.accepts("!"));
        assertFalse(automaton.accepts("/"));
        assertFalse(automaton.accepts("@"));
        assertFalse(automaton.accepts("["));
        assertTrue(automaton.accepts("9"));
        assertFalse(automaton.accepts("3rre"));
    }

    /**
     * Test klasy znaków space.
     */
    public void testSpaceNegatedAsciiCharacter() {
        ArrayList<String> params = new ArrayList<String>();
        params.add("space");
        RegexpOperator spec = (new NegatedAsciiCharacterClass.Factory()).createOperator(params);
        NondeterministicAutomatonByThompsonApproach automaton =
            new NondeterministicAutomatonByThompsonApproach(spec.createAutomaton(
                new ArrayList<AutomatonSpecification>()));
        assertFalse(automaton.accepts("\u000B"));
        assertFalse(automaton.accepts("\t"));
        assertFalse(automaton.accepts("\n"));
        assertFalse(automaton.accepts("\f"));
        assertTrue(automaton.accepts("a"));
        assertTrue(automaton.accepts("Z"));
        assertTrue(automaton.accepts("9"));
        assertFalse(automaton.accepts("gfgh"));
    }

    /**
     * Test klasy znaków upper.
     */
    public void testUpperNegatedAsciiCharacter() {
        ArrayList<String> params = new ArrayList<String>();
        params.add("upper");
        RegexpOperator spec = (new NegatedAsciiCharacterClass.Factory()).createOperator(params);
        NondeterministicAutomatonByThompsonApproach automaton =
            new NondeterministicAutomatonByThompsonApproach(spec.createAutomaton(
                new ArrayList<AutomatonSpecification>()));
        assertFalse(automaton.accepts("A"));
        assertFalse(automaton.accepts("C"));
        assertFalse(automaton.accepts("G"));
        assertFalse(automaton.accepts("Z"));
        assertTrue(automaton.accepts("a"));
        assertTrue(automaton.accepts("s"));
        assertTrue(automaton.accepts("9"));
        assertFalse(automaton.accepts("asdww"));
    }

    /**
     * Test klasy znaków word.
     */
    public void testWordNegatedAsciiCharacter() {
        ArrayList<String> params = new ArrayList<String>();
        params.add("word");
        RegexpOperator spec = (new NegatedAsciiCharacterClass.Factory()).createOperator(params);
        NondeterministicAutomatonByThompsonApproach automaton =
            new NondeterministicAutomatonByThompsonApproach(spec.createAutomaton(
                new ArrayList<AutomatonSpecification>()));
        assertFalse(automaton.accepts("a"));
        assertFalse(automaton.accepts("B"));
        assertFalse(automaton.accepts("_"));
        assertFalse(automaton.accepts("9"));
        assertTrue(automaton.accepts("!"));
        assertTrue(automaton.accepts("("));
        assertTrue(automaton.accepts("%"));
        assertFalse(automaton.accepts("dadada"));
        assertFalse(automaton.accepts(""));
    }

    /**
     * Test klasy znaków xdigit.
     */
    public void testXdigitNegatedAsciiCharacter() {
        ArrayList<String> params = new ArrayList<String>();
        params.add("xdigit");
        RegexpOperator spec = (new NegatedAsciiCharacterClass.Factory()).createOperator(params);
        NondeterministicAutomatonByThompsonApproach automaton =
            new NondeterministicAutomatonByThompsonApproach(spec.createAutomaton(
                new ArrayList<AutomatonSpecification>()));
        assertFalse(automaton.accepts("0"));
        assertFalse(automaton.accepts("8"));
        assertFalse(automaton.accepts("A"));
        assertFalse(automaton.accepts("f"));
        assertFalse(automaton.accepts("F"));
        assertFalse(automaton.accepts("a"));
        assertTrue(automaton.accepts("!"));
        assertTrue(automaton.accepts("("));
        assertTrue(automaton.accepts("%"));
        assertFalse(automaton.accepts("sfggg"));
    }

    /**
     * Test metody toString.
     */
    public void testToString() {
        NegatedAsciiCharacterClass negatedClass = new NegatedAsciiCharacterClass("alnum");
        assertTrue(negatedClass.toString() == "^ASCII");
    }
}
