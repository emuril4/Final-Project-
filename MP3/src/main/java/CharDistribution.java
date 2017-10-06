import java.io.File;
import java.net.URI;
import java.security.InvalidParameterException;
import java.util.Scanner;

/**
 * A class that determines the character distribution in string.
 * <p>
 * Analyzing character counts is a straightforward way to break any simple substitution cipher.
 * Given a large enough corpus, text in natural languages like English have extremely similar
 * distributions of characters. You can convince yourself of that using the main method below, which
 * compares Rent and Hamlet.
 * <p>
 * By computing the distribution of the characters in a ciphertext, a crytographer can begin the
 * process of breaking simple encryption schemes. (Letter frequency also has something to do with
 * the per-letter scores in Scrabble.)
 * <p>
 * The provided code is incomplete. Modify it so that it works properly and passes the unit tests in
 * <code>CharacterDistributionTest.java</code>.
 *
 * @see <a href="https://cs125.cs.illinois.edu/MP/3/">MP3 Documentation</a>
 * @see <a href="https://en.wikipedia.org/wiki/Letter_frequency">Letter frequency</a>
 */

public class CharDistribution {
    /*
     * Take a single string argument. Return an array of doubles such that array[0] through
     * array[25] are the fractions of characters for 'a-z' and array[26] through array[51] are the
     * fractions for 'A-Z'. Other characters should not be included in the array or the count.
     * You'll want to review the ASCII character table carefully. Call this function
     * computeDistribution.
     */
    // ranges for letters both caps and lowercase
    // 65-90 = A-Z
    // 97-122 = a-z
    /**
     * @alphabet : 26 letters
     */
    public static final int ALPHABET = 26; // 26 letters
    /**
     * @totalLetters : Lowercase and upercase letters
     */
    public static final int TOTAL_LETTERS = 52; // 26 uppercase and 26 lowercase letters represented

    // computes the number of letters of each type found
    /**
     * @param words : text input
     * @return : returns array
     */
    public static double[] computeDistribution(final String words) {
        String[] array = words.split("");
        double[] show = new double[TOTAL_LETTERS]; // 26 uppercase and 26 lowercase
        int total = 0;
        int i;
        for (i = 0; i < words.length(); i++) { // while less than the total number of chars in the
                                               // given text/string
            if (array[i].charAt(0) >= 'A' && array[i].charAt(0) <= 'Z') {
                show[array[i].charAt(0) - 'A' + ALPHABET]++; // 26 letter are being added
                total++;
            } else if (array[i].charAt(0) >= 'a' && array[i].charAt(0) <= 'z') {
                show[array[i].charAt(0) - 'a']++; // don't add the 26 letters before cause these
                                                  // values are the first ones displayed
                total++; // add to total to increment total count
            }
        }

        for (int j = 0; j < show.length; j++) {
            if (total > 0) {
                show[j] = show[j] / total;
            }
        }

        return show;

    }
    /** Text of Rent. Used for interactive testing. */
    private static final String RENT_FILE = "Rent.txt";

    /** Text of Hamlet. Used for interactive testing. */
    private static final String HAMLET_FILE = "Hamlet.txt";

    //////////////////////////////// DO NOT TOUCH BELOW THIS LINE /////////////////////////////
    /**
     * Compare the character distributions in Rent.txt and Hamlet.txt.
     * <p>
     * You are free to review this function, but should not modify it. Note that this function is
     * not tested by the test suite, as it is purely to aid your own interactive testing.
     *
     * @param unused unused input arguments
     */
    public static void main(final String[] unused) {

        String rentText, hamletText;
        try {
            String rentPath = CharDistribution.class.getClassLoader()
                    .getResource(RENT_FILE).getFile();
            rentPath = new URI(rentPath).getPath();
            File rentFile = new File(rentPath);
            Scanner rentScanner = new Scanner(rentFile, "UTF-8");
            rentText = rentScanner.useDelimiter("\\A").next();
            rentScanner.close();

            String hamletPath = CharDistribution.class.getClassLoader()
                    .getResource(HAMLET_FILE).getFile();
            hamletPath = new URI(hamletPath).getPath();
            File hamletFile = new File(hamletPath);
            Scanner hamletScanner = new Scanner(hamletFile, "UTF-8");
            hamletText = hamletScanner.useDelimiter("\\A").next();
            hamletScanner.close();
        } catch (Exception e) {
            throw new InvalidParameterException("Bad file path" + e);
        }

        double[] rentArray = computeDistribution(rentText);
        double[] hamletArray = computeDistribution(hamletText);
        if (rentArray.length != hamletArray.length) {
            throw new RuntimeException("Arrays should have the same length");
        }

        String charactersInOrder = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if (charactersInOrder.length() != rentArray.length) {
            throw new RuntimeException("Wrong number of characters in returned array");
        }

        System.out.printf("%-10s%-10s%-10s\n", "Char", "Rent", "Hamlet");
        System.out.println("------------------------------");
        for (int characterIndex = 0; characterIndex < rentArray.length; characterIndex++) {
            System.out.printf("%-10s%-10.2f%-10.2f\n",
                    charactersInOrder.substring(characterIndex, characterIndex + 1),
                    rentArray[characterIndex], hamletArray[characterIndex]);

        }
    }
}
