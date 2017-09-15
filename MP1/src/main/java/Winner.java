import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class that prints three scores in order.
 * <p>
 * The provided code is incomplete and may contain errors. Modify it so that it produces the correct
 * output passes the unit tests in <code>WinnerTest.java</code>.
 *
 * @see <a href="https://cs125.cs.illinois.edu/MP/1/">MP1 Documentation</a>
 */
public class Winner {

    /**
     * Returns the three scores in descending order separated by commas.
     * <p>
     * For example, winner(0, 1, 2) should return "2, 1, 0". You can omit the whitespace if you
     * prefer, but the commas are necessary.
     * <p>
     * Write this function.
     *
     * @param firstScore an integer score.
     * @param secondScore an integer score.
     * @param thirdScore an integer score.
     * @return the three scores in descending order as a comma-delimited string
     */
    public static String winner(final int firstScore, final int secondScore, final int thirdScore) {
        // This is clearly incorrect
        return "";
    }

    /**********************************************************************************************
     * You do not need to modify code below this comment.
     **********************************************************************************************/

    /**
     * Number of scores to sort.
     * <p>
     * Do not change this value.
     */
    public static final int SCORE_COUNT = 3;

    /**
     * Solicits three integer from the user at the command line and prints them in descending order.
     * <p>
     * You are free to review this function, but should not modify it. Note that this function is
     * <i>not used or tested by the test suite</i> as it is purely to aid your own interactive
     * testing.
     *
     * @param unused unused input parameters.
     */
    @SuppressWarnings("resource")
    public static void main(final String[] unused) {
        String inputPrompt = String.format("Enter three integers on successive lines:");
        Scanner lineScanner = new Scanner(System.in);
        ArrayList<Integer> userInputs;

        restart : while (true) {
            System.out.println(inputPrompt);
            userInputs = new ArrayList<Integer>();

            for (int inputCount = 0; inputCount < SCORE_COUNT; inputCount++) {
                /*
                 * We could just use lineScanner.hasNextInt() and not initialize a separate scanner.
                 * But the default Scanner class ignores blank lines and continues to search for
                 * input until a non-empty line is entered. This approach allows us to detect empty
                 * lines and remind the user to provide a valid input.
                 */
                String nextLine = lineScanner.nextLine();
                Scanner inputScanner = new Scanner(nextLine);
                if (!(inputScanner.hasNextInt())) {
                    /*
                     * These should be printed as errors using System.err.println. Unfortunately,
                     * Eclipse can't keep System.out and System.err ordered properly.
                     */
                    System.out.println("Invalid input: please enter only integers.");
                    continue restart;
                }
                userInputs.add(inputScanner.nextInt());
                /*
                 * If the line started with an integer but contains other tokens, reinitialize
                 * userInput and prompt the user again.
                 */
                if (inputScanner.hasNext()) {
                    System.out.println("Invalid input: please enter only one integer per line.");
                    continue restart;
                }
                inputScanner.close();
            }
            System.out.println(winner(userInputs.get(0), userInputs.get(1), userInputs.get(2)));
        }
    }
}
