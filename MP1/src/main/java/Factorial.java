import java.util.Scanner;

/**
 * A class that computes the factorial of a given number.
 * <p>
 * The provided code is incomplete and may contain errors. Modify it so that it calculates factorial
 * correctly and passes the unit tests in <code>FactorialTest.java</code>.
 *
 * @see <a href="https://cs125.cs.illinois.edu/MP/1/">MP1 Documentation</a>
 */
public final class Factorial {
    /**
     * Minimum valid factorial input.
     * <p>
     * Do not change this value.
     */
    public static final int FACTORIAL_MIN = 0;

    /**
     * Maximum valid factorial input.
     * <p>
     * Do not change this value.
     */
    public static final int FACTORIAL_MAX = 20;

    /**
     * Invalid factorial return value.
     * <p>
     * Do not change this value.
     */
    public static final int FACTORIAL_INVALID = -1;

    /**
     * Returns the factorial of a value.
     * <p>
     * This function should ensure that its input respects the bounds defined above. If the input is
     * too small or too large, it should return FACTORIAL_INVALID. Note that you may need to change
     * the return type (currently int) of this function.
     * <p>
     * Write this function.
     *
     * @param input an integer.
     * @return the factorial of input if it is valid, or FACTORIAL_INVALID if it is not.
     * @see <a href="https://en.wikipedia.org/wiki/Factorial">Factorial Definition</a>
     */
    public static int factorial(final int input) {
        return FACTORIAL_INVALID;
    }

    /**********************************************************************************************
     * You do not need to modify code below this comment.
     **********************************************************************************************/

    /**
     * Solicits an integer from the user at the command line and tries to compute its factorial.
     * <p>
     * You are free to review this function, but should not modify it. Note that this function is
     * not tested by the test suite, as it is purely to aid your own interactive testing.
     *
     * @param unused unused input parameters.
     */
    public static void main(final String[] unused) {
        String inputPrompt = String.format("Enter an integer between %d and %d, inclusive:",
                FACTORIAL_MIN, FACTORIAL_MAX);
        Scanner lineScanner = new Scanner(System.in);

        while (true) {
            int userInput = -1;
            System.out.println(inputPrompt);

            /*
             * We could just use lineScanner.hasNextInt() and not initialize a separate scanner. But
             * the default Scanner class ignores blank lines and continues to search for input until
             * a non-empty line is entered. This approach allows us to detect empty lines and remind
             * the user to provide a valid input.
             */
            String nextLine = lineScanner.nextLine();
            Scanner inputScanner = new Scanner(nextLine);
            if (!(inputScanner.hasNextInt())) {
                /*
                 * These should be printed as errors using System.err.println. Unfortunately,
                 * Eclipse can't keep System.out and System.err ordered properly.
                 */
                System.out.println("Invalid input: please enter an integer.");
                continue;
            }
            userInput = inputScanner.nextInt();
            /*
             * If the line started with an integer but contains other tokens, reinitialize userInput
             * and prompt the user again.
             */
            if (inputScanner.hasNext()) {
                System.out.println("Invalid input: please enter only an integer.");
                continue;
            }
            inputScanner.close();
            Long result = Long.valueOf(Factorial.factorial(userInput));
            if (result == FACTORIAL_INVALID) {
                System.out.println(String.format(
                        "Invalid input: please enter an integer between %d and %d, inclusive:",
                        FACTORIAL_MIN, FACTORIAL_MAX));
                continue;
            }
            String outputString = String.format("The factorial of %d is %d.", userInput, result);
            System.out.println(outputString);
            break;
        }
        lineScanner.close();
    }
}
