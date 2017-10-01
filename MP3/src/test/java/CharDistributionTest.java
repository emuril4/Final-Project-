import java.io.FileReader;
import java.net.URI;
import java.security.InvalidParameterException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test suite for the CharacterCounter class.
 * <p>
 * The provided test suite is correct and complete. You should not need to modify it. However, you
 * should understand it. You will need to augment or write test suites for later MPs.
 *
 * @see <a href="https://cs125.cs.illinois.edu/MP/3/">MP3 Documentation</a>
 */
public class CharDistributionTest {

    /** Timeout for our tests. Solution runs in 0.3s. */
    private static final int TEST_TIMEOUT = 3000;

    /** JSON file with test inputs and results. */
    private static final String TEST_FIXTURE_FILE = "CharacterDistributionTest.json";

    /** Float point equality threshold. */
    private static final double EPSILON = 0.000001;

    /**
     * Compare two double values with fixed precision.
     * <p>
     * Direct comparison of two doubles will often fail due to small differences. This avoids that
     * problem.
     *
     * @param firstDouble the first double to compare
     * @param secondDouble the second double to compare
     * @return whether the two doubles are the same to within EPSILON
     */
    private boolean doubleEquals(final double firstDouble, final double secondDouble) {
        return (Math.abs(firstDouble - secondDouble) < EPSILON);
    }
    /**
     * Test character distributions.
     */
    @Test(timeOut = TEST_TIMEOUT)
    public void testEncryptAndDecrypt() {
        JSONParser testFixturesParser = new JSONParser();

        String testFixturesPath = CharDistributionTest.class.getClassLoader()
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
            String text = (String) currentTestFixture.get("text");
            JSONArray distributionArray = (JSONArray) currentTestFixture.get("distribution");
            double[] expectedDistribution = new double[distributionArray.size()];
            for (int distributionIndex = 0; distributionIndex < distributionArray.size(); //
                    distributionIndex++) {
                expectedDistribution[distributionIndex] = //
                        ((Number) distributionArray.get(distributionIndex)).doubleValue();
            }

            double[] distribution = CharDistribution.computeDistribution(text);
            Assert.assertEquals(distribution.length, expectedDistribution.length);
            for (int distributionIndex = 0; distributionIndex < distribution.length; //
                    distributionIndex++) {
                Assert.assertTrue(doubleEquals(distribution[distributionIndex],
                        expectedDistribution[distributionIndex]),
                        distribution[distributionIndex] + " != "
                                + expectedDistribution[distributionIndex]);
            }
        }
    }
}
