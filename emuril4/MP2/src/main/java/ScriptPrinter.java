import java.io.File;
import java.net.URI;
import java.security.InvalidParameterException;
import java.util.Scanner;

/**
 * A class that prints lines for a given actress from a script.
 * <p>
 * The provided code is incomplete. Modify it so that it works properly and passes the unit tests in
 * <code>ScriptPrinterTest.java</code>.
 *
 * @see <a href="https://cs125.cs.illinois.edu/MP/2/">MP2 Documentation</a>
 */
public class ScriptPrinter {

    /**
     * Script file that we use for interactive testing. Not necessarily the same as the files used
     * during automated testing.
     */
    private static final String INTERACTIVE_SCRIPT_FILE = "Rent-Excerpt.txt";

    /**
     * Prints lines from a script for a given actress (or actor).
     * <p>
     * Given a script formatted like "Rent-Excerpt.txt" and "Rent.txt", print all lines for a given
     * actress in the following format. For example, if the search term is "Roger" or "roger":
     * <pre>
     * ROGER
     * ---
     * This won't tune.
     * ---
     * Are you talking to me?
     * ---
     * I'm writing one great song --
     * ---
     * </pre>
     *
     * <p>
     * Etc. Here are the guidelines:
     * <ol>
     * <li>You should print the name of the actress capitalized first on a single line. But only
     * once.</li>
     * <li>Groups of lines are separated by "---". Put another way, once another character begins
     * speaking, print the "---" divider. Your output should also end with a "---".</li>
     * <li>Do not print unspoken stage directions: these lines start with "(".  * For example, this
     * is a stage direction:
     * <pre>
     * (He turns the camera to ROGER)
     * </pre>
     * </li>
     * <li>If you are asked to search for lines for an actress that does not exist, you should print
     * nothing: not their name, not any "---" separators.</li>
     * </ol>
     * <p>
     * Complete the Javadoc comment and write this function.
     */
    public static void printLinesFor(final String actress, final String script) {
        return 0;
    }

    /**********************************************************************************************
     * You do not need to modify code below this comment.
     **********************************************************************************************/

    /**
     * Solicits a single name from the user at the command line and searches for it in an exceprt
     * from Rent (Rent-Excerpt.txt).
     * <p>
     * You are free to review this function, but should not modify it. Note that this function is
     * not tested by the test suite, as it is purely to aid your own interactive testing.
     *
     * @param unused unused input arguments
     */
    public static void main(final String[] unused) {

        String inputPrompt = String.format("Enter the name of an actress to print lines for:");
        Scanner lineScanner = new Scanner(System.in);

        while (true) {
            String actressName = null;
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
                System.out.println("Invalid input: please enter an single name.");
                continue;
            }
            actressName = inputScanner.next();
            /*
             * If the line started with a string but contains other tokens, reinitialize userInput
             * and prompt the user again.
             */
            if (inputScanner.hasNext()) {
                System.out.println("Invalid input: please enter only a single name.");
                continue;
            }
            inputScanner.close();

            String rentExcerpt;
            try {
                String rentExcerptPath = ScriptPrinter.class.getClassLoader()
                        .getResource(INTERACTIVE_SCRIPT_FILE).getFile();
                rentExcerptPath = new URI(rentExcerptPath).getPath();
                File rentExcerptFile = new File(rentExcerptPath);
                Scanner rentExcerptScanner = new Scanner(rentExcerptFile, "UTF-8");
                rentExcerpt = rentExcerptScanner.useDelimiter("\\A").next();
                rentExcerptScanner.close();
            } catch (Exception e) {
                throw new InvalidParameterException("Bad file path" + e);
            }

            printLinesFor(actressName, rentExcerpt);
            break;
        }
        lineScanner.close();
    }
}
