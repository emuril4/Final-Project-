import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A class that searches for a string in text.
 * <p>
 * The provided code is incomplete. Modify it so that it works properly and passes the unit tests in
 * <code>FindLineTest.java</code>.
 *
 * @see <a href="https://cs125.cs.illinois.edu/MP/2/">MP2 Documentation</a>
 * @see <a href="https://en.wikipedia.org/wiki/Grep">grep Command Documentation</a>
 */
public class FindLine {

    /**
     * Text file that we use for interactive testing. Not necessarily the same as the files used
     * during automated testing. See FindLineTest.java.
     */
    private static final String INTERACTIVE_TEXT_FILE = "BillOfRights.txt";

    /**
     * Find word in multi-line string and print matching lines and line numbers.
     * <p>
     * For example, given the string:
     * <pre>
     * Hello, there!
     * I'm here.
     * You're there.
     * Therefore, we're near.
     * </pre>
     * <p>
     * If the search term is "there" or "There" or "THERE", the following lines
     * should be printed:
     * <pre>
     * 1: Hello, there!
     * 3: You're there.
     * </pre>
     * <p>
     * If the search term is "I'm" or "i'm", the following lines should be printed
     * <pre>
     * "2: I'm here."
     * </pre>
     * <p>
     * Here are some things to keep in mind:
     * <ol>
     * <li>Note that the line numbering is 1-indexed: i.e., the first line is line 1, not line 0,
     * which might be a bit more natural to a computer scientist.</li>
     * <li>Matches are always case-insensitive.</li>
     * <li>The follow characters are considered word boundaries and will never be part of the search
     * term: ' ', '.', '!', '?', ',', ';', '(', ')', '-'</li>
     * <li>So, for example, searching for "two" should match "two fold", "two.", "two!", "two?",
     * "two,", "two;", "(one two)", "(two one)", and "two-fold".</li>
     * <li>You should print to the console in the usual way, not return a string.</li>
     * </ol>
     * <p>
     * Also note the leading space after the line number and colon:
     * <pre>
     * (number):  (line contents)
     * </pre>
     * <p>
     * Your solution must match the expected output exactly, otherwise you will not receive
     * credit.
     * <p>
     * Complete the Javadoc comment for this function and write it.
     */
    public static void printMatchingLines(final String searchWord, final String corpus) {
        return null;
    }

    /**********************************************************************************************
     * You do not need to modify code below this comment.
     **********************************************************************************************/

    /**
     * Solicits a single word from the user at the command line and searches for it in the Bill of
     * Rights (BillOfRights.txt).
     * <p>
     * You are free to review this function, but should not modify it. Note that this function is
     * not tested by the test suite, as it is purely to aid your own interactive testing.
     *
     * @param unused unused input arguments
     * @throws FileNotFoundException exception if the text file cannot be located
     */
    public static void main(final String[] unused) throws FileNotFoundException {

        String inputPrompt = String.format("Enter an single word:");
        Scanner lineScanner = new Scanner(System.in);

        while (true) {
            String searchTerm = null;
            System.out.println(inputPrompt);

            /*
             * We could just use lineScanner.hasNextInt() and not initialize a separate scanner. But
             * the default Scanner class ignores blank lines and continues to search for input until
             * a non-empty line is entered. This approach allows us to detect empty lines and remind
             * the user to provide a valid input.
             */
            String nextLine = lineScanner.nextLine();
            Scanner inputScanner = new Scanner(nextLine);
            if (!(inputScanner.hasNext())) {
                /*
                 * These should be printed as errors using System.err.println. Unfortunately,
                 * Eclipse can't keep System.out and System.err ordered properly.
                 */
                System.out.println("Invalid input: please enter an single word.");
                continue;
            }
            searchTerm = inputScanner.next();
            /*
             * If the line started with a string but contains other tokens, reinitialize userInput
             * and prompt the user again.
             */
            if (inputScanner.hasNext()) {
                System.out.println("Invalid input: please enter only a single word.");
                continue;
            }
            inputScanner.close();

            ClassLoader classLoader = FindLine.class.getClassLoader();
            File billOfRightsFile = new File(
                    classLoader.getResource(INTERACTIVE_TEXT_FILE).getFile());

            Scanner billOfRightsScanner = new Scanner(billOfRightsFile, "UTF-8");
            String billOfRights = billOfRightsScanner.useDelimiter("\\A").next();
            billOfRightsScanner.close();

            printMatchingLines(searchTerm, billOfRights);
            break;
        }
        lineScanner.close();
    }
}
