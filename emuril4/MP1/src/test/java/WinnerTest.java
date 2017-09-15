import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test suite for the Winner class.
 * <p>
 * The provided test suite is correct and complete. You should not need to modify it. However, you
 * should understand it. You will need to augment or write test suites for later MPs.
 *
 * @see <a href="https://cs125.cs.illinois.edu/MP/1/">MP1 Documentation</a>
 */
@Test
public class WinnerTest {

    /** Pre-computed quiz scores to test against. */
    @SuppressWarnings("checkstyle:magicnumber")
    private static final Map<ArrayList<Integer>, String> PRECOMPUTED_RESULTS =
        new HashMap<ArrayList<Integer>, String>() {
        {
            put(new ArrayList<Integer>() {
                {
                    add(0);
                    add(1);
                    add(2);
                }
            }, "2, 1, 0");
            put(new ArrayList<Integer>() {
                {
                    add(8);
                    add(58);
                    add(62);
                }
            }, "62, 58, 8");
            put(new ArrayList<Integer>() {
                {
                    add(-10);
                    add(-8);
                    add(-6);
                }
            }, "-6, -8, -10");
            put(new ArrayList<Integer>() {
                {
                    add(34);
                    add(22);
                    add(9);
                }
            }, "34, 22, 9");
        }
    };

    /**
     * Test valid Factorial.factorial inputs with precomputed values.
     */
    @Test
    public void testValidInputs() {
        for (Map.Entry<ArrayList<Integer>, String> precomputedResult : PRECOMPUTED_RESULTS
                .entrySet()) {
            ArrayList<Integer> input = precomputedResult.getKey();
            String[] results = precomputedResult.getValue().split(",");
            for (int rotateCount = 0; rotateCount < Winner.SCORE_COUNT; rotateCount++) {
                String testResult = Winner.winner(input.get((0 + rotateCount) % Winner.SCORE_COUNT),
                        input.get((1 + rotateCount) % Winner.SCORE_COUNT),
                        input.get((2 + rotateCount) % Winner.SCORE_COUNT));
                String[] testResults = testResult.split(",");
                Assert.assertEquals(testResults.length, results.length,
                        "results is not the correct length");
                for (int testIndex = 0; testIndex < Winner.SCORE_COUNT; testIndex++) {
                    Assert.assertTrue(results[testIndex].equals(testResults[testIndex]));
                }
            }
        }
    }
}
