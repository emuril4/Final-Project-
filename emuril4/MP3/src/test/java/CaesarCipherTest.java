import java.io.FileReader;
import java.net.URI;
import java.security.InvalidParameterException;
import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test suite for the CaesarCipher class.
 * <p>
 * The provided test suite is correct and complete. You should not need to modify it. However, you
 * should understand it. You will need to augment or write test suites for later MPs.
 *
 * @see <a href="https://cs125.cs.illinois.edu/MP/3/">MP3 Documentation</a>
 */
public class CaesarCipherTest {

    /** Timeout for our tests. Solution runs in 0.04s. */
    private static final int TEST_TIMEOUT = 1000;

    /** JSON file with test inputs and results. */
    private static final String TEST_FIXTURE_FILE = "CaesarCipherTest.json";

    /**
     * Test encryption and decryption.
     */
    @Test(timeOut = TEST_TIMEOUT)
    public void testEncryptAndDecrypt() {
        JSONParser testFixturesParser = new JSONParser();

        String testFixturesPath = CaesarCipherTest.class.getClassLoader()
                .getResource(TEST_FIXTURE_FILE).getFile();

        JSONArray testFixtures;
        try {
            testFixturesPath = new URI(testFixturesPath).getPath();
            testFixtures = (JSONArray) testFixturesParser.parse(new FileReader(testFixturesPath));
        } catch (Exception e) {
            e.printStackTrace();
            throw new InvalidParameterException("Couldn't open " + TEST_FIXTURE_FILE);
        }

        for (int testFixtureIndex = 0; testFixtureIndex < testFixtures.size(); testFixtureIndex++) {
            JSONObject currentTestFixture = (JSONObject) testFixtures.get(testFixtureIndex);

            /*
             * Pull values from the JSON object.
             */
            char[] line = ((String) currentTestFixture.get("line")).toCharArray();
            boolean encrypt = (boolean) currentTestFixture.get("encrypt");
            int shift = ((Number) currentTestFixture.get("shift")).intValue();
            String result = (String) currentTestFixture.get("result");

            char[] expectedOutput = null;
            if (result != null) {
                expectedOutput = result.toCharArray();
                Assert.assertEquals(line.length, result.length());
            }
            char[] copyOfLine = line.clone();
            char[] transformedLine;
            if (encrypt) {
                transformedLine = CaesarCipher.encrypt(line, shift);
            } else {
                transformedLine = CaesarCipher.decrypt(line, shift);
            }
            if (transformedLine != null) {
                Assert.assertEquals(copyOfLine.length, transformedLine.length);
            }
            /*
             * Ensure that encrypt and decrypt do not modify the original array
             */
            Assert.assertFalse(line == transformedLine);
            Assert.assertTrue(Arrays.equals(line, copyOfLine));
            Assert.assertTrue(String.valueOf(copyOfLine).equals(String.valueOf(line)));

            /*
             * Check expected output
             */
            if (expectedOutput != null) {
                Assert.assertTrue(Arrays.equals(transformedLine, expectedOutput),
                        String.valueOf(transformedLine) + " != " + String.valueOf(expectedOutput));
            } else {
                Assert.assertTrue(Arrays.equals(transformedLine, expectedOutput));
            }
            /*
             * Stopping point for invalid values
             */
            if (expectedOutput == null) {
                continue;
            }
            Assert.assertTrue(String.valueOf(expectedOutput)
                    .equals(String.valueOf(transformedLine)));

            /*
             * Now undo the transformation
             */
            char[] copyOfTransformedLine = transformedLine.clone();
            char[] reformedLine;
            if (encrypt) {
                reformedLine = CaesarCipher.decrypt(transformedLine, shift);
            } else {
                reformedLine = CaesarCipher.encrypt(transformedLine, shift);
            }

            /*
             * Ensure that encrypt and decrypt do not modify the original array
             */
            Assert.assertFalse(line == reformedLine);
            Assert.assertTrue(Arrays.equals(line, copyOfLine));
            Assert.assertTrue(String.valueOf(copyOfLine).equals(String.valueOf(line)));
            Assert.assertFalse(transformedLine == reformedLine);
            Assert.assertTrue(Arrays.equals(transformedLine, copyOfTransformedLine));
            Assert.assertTrue(String.valueOf(copyOfTransformedLine)
                    .equals(String.valueOf(transformedLine)));

            /*
             * Check expected output. We should be back to the original string.
             */
            Assert.assertTrue(Arrays.equals(line, reformedLine));
            Assert.assertTrue(String.valueOf(reformedLine).equals(String.valueOf(line)));
        }
    }
}
