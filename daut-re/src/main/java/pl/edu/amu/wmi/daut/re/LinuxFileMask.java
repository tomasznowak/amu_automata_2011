package pl.edu.amu.wmi.daut.re;

import pl.edu.amu.wmi.daut.base.Acceptor;

/**
 * Klasa reprezentująca maski nazw plików Linuksa.
 */
public class LinuxFileMask implements Acceptor {
    /**
     * Tekst maski.
     */
    private String maskText;

    /**
     * Konstruktor klasy. 
     */
    public LinuxFileMask(String maskText) {
        this.maskText = maskText;
    }

    /**
     * Sprawdza, czy podany String jest akceptowany przez maskę. 
     */
    @Override
    public boolean accepts(String text) {
    	if (!maskText.contains("*") && !maskText.contains("?") && text == maskText) return true;

    	boolean onlyQMarks = true;
    	for (int i = 0; i < maskText.length(); i++)
    		if (maskText.charAt(i) != '?') {
    			onlyQMarks = false;
    			break;
    		}
    	if (onlyQMarks == true) {
    		if (text.length() == maskText.length()) return true;
    		else return false;
    	}

    	int i = 0;
    	int j = 0;
    	int cp = 0;
    	int mp = 0;

    	while (i < text.length() && j < maskText.length() && maskText.charAt(j) != '*') {
    		if ((text.charAt(i) != maskText.charAt(j)) && maskText.charAt(j) != '?') return false;
    		i++;
    		j++;
    	}

    	while (i < text.length()) {
    		if (j < maskText.length() && maskText.charAt(j) == '*') {
    			if (++j > maskText.length() - 1) return true;
    			mp = j;
    			cp = i + 1;
    		}
    		else if ((j < maskText.length() && maskText.charAt(j) == text.charAt(i)) || (j < maskText.length() && maskText.charAt(j) == '?')) {
    			i++;
    			j++;
    		}
    		else {
    			j = mp;
    			i = cp++;
    		}
    	}

    	while (j < maskText.length() && maskText.charAt(j) == '*') j++;

    	boolean result = j > maskText.length() - 1;
    	return result;
    }
}
