package pl.edu.amu.wmi.daut.base;

import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;

/**
 * Klasa testująca klasę RandomStringGenerator.
 */
public class TestRandomStringGenerator extends TestCase {

    private static final int N = 1000;

    /**
     * Metoda testująca RandomStringGenerator.
     * Puste słowo.
     */
    public void testIsEmptyWordPresent() {
        RandomStringGenerator generator = new RandomStringGenerator("ABCD");
        List<String>  randomWords = generateRandomWords(generator, N);
        assertTrue(listContains(randomWords, ""));
    }

    /**
     * Metoda testująca RandomStringGenerator.
     * Niepuste słowo.
     */
    public void testAreAllCharacterPresent() {
        String alphabet = "ABCDEFGHIJ";
        RandomStringGenerator generator = new RandomStringGenerator(alphabet);
        List<String>  randomWords = generateRandomWords(generator, N);
        for (int i = 0; i < alphabet.length(); i++) {
            assertTrue(listContains(randomWords, alphabet.charAt(i)));
        }
    }

    private List<String>  generateRandomWords(RandomStringGenerator generator, int n) {
        List<String>  result = new ArrayList<String>(n);
        for (int i = 0; i < n; i++) {
            result.add(generator.getRandomString());
        }
        return result;
    }

    private boolean listContains(List<String>  list, String pattern) {
        for (String word : list) {
            if (word.equals(pattern)) {
                return true;
            }
        }
        return false;
    }

    private boolean listContains(List<String>  list, char character) {
        CharBuffer pattern = CharBuffer.allocate(1);
        pattern.append(character);
        for (String word : list) {
            if (word.contains(pattern)) {
                return true;
            }
        }
        return false;
    }
}
