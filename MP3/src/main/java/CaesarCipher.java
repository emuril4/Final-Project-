import java.util.Scanner;

/**
 * A class that "encrypts" data through a simple transformation.
 * <p>
 * The provided code is incomplete. Modify it so that it works properly and passes the unit tests in
 * <code>CaesarCipherTest.java</code>.
 *
 * @see <a href="https://cs125.cs.illinois.edu/MP/3/">MP3 Documentation</a>
 * @see <a href="https://en.wikipedia.org/wiki/Caesar_cipher">Caesar Cipher Documentation</a>
 */
public class CaesarCipher {

    /** Minimum shift that encrypt and decrypt need to handle. */
    public static final int MIN_SHIFT = -1024;

    /** Maximum shift that encrypt and decrypt need to handle. */
    /**
     * @MAX_SHIFT : maximum number you can shift
     */
    // maximum shift that encrypt function can handle
    public static final int MAX_SHIFT = 1024;
    /**
     * @start : the begining values
     */
    // where the character values begin
    public static final int START = 32;
    /**
     * @difference : difference between the max and min
     */
    // the difference between 126 and 32
    public static final int DIFFERENCE = 95;
    /**
     * @end : max char value
     */
    public static final int END = 126;
    /**
     * Encrypt a single line of text using a rotate-N transformation.
     * <p>
     * The printable range of ASCII characters starts at decimal value 32 (' ') and ends at 126
     * ('~'). You should shift characters within this range by the shift value provided. For
     * example, ' ' (32) shift 1 becomes '!' (33), while '~' (126) shift 1 wraps around and becomes
     * ' ' (32). You may want to explore modular arithmetic to simplify the transformation.
     * <p>
     * Your function should return a new character array, not modify the one that it is passed.
     * <p>
     * Both encrypt and decrypt may receive invalid inputs. If the character array contains invalid
     * characters (outside of the range defined above), or if the shift value is outside the range
     * defined above (e.g., larger than MAX_SHIFT), you should return null.
     * <p>
     * <strong>Your solution must match the expected output exactly, otherwise you will not receive
     * //* credit.</strong>
     * <p>
     * Complete the Javadoc comment for this function and write it.
     *
     * @see <a href="http://www.asciitable.com/">ASCII Character Table</a>
     */
    /**
     * @param line : text
     * @param shift : the number of places to move the characters to encrypt
     * @return : should return null or the characters shifted by the shift amount
     */
    public static char[] encrypt(final char[] line, final int shift) {
        // if outside shift range return null
        char[] encrypted = new char[line.length];
        if (shift > MAX_SHIFT || shift < MIN_SHIFT) {
            return null;
        }

        int shiftNum = 0;
        for (int i = 0; i < line.length; i++) {
            char c = line[i];
            if (c <= END && c >= START) { // bounds
                if (shift > 0) {
                    shiftNum = (((c - START) + (shift % DIFFERENCE)) + START);
                    // holds the value that
                    // you will be shifting
                    // in the positive
                    // direction
                } else {
                    shiftNum = ((((c - START) + (shift % DIFFERENCE)
                            + DIFFERENCE)
                            % DIFFERENCE)
                            + START); // the value when negative shift is input
                }
            }
            encrypted[i] = (char) shiftNum;
        }
        return encrypted;
    }

    /*
     * Undoes the transformation performed by encrypt above. Add a Java document comment for this
     * function and complete it. It should also return a new character array, not modify the one
     * passed in.
     */
    /**
     * @param line : text input
     * @param shift : number by which encryption occurs
     * @return : returns the original input
     */
    public static char[] decrypt(final char[] line, final int shift) {
        return encrypt(line, -shift);
    }

    /**********************************************************************************************
     * You do not need to modify code below this comment.
     **********************************************************************************************/

    /**
     * Solicits a single line of text from the user, encrypts it using a random shift, and then
     * decrypts it.
     * <p>
     * You are free to review this function, but should not modify it. Note that this function is
     * not tested by the test suite, as it is purely to aid your own interactive testing.
     *
     * @param unused unused input arguments
     */
    @SuppressWarnings("resource")
    public static void main(final String[] unused) {

        String linePrompt = String.format("Enter a line of text, or a blank line to exit:");
        String shiftPrompt = String.format("Enter an integer to shift by:");

        /*
         * Two steps here: first get a line, then a shift integer.
         */

        Scanner lineScanner = new Scanner(System.in);
        repeat : while (true) {
            String line = null;
            Integer shift = null;

            System.out.println(linePrompt);
            while (lineScanner.hasNextLine()) {
                line = lineScanner.nextLine();
                if (line.equals("")) {
                    break repeat;
                } else {
                    break;
                }
            }

            System.out.println(shiftPrompt);
            while (lineScanner.hasNextLine()) {
                Scanner intScanner = new Scanner(lineScanner.nextLine());
                if (intScanner.hasNextInt()) {
                    shift = intScanner.nextInt();
                    if (intScanner.hasNext()) {
                        shift = null;
                        System.out.println("Invalid input: please enter only a single integer.");
                    }
                } else {
                    System.out.println("Invalid input: please enter an integer.");
                }
                intScanner.close();
                if (shift != null) {
                    break;
                }
            }

            if (line == null || line.equals("")) {
                throw new RuntimeException("Should have a line at this point");
            }
            if (shift == null) {
                throw new RuntimeException("Should have a shift value at this point");
            }

            char[] originalCharacterArray = line.toCharArray();
            char[] encryptedCharacterArray = encrypt(originalCharacterArray, shift);
            char[] decryptedCharacterArray;

            if (encryptedCharacterArray != null) {
                decryptedCharacterArray = decrypt(encryptedCharacterArray, shift);
            }

            System.out.println("Encrypted line with ROT-" + shift + ":");
            System.out.println("Original line:");
            System.out.println(String.valueOf(originalCharacterArray));

            if (encryptedCharacterArray != null) {
                System.out.println(String.valueOf(encryptedCharacterArray));
            } else {
                System.out.println("null");
            }
            System.out.println("Decrypted line:");
            if (encryptedCharacterArray != null) {
                decryptedCharacterArray = decrypt(encryptedCharacterArray, shift);
                if (decryptedCharacterArray != null) {
                    System.out.println(String.valueOf(decryptedCharacterArray));
                }
            } else {
                System.out.println("null");
            }
        }
        lineScanner.close();
    }
}
