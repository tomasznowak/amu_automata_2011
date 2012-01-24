package pl.edu.amu.wmi.daut.base;

/**
 *
 * @author irmina90
 *
 * Klasa odpowiedzialna za epsilon-przejscie.
 *
 */
public class EndOfTextTransitionLabel extends ZeroLengthConditionalTransitionLabel {

    @Override
    protected boolean doCheckContext(String s, int position) {
        if (position < 0 || position > s.length()) {
            throw new PositionOutOfStringBordersException();
        }
        if (position == s.length()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean canAcceptCharacter(char c) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public String toString() {
        return "EndOfText";
    }

}
