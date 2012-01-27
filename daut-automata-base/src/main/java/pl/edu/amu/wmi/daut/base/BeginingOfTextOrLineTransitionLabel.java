package pl.edu.amu.wmi.daut.base;

/**
 * Klasa BeginingOfTextOrLineTransitionLabel, która odpowiada operatorowi "^" z
 * z wyrażeń regularnych standardu POSIX.
 */
public class BeginingOfTextOrLineTransitionLabel
    extends ZeroLengthConditionalTransitionLabel {

    @Override
    protected boolean doCheckContext(String s, int position) {
        if ((s.length() < position) || (position < 0)) {
            throw new PositionOutOfStringBordersException();
        } else if (!s.isEmpty() && position == 0 || s.charAt(-1) == '\n') {
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
        return "BeginingOfTextOrLine";
    }
}
