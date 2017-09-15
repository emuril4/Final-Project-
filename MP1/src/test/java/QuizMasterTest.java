import java.util.Map;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test suite for the QuizMaster class.
 * <p>
 * The provided test suite is correct and complete. You should not need to modify it. However, you
 * should understand it. You will need to augment or write test suites for later MPs.
 *
 * @see <a href="https://cs125.cs.illinois.edu/MP/1/">MP1 Documentation</a>
 */
@Test
public class QuizMasterTest {

        /** Pre-computed quiz scores to test against. */
    @SuppressWarnings("checkstyle:magicnumber")
    private static final Map<QuizAnswer, Integer> PRECOMPUTED_SCORES =
        new HashMap<QuizAnswer, Integer>() {
        {
            put(new QuizAnswer(14213, false, false, false, false), 0);
            put(new QuizAnswer(48103, false, false, false, false), 0);
            put(new QuizAnswer(61820, false, false, false, false), 5);

            put(new QuizAnswer(2138, true, false, false, false), 10);
            put(new QuizAnswer(48109, false, true, false, false), 0);
            put(new QuizAnswer(61801, false, false, true, false), 15);

            put(new QuizAnswer(2138, true, true, true, false), 30);
            put(new QuizAnswer(48107, true, true, false, true), 0);
            put(new QuizAnswer(61803, true, false, true, true), 35);

            put(new QuizAnswer(2138, true, true, true, true), 40);
            put(new QuizAnswer(48107, true, true, true, true), 40);
            put(new QuizAnswer(61803, true, true, true, true), 40);
        }
    };

    /**
     * Test valid Factorial.factorial inputs with precomputed values.
     */
    @Test
    public void testValidInputs() {
        for (Map.Entry<QuizAnswer, Integer> precomputedScore : PRECOMPUTED_SCORES.entrySet()) {
            QuizAnswer input = precomputedScore.getKey();
            int score = precomputedScore.getValue();
            Assert.assertEquals(
                    QuizMaster.computeScore(input.zipCode, input.diversityAnswerCorrect,
                            input.illiacAnswerCorrect, input.mosaicAnswerCorrect,
                            input.variableAnswerCorrect),
                    score, String.format("Score for %s is incorrect", input.toString()));
        }
    }

    /**
     * Group of quiz answers for testing.
     */
    @SuppressWarnings("checkstyle:visibilitymodifier")
    public static class QuizAnswer {

        /** The answer's ZIP code. */
        public final int zipCode;

        /** Whether the diversity answer is correct. */
        public final Boolean diversityAnswerCorrect;

        /** Whether the ILLIAC answer is correct. */
        public final Boolean illiacAnswerCorrect;

        /** Whether the mosaic answer is correct. */
        public final Boolean mosaicAnswerCorrect;

        /** Whether the variable answer is correct. */
        public final Boolean variableAnswerCorrect;

        /**
         * Create a new quiz answer.
         *
         * @param setZipCode the ZIP code
         * @param setDiversityAnswerCorrect whether the diversity answer is correct
         * @param setIlliacAnswerCorrect whether the ILLIAC answer is correct
         * @param setMosaicAnswerCorrect whether the Mosaic answer is correct
         * @param setVariableAnswerCorrect whether the variable answer is correct
         */
        public QuizAnswer(final int setZipCode, final Boolean setDiversityAnswerCorrect,
                final Boolean setIlliacAnswerCorrect, final Boolean setMosaicAnswerCorrect,
                final Boolean setVariableAnswerCorrect) {
            super();
            this.zipCode = setZipCode;
            this.diversityAnswerCorrect = setDiversityAnswerCorrect;
            this.illiacAnswerCorrect = setIlliacAnswerCorrect;
            this.mosaicAnswerCorrect = setMosaicAnswerCorrect;
            this.variableAnswerCorrect = setVariableAnswerCorrect;
        }

        @Override
        public final String toString() {
            return "QuizAnswer [zipCode=" + zipCode + ", diversityAnswerCorrect="
                    + diversityAnswerCorrect + ", illiacAnswerCorrect=" + illiacAnswerCorrect
                    + ", mosaicAnswerCorrect=" + mosaicAnswerCorrect + ", variableAnswerCorrect="
                    + variableAnswerCorrect + "]";
        }

        @Override
        @SuppressWarnings("checkstyle:avoidinlineconditionals")
        public final int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result
                    + ((diversityAnswerCorrect == null) ? 0 : diversityAnswerCorrect.hashCode());
            result = prime * result
                    + ((illiacAnswerCorrect == null) ? 0 : illiacAnswerCorrect.hashCode());
            result = prime * result
                    + ((mosaicAnswerCorrect == null) ? 0 : mosaicAnswerCorrect.hashCode());
            result = prime * result
                    + ((variableAnswerCorrect == null) ? 0 : variableAnswerCorrect.hashCode());
            result = prime * result + zipCode;
            return result;
        }

        @Override
        public final boolean equals(final Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            QuizAnswer other = (QuizAnswer) obj;
            if (diversityAnswerCorrect == null) {
                if (other.diversityAnswerCorrect != null) {
                    return false;
                }
            } else if (!diversityAnswerCorrect.equals(other.diversityAnswerCorrect)) {
                return false;
            }
            if (illiacAnswerCorrect == null) {
                if (other.illiacAnswerCorrect != null) {
                    return false;
                }
            } else if (!illiacAnswerCorrect.equals(other.illiacAnswerCorrect)) {
                return false;
            }
            if (mosaicAnswerCorrect == null) {
                if (other.mosaicAnswerCorrect != null) {
                    return false;
                }
            } else if (!mosaicAnswerCorrect.equals(other.mosaicAnswerCorrect)) {
                return false;
            }
            if (variableAnswerCorrect == null) {
                if (other.variableAnswerCorrect != null) {
                    return false;
                }
            } else if (!variableAnswerCorrect.equals(other.variableAnswerCorrect)) {
                return false;
            }
            if (zipCode != other.zipCode) {
                return false;
            }
            return true;
        }
    }
}
