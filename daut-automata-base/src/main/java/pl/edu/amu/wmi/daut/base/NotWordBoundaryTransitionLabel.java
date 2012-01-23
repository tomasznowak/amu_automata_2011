/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.amu.wmi.daut.base;

/**
 *
 * Klasa przejścia, które odpowiada operatorowi \B z wyrażeń regularnych.
 *
 */
public class NotWordBoundaryTransitionLabel extends ZeroLengthConditionalTransitionLabel {

    @Override
    protected boolean doCheckContext(String s, int position) {

        if (s.length() < position || position < 0)
             throw new PositionOutOfStringBordersException();

        if (position == s.length() - 1)
            return false;

        if (position == 0)
            return false;

        if (isCharacter(s.charAt(position)) && (!isCharacter(s.charAt(position - 1))
                || !isCharacter(s.charAt(position + 1))))
            return false;

        if (!isCharacter(s.charAt(position)))
            return false;

        return true;

    };

    @Override
    public boolean canAcceptCharacter(char c) {
        return false;
    };

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public String toString() {
        return "NotWordBoundary";
    }

}

