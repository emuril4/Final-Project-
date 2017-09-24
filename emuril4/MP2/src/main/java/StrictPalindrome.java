import java.util.Scanner;

/**
 * A class that determines if a word is a palindrome.
 * <p>
 * The provided code is incomplete. Modify it so that it works properly and passes the unit tests in
 * <code>PalindromeTest.java</code>.
 *
 * @see <a href="https://cs125.cs.illinois.edu/MP/2/">MP2 Documentation</a>
 * @see <a href="https://en.wikipedia.org/wiki/Palindrome">Palindrome Definition</a>
 */
public class StrictPalindrome {

    /**
     * Determine if a word is a palindrome.
     * <p>
     * Your function should use strict matching of characters, case, and whitspace. So, for example,
     * you should consider "A man, a plan, a cam, a yak, a yam, a canal – Panama!" to _not_ be a
     * palindrome, "ABba" to not be a palindrome, while "A-B-B-A" is a palindrome.
     * <p>
     * Add the function declaration below and write this function. Call it isStrictPalindrome. It
     * should take a single String argument and return a boolean.
     *
     * @param word the word to search for
     * @return whether the word is a strict palindrome or not
     */

    /**********************************************************************************************
     * You do not have to modify code below this comment.
     **********************************************************************************************/

    /**
     * Solicits a single word from the user at the command line and return whether it is a strict
     * palindrome.
     * <p>
     * You are free to review this function, but should not modify it. Note that this function is
     * not tested by the test suite, as it is purely to aid your own interactive testing.
     *
     * Note that this function will not accept inputs that contain spaces, but your palindrome
     * function should handle them properly. If you consider this a limitation, feel free to modify
     * the code below so that it accepts multi-word inputs.
     *
     * @param unused unused input arguments
     */
    @SuppressWarnings("resource")
    public static void main(final String[] unused) {

        String inputPrompt = String.format("Enter an single word to test as a palindrome:");
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

            if (isStrictPalindrome(searchTerm)) {
                System.out.println(searchTerm + " is a palindrome!");
            } else {
                System.out.println(searchTerm + " is not a palindrome!");
            }
        }
    }
}
