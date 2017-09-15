import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test suite for the Factorial class.
 * <p>
 * The provided test suite is correct and complete. You should not need to modify it. However, you
 * should understand it. You will need to augment or write test suites for later MPs.
 *
 * @see <a href="https://cs125.cs.illinois.edu/MP/1/">MP1 Documentation</a>
 */
@Test
public class FactorialTest {
    /**
     * Static HashMap holding precomputed factorial values for testing.
     */
    @SuppressWarnings("checkstyle:magicnumber")
    private static final Map<Integer, Long> PRECOMPUTED_FACTORIALS = new HashMap<Integer, Long>() {
        {
            /*
             * Make sure that we test the currently-defined minimum and maximum. If you change them,
             * you will need to update these precomputed values.
             */
            put(Factorial.FACTORIAL_MIN, 1L);
            put(1, 1L);
            put(2, 2L);
            put(4, 24L);
            put(8, 40320L);
            put(16, 20922789888000L);
            put(Factorial.FACTORIAL_MAX, 2432902008176640000L);
        }
    };

    /**
     * Test valid Factorial.factorial inputs with precomputed values.
     */
    @Test
    public void testValidInputs() {
        for (Map.Entry<Integer, Long> precomputedFactorial : PRECOMPUTED_FACTORIALS.entrySet()) {
            Integer input = precomputedFactorial.getKey();
            Long output = precomputedFactorial.getValue();
            Assert.assertEquals(Long.valueOf(Factorial.factorial(input)), output,
                    String.format("Factorial of %d is incorrect", input));
        }
    }

    /**
     * Test invalid Factorial.factorial inputs.
     */
    @Test
    public void testInvalidInputs() {
        /*
         * Ensure that not everything returns an error before checking bounds.
         */
        Assert.assertNotEquals(Long.valueOf(Factorial.factorial(Factorial.FACTORIAL_MIN)),
                Long.valueOf(Factorial.FACTORIAL_INVALID),
                String.format("Factorial of %d is incorrect", Factorial.FACTORIAL_MIN));
        /*
         * Values that are out of bounds or known to be bad should return errors.
         */
        for (Integer input : Arrays.asList(Factorial.FACTORIAL_MIN - 1, Factorial.FACTORIAL_MAX + 1,
                -1)) {
            Assert.assertEquals(Long.valueOf(Factorial.factorial(input)),
                    Long.valueOf(Factorial.FACTORIAL_INVALID), String.format(
                            "Factorial of %d is out of bounds but did not return an error", input));
        }
    }

    /**
     * Sanity check to make sure that limits did not change.
     */
    @Test
    @SuppressWarnings("checkstyle:magicnumber")
    public void testMaximumAndMinimum() {
        Assert.assertEquals(Factorial.FACTORIAL_MIN, 0);
        Assert.assertEquals(Factorial.FACTORIAL_MAX, 20);
        Assert.assertEquals(Factorial.FACTORIAL_INVALID, -1);
    }
}
