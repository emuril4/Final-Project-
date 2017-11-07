// CHECKSTYLE:OFF:FileLength
import java.util.ArrayList;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import junit.framework.Assert;

/**
 * Test suite for the Tree class.
 * <p>
 * The provided test suite is correct and complete. You should not need to modify it. However, you
 * should understand it. You may need to augment or write test suites for later MPs.
 *
 * @see <a href="https://cs125.cs.illinois.edu/MP/6/">MP6 Documentation</a>
 */
@SuppressWarnings("checkstyle:magicnumber")
public class TreeTest {

    // TODO
    /** Timeout for tree tests. */
    private static final int TREE_TEST_TIMEOUT = 1000;

    /**
     * Create a tree from an array of values.
     * <p>
     * Helper function for the testing suite.
     *
     * @param treeTestInput the input to create a tree from
     * @return the tree that was created, or null if creation fails
     */
    private Tree doCreate(final TreeTestInput treeTestInput) {
        boolean createSucceeded = true;
        Tree testTree = null;
        for (int insert : treeTestInput.values) {
            if (testTree == null) {
                testTree = new Tree(insert);
            } else {
                createSucceeded = testTree.insert(insert);
                if (!createSucceeded) {
                    break;
                }
            }
        }
        if (createSucceeded) {
            return testTree;
        } else {
            return null;
        }
    }

    /**
     * Test tree creation.
     */
    @Test(timeOut = TREE_TEST_TIMEOUT, groups = {"create"})
    public void testCreate() {
        for (TreeTestInput testInput : GOOD_TREES) {
            Assert.assertNotNull(doCreate(testInput));
        }
        for (TreeTestInput testInput : BAD_TREES) {
            Assert.assertNull(doCreate(testInput));
        }
    }

    /**
     * Test tree minimum and maximum.
     */
    @Test(timeOut = TREE_TEST_TIMEOUT, dependsOnGroups = {"create.*"})
    public void testMinAndMax() {
        for (TreeTestInput testInput : GOOD_TREES) {
            Tree tree = doCreate(testInput);
            Assert.assertNotNull(tree);
            Assert.assertEquals(testInput.maximum, tree.maximum());
            Assert.assertEquals(testInput.minimum, tree.minimum());
        }
    }

    /**
     * Test tree searches.
     */
    @Test(timeOut = TREE_TEST_TIMEOUT, dependsOnGroups = {"create.*"})
    public void testSearch() {
        for (TreeTestInput testInput : GOOD_TREES) {
            Tree tree = doCreate(testInput);
            Assert.assertNotNull(tree);
            for (Map.Entry<Integer, Boolean> precomputedSearchResult : //
            testInput.searchResults.entrySet()) {
                Tree result = tree.search(precomputedSearchResult.getKey());
                Assert.assertEquals(precomputedSearchResult.getValue().booleanValue(),
                        (result != null));
            }
        }
    }

    /**
     * Test tree node depth.
     */
    @Test(timeOut = TREE_TEST_TIMEOUT, dependsOnGroups = {"create.*"})
    public void testDepth() {
        for (TreeTestInput testInput : GOOD_TREES) {
            Tree tree = doCreate(testInput);
            Assert.assertNotNull(tree);
            for (Map.Entry<Integer, Integer> precomputedDepthResult : //
            testInput.depthResults.entrySet()) {
                Tree result = tree.search(precomputedDepthResult.getKey());
                Assert.assertNotNull(result);
                Assert.assertEquals(precomputedDepthResult.getValue().intValue(), result.depth());
            }
        }
    }

    /**
     * Test tree node descendants.
     */
    @Test(timeOut = TREE_TEST_TIMEOUT, dependsOnGroups = {"create.*"})
    public void testDescendants() {
        for (TreeTestInput testInput : GOOD_TREES) {
            Tree tree = doCreate(testInput);
            Assert.assertNotNull(tree);
            for (Map.Entry<Integer, Integer> precomputedDescendantResult : //
            testInput.descendantResults.entrySet()) {
                Tree result = tree.search(precomputedDescendantResult.getKey());
                Assert.assertNotNull(result);
                Assert.assertEquals(precomputedDescendantResult.getValue().intValue(),
                        result.descendants());
            }
        }
    }

    /**
     * Class for storing trees and precomputed results for the tree tests.
     */
    @SuppressWarnings("checkstyle:visibilitymodifier")
    public static class TreeTestInput {

        /** Ordered list of values to insert. */
        public int[] values;

        /** Pre-computed maximum value in this tree. */
        public int maximum;

        /** Pre-computed minimum value in this tree. */
        public int minimum;

        /** Pre-computed search results. */
        public Map<Integer, Boolean> searchResults;

        /** Pre-computed depth results. */
        public Map<Integer, Integer> depthResults;

        /** Pre-computed descendant results. */
        public Map<Integer, Integer> descendantResults;

        /**
         * Create a new tree testing input.
         *
         * @param setValues ordered list of values to insert
         */
        public TreeTestInput(final int[] setValues) {
            values = setValues;
        }

        /**
         * Create a new tree testing input with precomputed values.
         *
         * @param setValues ordered list of values to insert
         * @param setMaximum precomputed maximum value
         * @param setMinimum precomputed minimum value
         * @param setSearchResults precomputed search results
         * @param setDepthResults precomputed depth results
         * @param setDescendantResults precomputed descendant results
         */
        public TreeTestInput(final int[] setValues, final int setMaximum, final int setMinimum,
                final LinkedHashMap<Integer, Boolean> setSearchResults,
                final LinkedHashMap<Integer, Integer> setDepthResults,
                final LinkedHashMap<Integer, Integer> setDescendantResults) {
            values = setValues;
            maximum = setMaximum;
            minimum = setMinimum;
            searchResults = setSearchResults;
            depthResults = setDepthResults;
            descendantResults = setDescendantResults;
        }
    }

    /** Pre-computed list of trees to use for testing. */
    private static final List<TreeTestInput> GOOD_TREES = //
            new ArrayList<TreeTestInput>();

    /** Pre-computed bad trees with duplicate values. */
    private static final List<TreeTestInput> BAD_TREES = //
            new ArrayList<TreeTestInput>();

    static {
        GOOD_TREES.add(new TreeTestInput(new int[]{1}, 1, 1,
                new LinkedHashMap<Integer, Boolean>() {
                    {
                        put(1, true);
                        put(2, false);
                        put(0, false);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(1, 0);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(1, 0);
                    }
                }));
        GOOD_TREES.add(new TreeTestInput(new int[]{2, 1, 3}, 3, 1,
                new LinkedHashMap<Integer, Boolean>() {
                    {
                        put(2, true);
                        put(5, false);
                        put(-1, false);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(2, 0);
                        put(1, 1);
                        put(3, 1);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(2, 2);
                        put(1, 0);
                        put(3, 0);
                    }
                }));
        GOOD_TREES.add(new TreeTestInput(new int[]{1, 2, 3, 4, 5}, 5, 1,
                new LinkedHashMap<Integer, Boolean>() {
                    {
                        put(2, true);
                        put(5, true);
                        put(10, false);
                        put(6, false);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(1, 0);
                        put(2, 1);
                        put(3, 2);
                        put(4, 3);
                        put(5, 4);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(1, 4);
                        put(2, 3);
                        put(3, 2);
                        put(4, 1);
                        put(5, 0);
                    }
                }));
        /* BEGIN AUTOGENERATED CODE */
        GOOD_TREES.add(new TreeTestInput(new int[]{
                -986,
                                -497,
                                -12,
                                -580,
                                1006,
                                -561,
                                69,
                                -642,
                                292,
                                55,
                                -881,
                                592,
                                -926,
                                270,
                                236,
                                -798,
                                -321,
                                647,
                                616,
                                -530,
                                31,
                                970,
                                74,
                                144,
                                436,
                                661,
                                -492,
                                502,
                                198,
                                -418,
                                -786,
                                468,
                                709,
                                -235,
                                413,
                                -876,
                                192,
                                -466,
                                -851,
                                564,
                                -571,
                                -738,
                                15,
                                339,
                                -192,
                                478,
                                -7,
                                -988,
                                -209,
                                -913,
                                833,
                                997,
                                -234,
                                314,
                                -409
                 }, 1006, -988,
                new LinkedHashMap<Integer, Boolean>() {
                    {
                        put(-416, false);
                        put(-543, false);
                        put(-192, true);
                        put(-872, false);
                        put(-209, true);
                        put(-209, true);
                        put(-913, true);
                        put(-571, true);
                        put(999, false);
                        put(69, true);
                        put(-235, true);
                        put(964, false);
                        put(401, false);
                        put(-497, true);
                        put(-466, true);
                        put(-816, false);
                        put(-881, true);
                        put(-667, false);
                        put(-331, false);
                        put(-466, true);
                        put(564, true);
                        put(96, false);
                        put(349, false);
                        put(-359, false);
                        put(314, true);
                        put(982, false);
                        put(-986, true);
                        put(-61, false);
                        put(-498, false);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(-986, 0);
                        put(-497, 1);
                        put(-12, 2);
                        put(-580, 2);
                        put(1006, 3);
                        put(-561, 3);
                        put(69, 4);
                        put(-642, 3);
                        put(292, 5);
                        put(55, 5);
                        put(-881, 4);
                        put(592, 6);
                        put(-926, 5);
                        put(270, 6);
                        put(236, 7);
                        put(-798, 5);
                        put(-321, 3);
                        put(647, 7);
                        put(616, 8);
                        put(-530, 4);
                        put(31, 6);
                        put(970, 8);
                        put(74, 8);
                        put(144, 9);
                        put(436, 7);
                        put(661, 9);
                        put(-492, 4);
                        put(502, 8);
                        put(198, 10);
                        put(-418, 5);
                        put(-786, 6);
                        put(468, 9);
                        put(709, 10);
                        put(-235, 4);
                        put(413, 8);
                        put(-876, 6);
                        put(192, 11);
                        put(-466, 6);
                        put(-851, 7);
                        put(564, 9);
                        put(-571, 4);
                        put(-738, 7);
                        put(15, 7);
                        put(339, 9);
                        put(-192, 5);
                        put(478, 10);
                        put(-7, 8);
                        put(-988, 1);
                        put(-209, 6);
                        put(-913, 6);
                        put(833, 11);
                        put(997, 9);
                        put(-234, 7);
                        put(314, 10);
                        put(-409, 6);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(-986, 54);
                        put(-497, 52);
                        put(-12, 38);
                        put(-580, 12);
                        put(1006, 28);
                        put(-561, 2);
                        put(69, 27);
                        put(-642, 8);
                        put(292, 22);
                        put(55, 3);
                        put(-881, 7);
                        put(592, 15);
                        put(-926, 1);
                        put(270, 5);
                        put(236, 4);
                        put(-798, 4);
                        put(-321, 8);
                        put(647, 6);
                        put(616, 0);
                        put(-530, 0);
                        put(31, 2);
                        put(970, 4);
                        put(74, 3);
                        put(144, 2);
                        put(436, 7);
                        put(661, 2);
                        put(-492, 3);
                        put(502, 3);
                        put(198, 1);
                        put(-418, 2);
                        put(-786, 1);
                        put(468, 1);
                        put(709, 1);
                        put(-235, 3);
                        put(413, 2);
                        put(-876, 1);
                        put(192, 0);
                        put(-466, 0);
                        put(-851, 0);
                        put(564, 0);
                        put(-571, 0);
                        put(-738, 0);
                        put(15, 1);
                        put(339, 1);
                        put(-192, 2);
                        put(478, 0);
                        put(-7, 0);
                        put(-988, 0);
                        put(-209, 1);
                        put(-913, 0);
                        put(833, 0);
                        put(997, 0);
                        put(-234, 0);
                        put(314, 0);
                        put(-409, 0);
                    }
                }));
        GOOD_TREES.add(new TreeTestInput(new int[]{
                566,
                                727,
                                -837,
                                -59,
                                243,
                                -208,
                                66,
                                -588,
                                592,
                                575,
                                658,
                                -1005,
                                -611,
                                462,
                                -46,
                                600,
                                330,
                                -606,
                                -963,
                                830,
                                1019,
                                465,
                                112,
                                872,
                                298,
                                155,
                                -664,
                                -358,
                                -182,
                                628,
                                341,
                                -854,
                                205,
                                410,
                                869,
                                424,
                                -722,
                                -595,
                                987,
                                -20,
                                681,
                                -82,
                                -112,
                                148,
                                -124,
                                877,
                                106,
                                -865,
                                -766,
                                583,
                                -171,
                                161,
                                297,
                                499,
                                -574,
                                -145,
                                922,
                                -374,
                                -11,
                                527,
                                361,
                                -717
                 }, 1019, -1005,
                new LinkedHashMap<Integer, Boolean>() {
                    {
                        put(727, true);
                        put(-991, false);
                        put(-150, false);
                        put(-22, false);
                        put(297, true);
                        put(149, false);
                        put(-299, false);
                        put(658, true);
                        put(148, true);
                        put(747, false);
                        put(933, false);
                        put(-606, true);
                        put(-759, false);
                        put(988, false);
                        put(-20, true);
                        put(155, true);
                        put(472, false);
                        put(-59, true);
                        put(901, false);
                        put(243, true);
                        put(-208, true);
                        put(731, false);
                        put(330, true);
                        put(-609, false);
                        put(626, false);
                        put(727, true);
                        put(832, false);
                        put(826, false);
                        put(243, true);
                        put(-664, true);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(566, 0);
                        put(727, 1);
                        put(-837, 1);
                        put(-59, 2);
                        put(243, 3);
                        put(-208, 3);
                        put(66, 4);
                        put(-588, 4);
                        put(592, 2);
                        put(575, 3);
                        put(658, 3);
                        put(-1005, 2);
                        put(-611, 5);
                        put(462, 4);
                        put(-46, 5);
                        put(600, 4);
                        put(330, 5);
                        put(-606, 6);
                        put(-963, 3);
                        put(830, 2);
                        put(1019, 3);
                        put(465, 5);
                        put(112, 5);
                        put(872, 4);
                        put(298, 6);
                        put(155, 6);
                        put(-664, 6);
                        put(-358, 5);
                        put(-182, 4);
                        put(628, 5);
                        put(341, 6);
                        put(-854, 4);
                        put(205, 7);
                        put(410, 7);
                        put(869, 5);
                        put(424, 8);
                        put(-722, 7);
                        put(-595, 7);
                        put(987, 5);
                        put(-20, 6);
                        put(681, 4);
                        put(-82, 5);
                        put(-112, 6);
                        put(148, 7);
                        put(-124, 7);
                        put(877, 6);
                        put(106, 6);
                        put(-865, 5);
                        put(-766, 8);
                        put(583, 4);
                        put(-171, 8);
                        put(161, 8);
                        put(297, 7);
                        put(499, 6);
                        put(-574, 6);
                        put(-145, 9);
                        put(922, 7);
                        put(-374, 7);
                        put(-11, 7);
                        put(527, 7);
                        put(361, 8);
                        put(-717, 8);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(566, 61);
                        put(727, 14);
                        put(-837, 45);
                        put(-59, 40);
                        put(243, 21);
                        put(-208, 17);
                        put(66, 9);
                        put(-588, 10);
                        put(592, 6);
                        put(575, 1);
                        put(658, 3);
                        put(-1005, 3);
                        put(-611, 6);
                        put(462, 10);
                        put(-46, 2);
                        put(600, 1);
                        put(330, 6);
                        put(-606, 1);
                        put(-963, 2);
                        put(830, 6);
                        put(1019, 5);
                        put(465, 2);
                        put(112, 5);
                        put(872, 4);
                        put(298, 1);
                        put(155, 3);
                        put(-664, 3);
                        put(-358, 2);
                        put(-182, 5);
                        put(628, 0);
                        put(341, 3);
                        put(-854, 1);
                        put(205, 1);
                        put(410, 2);
                        put(869, 0);
                        put(424, 0);
                        put(-722, 2);
                        put(-595, 0);
                        put(987, 2);
                        put(-20, 1);
                        put(681, 0);
                        put(-82, 4);
                        put(-112, 3);
                        put(148, 0);
                        put(-124, 2);
                        put(877, 1);
                        put(106, 0);
                        put(-865, 0);
                        put(-766, 0);
                        put(583, 0);
                        put(-171, 1);
                        put(161, 0);
                        put(297, 0);
                        put(499, 1);
                        put(-574, 1);
                        put(-145, 0);
                        put(922, 0);
                        put(-374, 0);
                        put(-11, 0);
                        put(527, 0);
                        put(361, 0);
                        put(-717, 0);
                    }
                }));
        GOOD_TREES.add(new TreeTestInput(new int[]{
                934,
                                -528,
                                -575,
                                -794,
                                873,
                                842,
                                -861,
                                -123,
                                -289,
                                -309,
                                -68,
                                386,
                                -360,
                                -578,
                                746,
                                405,
                                -512
                 }, 934, -861,
                new LinkedHashMap<Integer, Boolean>() {
                    {
                        put(809, false);
                        put(842, true);
                        put(405, true);
                        put(215, false);
                        put(-68, true);
                        put(66, false);
                        put(-639, false);
                        put(-120, false);
                        put(-861, true);
                        put(746, true);
                        put(-871, false);
                        put(386, true);
                        put(-226, false);
                        put(-327, false);
                        put(873, true);
                        put(231, false);
                        put(-575, true);
                        put(-848, false);
                        put(-289, true);
                        put(-54, false);
                        put(-413, false);
                        put(-289, true);
                        put(-156, false);
                        put(-68, true);
                        put(-578, true);
                        put(-794, true);
                        put(520, false);
                        put(198, false);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(934, 0);
                        put(-528, 1);
                        put(-575, 2);
                        put(-794, 3);
                        put(873, 2);
                        put(842, 3);
                        put(-861, 4);
                        put(-123, 4);
                        put(-289, 5);
                        put(-309, 6);
                        put(-68, 5);
                        put(386, 6);
                        put(-360, 7);
                        put(-578, 4);
                        put(746, 7);
                        put(405, 8);
                        put(-512, 8);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(934, 16);
                        put(-528, 15);
                        put(-575, 3);
                        put(-794, 2);
                        put(873, 10);
                        put(842, 9);
                        put(-861, 0);
                        put(-123, 8);
                        put(-289, 3);
                        put(-309, 2);
                        put(-68, 3);
                        put(386, 2);
                        put(-360, 1);
                        put(-578, 0);
                        put(746, 1);
                        put(405, 0);
                        put(-512, 0);
                    }
                }));
        GOOD_TREES.add(new TreeTestInput(new int[]{
                -150,
                                -540,
                                -599,
                                829,
                                873,
                                -157,
                                -65,
                                874,
                                -241,
                                -466,
                                -183,
                                -565,
                                -145
                 }, 874, -599,
                new LinkedHashMap<Integer, Boolean>() {
                    {
                        put(873, true);
                        put(-398, false);
                        put(829, true);
                        put(-183, true);
                        put(-396, false);
                        put(873, true);
                        put(-565, true);
                        put(-241, true);
                        put(-298, false);
                        put(1013, false);
                        put(205, false);
                        put(-466, true);
                        put(-729, false);
                        put(532, false);
                        put(300, false);
                        put(873, true);
                        put(946, false);
                        put(-183, true);
                        put(-157, true);
                        put(335, false);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(-150, 0);
                        put(-540, 1);
                        put(-599, 2);
                        put(829, 1);
                        put(873, 2);
                        put(-157, 2);
                        put(-65, 2);
                        put(874, 3);
                        put(-241, 3);
                        put(-466, 4);
                        put(-183, 4);
                        put(-565, 3);
                        put(-145, 3);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(-150, 12);
                        put(-540, 6);
                        put(-599, 1);
                        put(829, 4);
                        put(873, 1);
                        put(-157, 3);
                        put(-65, 1);
                        put(874, 0);
                        put(-241, 2);
                        put(-466, 0);
                        put(-183, 0);
                        put(-565, 0);
                        put(-145, 0);
                    }
                }));
        GOOD_TREES.add(new TreeTestInput(new int[]{
                99,
                                -365,
                                137,
                                544,
                                100,
                                302,
                                -1010,
                                -235,
                                -234,
                                128,
                                358,
                                373,
                                155,
                                -304,
                                -144,
                                835
                 }, 835, -1010,
                new LinkedHashMap<Integer, Boolean>() {
                    {
                        put(100, true);
                        put(-797, false);
                        put(-234, true);
                        put(-304, true);
                        put(698, false);
                        put(373, true);
                        put(-840, false);
                        put(-942, false);
                        put(-234, true);
                        put(638, false);
                        put(222, false);
                        put(835, true);
                        put(-234, true);
                        put(-1010, true);
                        put(-294, false);
                        put(29, false);
                        put(177, false);
                        put(-144, true);
                        put(-891, false);
                        put(-365, true);
                        put(971, false);
                        put(939, false);
                        put(-58, false);
                        put(-515, false);
                        put(302, true);
                        put(-365, true);
                        put(155, true);
                        put(286, false);
                        put(838, false);
                        put(93, false);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(99, 0);
                        put(-365, 1);
                        put(137, 1);
                        put(544, 2);
                        put(100, 2);
                        put(302, 3);
                        put(-1010, 2);
                        put(-235, 2);
                        put(-234, 3);
                        put(128, 3);
                        put(358, 4);
                        put(373, 5);
                        put(155, 4);
                        put(-304, 3);
                        put(-144, 4);
                        put(835, 3);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(99, 15);
                        put(-365, 5);
                        put(137, 8);
                        put(544, 5);
                        put(100, 1);
                        put(302, 3);
                        put(-1010, 0);
                        put(-235, 3);
                        put(-234, 1);
                        put(128, 0);
                        put(358, 1);
                        put(373, 0);
                        put(155, 0);
                        put(-304, 0);
                        put(-144, 0);
                        put(835, 0);
                    }
                }));
        GOOD_TREES.add(new TreeTestInput(new int[]{
                937,
                                115,
                                -307,
                                661,
                                270,
                                693,
                                -566,
                                -453,
                                -815,
                                -801,
                                -554,
                                323,
                                448,
                                137,
                                149,
                                -695,
                                -252,
                                986,
                                -1,
                                152,
                                -29,
                                -529,
                                154,
                                422,
                                906
                 }, 986, -815,
                new LinkedHashMap<Integer, Boolean>() {
                    {
                        put(-524, false);
                        put(-815, true);
                        put(323, true);
                        put(-44, false);
                        put(986, true);
                        put(-529, true);
                        put(-1001, false);
                        put(-830, false);
                        put(693, true);
                        put(-361, false);
                        put(-801, true);
                        put(154, true);
                        put(-643, false);
                        put(115, true);
                        put(137, true);
                        put(448, true);
                        put(906, true);
                        put(-728, false);
                        put(-166, false);
                        put(270, true);
                        put(-29, true);
                        put(-553, false);
                        put(661, true);
                        put(137, true);
                        put(-566, true);
                        put(616, false);
                        put(661, true);
                        put(773, false);
                        put(-453, true);
                        put(661, true);
                        put(-318, false);
                        put(906, true);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(937, 0);
                        put(115, 1);
                        put(-307, 2);
                        put(661, 2);
                        put(270, 3);
                        put(693, 3);
                        put(-566, 3);
                        put(-453, 4);
                        put(-815, 4);
                        put(-801, 5);
                        put(-554, 5);
                        put(323, 4);
                        put(448, 5);
                        put(137, 4);
                        put(149, 5);
                        put(-695, 6);
                        put(-252, 3);
                        put(986, 1);
                        put(-1, 4);
                        put(152, 6);
                        put(-29, 5);
                        put(-529, 6);
                        put(154, 7);
                        put(422, 6);
                        put(906, 4);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(937, 24);
                        put(115, 22);
                        put(-307, 10);
                        put(661, 10);
                        put(270, 7);
                        put(693, 1);
                        put(-566, 6);
                        put(-453, 2);
                        put(-815, 2);
                        put(-801, 1);
                        put(-554, 1);
                        put(323, 2);
                        put(448, 1);
                        put(137, 3);
                        put(149, 2);
                        put(-695, 0);
                        put(-252, 2);
                        put(986, 0);
                        put(-1, 1);
                        put(152, 1);
                        put(-29, 0);
                        put(-529, 0);
                        put(154, 0);
                        put(422, 0);
                        put(906, 0);
                    }
                }));
        GOOD_TREES.add(new TreeTestInput(new int[]{
                -792,
                                -549,
                                573,
                                -765,
                                429,
                                317,
                                -696,
                                -636,
                                205,
                                682,
                                410,
                                867,
                                -767,
                                -214,
                                -148
                 }, 867, -792,
                new LinkedHashMap<Integer, Boolean>() {
                    {
                        put(-696, true);
                        put(-942, false);
                        put(-851, false);
                        put(205, true);
                        put(-767, true);
                        put(410, true);
                        put(-767, true);
                        put(587, false);
                        put(812, false);
                        put(76, false);
                        put(745, false);
                        put(-636, true);
                        put(-571, false);
                        put(-82, false);
                        put(317, true);
                        put(-651, false);
                        put(-268, false);
                        put(-738, false);
                        put(-525, false);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(-792, 0);
                        put(-549, 1);
                        put(573, 2);
                        put(-765, 2);
                        put(429, 3);
                        put(317, 4);
                        put(-696, 3);
                        put(-636, 4);
                        put(205, 5);
                        put(682, 3);
                        put(410, 5);
                        put(867, 4);
                        put(-767, 3);
                        put(-214, 6);
                        put(-148, 7);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(-792, 14);
                        put(-549, 13);
                        put(573, 8);
                        put(-765, 3);
                        put(429, 5);
                        put(317, 4);
                        put(-696, 1);
                        put(-636, 0);
                        put(205, 2);
                        put(682, 1);
                        put(410, 0);
                        put(867, 0);
                        put(-767, 0);
                        put(-214, 1);
                        put(-148, 0);
                    }
                }));
        GOOD_TREES.add(new TreeTestInput(new int[]{
                -901,
                                777,
                                951,
                                -463,
                                -288,
                                955,
                                -575,
                                569,
                                570,
                                -721,
                                -228,
                                -589,
                                -193,
                                977,
                                133,
                                -22,
                                -1004,
                                -400,
                                472,
                                -441,
                                -179,
                                831,
                                -403,
                                -627,
                                122,
                                -947,
                                921
                 }, 977, -1004,
                new LinkedHashMap<Integer, Boolean>() {
                    {
                        put(-627, true);
                        put(548, false);
                        put(700, false);
                        put(555, false);
                        put(955, true);
                        put(955, true);
                        put(831, true);
                        put(-228, true);
                        put(570, true);
                        put(133, true);
                        put(-575, true);
                        put(-768, false);
                        put(336, false);
                        put(-403, true);
                        put(-1004, true);
                        put(11, false);
                        put(845, false);
                        put(-721, true);
                        put(-875, false);
                        put(-126, false);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(-901, 0);
                        put(777, 1);
                        put(951, 2);
                        put(-463, 2);
                        put(-288, 3);
                        put(955, 3);
                        put(-575, 3);
                        put(569, 4);
                        put(570, 5);
                        put(-721, 4);
                        put(-228, 5);
                        put(-589, 5);
                        put(-193, 6);
                        put(977, 4);
                        put(133, 7);
                        put(-22, 8);
                        put(-1004, 1);
                        put(-400, 4);
                        put(472, 8);
                        put(-441, 5);
                        put(-179, 9);
                        put(831, 3);
                        put(-403, 6);
                        put(-627, 6);
                        put(122, 9);
                        put(-947, 2);
                        put(921, 4);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(-901, 26);
                        put(777, 23);
                        put(951, 4);
                        put(-463, 17);
                        put(-288, 12);
                        put(955, 1);
                        put(-575, 3);
                        put(569, 8);
                        put(570, 0);
                        put(-721, 2);
                        put(-228, 6);
                        put(-589, 1);
                        put(-193, 5);
                        put(977, 0);
                        put(133, 4);
                        put(-22, 2);
                        put(-1004, 1);
                        put(-400, 2);
                        put(472, 0);
                        put(-441, 1);
                        put(-179, 0);
                        put(831, 1);
                        put(-403, 0);
                        put(-627, 0);
                        put(122, 0);
                        put(-947, 0);
                        put(921, 0);
                    }
                }));
        GOOD_TREES.add(new TreeTestInput(new int[]{
                -289,
                                -721,
                                344,
                                -685,
                                593,
                                -853,
                                -953,
                                63,
                                82,
                                -697,
                                -417,
                                567,
                                454,
                                187,
                                -479,
                                -206,
                                310,
                                685,
                                58,
                                422,
                                -305,
                                -708,
                                -666,
                                301,
                                627,
                                903
                 }, 903, -953,
                new LinkedHashMap<Integer, Boolean>() {
                    {
                        put(567, true);
                        put(195, false);
                        put(-121, false);
                        put(-108, false);
                        put(-479, true);
                        put(187, true);
                        put(-180, false);
                        put(310, true);
                        put(608, false);
                        put(627, true);
                        put(454, true);
                        put(-582, false);
                        put(702, false);
                        put(-685, true);
                        put(-224, false);
                        put(-372, false);
                        put(839, false);
                        put(-305, true);
                        put(58, true);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(-289, 0);
                        put(-721, 1);
                        put(344, 1);
                        put(-685, 2);
                        put(593, 2);
                        put(-853, 2);
                        put(-953, 3);
                        put(63, 2);
                        put(82, 3);
                        put(-697, 3);
                        put(-417, 3);
                        put(567, 3);
                        put(454, 4);
                        put(187, 4);
                        put(-479, 4);
                        put(-206, 3);
                        put(310, 5);
                        put(685, 3);
                        put(58, 4);
                        put(422, 5);
                        put(-305, 4);
                        put(-708, 4);
                        put(-666, 5);
                        put(301, 6);
                        put(627, 4);
                        put(903, 4);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(-289, 25);
                        put(-721, 9);
                        put(344, 14);
                        put(-685, 6);
                        put(593, 6);
                        put(-853, 1);
                        put(-953, 0);
                        put(63, 6);
                        put(82, 3);
                        put(-697, 1);
                        put(-417, 3);
                        put(567, 2);
                        put(454, 1);
                        put(187, 2);
                        put(-479, 1);
                        put(-206, 1);
                        put(310, 1);
                        put(685, 2);
                        put(58, 0);
                        put(422, 0);
                        put(-305, 0);
                        put(-708, 0);
                        put(-666, 0);
                        put(301, 0);
                        put(627, 0);
                        put(903, 0);
                    }
                }));
        GOOD_TREES.add(new TreeTestInput(new int[]{
                -47,
                                505,
                                725,
                                451,
                                -443,
                                355,
                                934,
                                117,
                                707,
                                873,
                                201,
                                -837,
                                -72,
                                358,
                                -677,
                                -13,
                                -980,
                                -556,
                                -598,
                                -214,
                                -972,
                                -963,
                                -344,
                                -161,
                                925,
                                -659,
                                -7
                 }, 934, -980,
                new LinkedHashMap<Integer, Boolean>() {
                    {
                        put(925, true);
                        put(489, false);
                        put(859, false);
                        put(836, false);
                        put(-344, true);
                        put(160, false);
                        put(-875, false);
                        put(-556, true);
                        put(-47, true);
                        put(201, true);
                        put(117, true);
                        put(925, true);
                        put(964, false);
                        put(351, false);
                        put(897, false);
                        put(-582, false);
                        put(-276, false);
                        put(121, false);
                        put(725, true);
                        put(451, true);
                        put(-941, false);
                        put(-161, true);
                        put(-786, false);
                        put(-972, true);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(-47, 0);
                        put(505, 1);
                        put(725, 2);
                        put(451, 2);
                        put(-443, 1);
                        put(355, 3);
                        put(934, 3);
                        put(117, 4);
                        put(707, 3);
                        put(873, 4);
                        put(201, 5);
                        put(-837, 2);
                        put(-72, 2);
                        put(358, 4);
                        put(-677, 3);
                        put(-13, 5);
                        put(-980, 3);
                        put(-556, 4);
                        put(-598, 5);
                        put(-214, 3);
                        put(-972, 4);
                        put(-963, 5);
                        put(-344, 4);
                        put(-161, 4);
                        put(925, 5);
                        put(-659, 6);
                        put(-7, 6);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(-47, 26);
                        put(505, 12);
                        put(725, 4);
                        put(451, 6);
                        put(-443, 12);
                        put(355, 5);
                        put(934, 2);
                        put(117, 3);
                        put(707, 0);
                        put(873, 1);
                        put(201, 0);
                        put(-837, 7);
                        put(-72, 3);
                        put(358, 0);
                        put(-677, 3);
                        put(-13, 1);
                        put(-980, 2);
                        put(-556, 2);
                        put(-598, 1);
                        put(-214, 2);
                        put(-972, 1);
                        put(-963, 0);
                        put(-344, 0);
                        put(-161, 0);
                        put(925, 0);
                        put(-659, 0);
                        put(-7, 0);
                    }
                }));
        GOOD_TREES.add(new TreeTestInput(new int[]{
                603,
                                856,
                                -1003,
                                -862,
                                -334,
                                -201,
                                279,
                                412,
                                -410,
                                777,
                                614,
                                -473,
                                -440,
                                -441,
                                952,
                                -738,
                                -853,
                                47,
                                698,
                                233,
                                760,
                                -538,
                                -770,
                                -358,
                                1003,
                                -721,
                                -543,
                                -802,
                                24,
                                45,
                                36,
                                363,
                                513,
                                458,
                                -902,
                                291,
                                452,
                                905,
                                59,
                                -491,
                                324,
                                -48,
                                530,
                                690,
                                -935,
                                -305,
                                959,
                                516,
                                -10,
                                522,
                                316,
                                1001,
                                -653,
                                -161,
                                -697,
                                -816,
                                425,
                                -824,
                                876,
                                334,
                                -541,
                                -850,
                                729
                 }, 1003, -1003,
                new LinkedHashMap<Integer, Boolean>() {
                    {
                        put(-189, false);
                        put(-102, false);
                        put(-509, false);
                        put(874, false);
                        put(201, false);
                        put(-989, false);
                        put(59, true);
                        put(-770, true);
                        put(-278, false);
                        put(-1003, true);
                        put(856, true);
                        put(-161, true);
                        put(474, false);
                        put(655, false);
                        put(-495, false);
                        put(-78, false);
                        put(-668, false);
                        put(-48, true);
                        put(499, false);
                        put(-853, true);
                        put(-351, false);
                        put(-754, false);
                        put(-778, false);
                        put(774, false);
                        put(208, false);
                        put(601, false);
                        put(-339, false);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(603, 0);
                        put(856, 1);
                        put(-1003, 1);
                        put(-862, 2);
                        put(-334, 3);
                        put(-201, 4);
                        put(279, 5);
                        put(412, 6);
                        put(-410, 4);
                        put(777, 2);
                        put(614, 3);
                        put(-473, 5);
                        put(-440, 6);
                        put(-441, 7);
                        put(952, 2);
                        put(-738, 6);
                        put(-853, 7);
                        put(47, 6);
                        put(698, 4);
                        put(233, 7);
                        put(760, 5);
                        put(-538, 7);
                        put(-770, 8);
                        put(-358, 5);
                        put(1003, 3);
                        put(-721, 8);
                        put(-543, 9);
                        put(-802, 9);
                        put(24, 7);
                        put(45, 8);
                        put(36, 9);
                        put(363, 7);
                        put(513, 7);
                        put(458, 8);
                        put(-902, 3);
                        put(291, 8);
                        put(452, 9);
                        put(905, 3);
                        put(59, 8);
                        put(-491, 8);
                        put(324, 9);
                        put(-48, 8);
                        put(530, 8);
                        put(690, 5);
                        put(-935, 4);
                        put(-305, 5);
                        put(959, 4);
                        put(516, 9);
                        put(-10, 9);
                        put(522, 10);
                        put(316, 10);
                        put(1001, 5);
                        put(-653, 10);
                        put(-161, 9);
                        put(-697, 11);
                        put(-816, 10);
                        put(425, 10);
                        put(-824, 11);
                        put(876, 4);
                        put(334, 10);
                        put(-541, 10);
                        put(-850, 12);
                        put(729, 6);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(603, 62);
                        put(856, 12);
                        put(-1003, 48);
                        put(-862, 47);
                        put(-334, 44);
                        put(-201, 24);
                        put(279, 22);
                        put(412, 12);
                        put(-410, 18);
                        put(777, 5);
                        put(614, 4);
                        put(-473, 16);
                        put(-440, 1);
                        put(-441, 0);
                        put(952, 5);
                        put(-738, 13);
                        put(-853, 5);
                        put(47, 8);
                        put(698, 3);
                        put(233, 1);
                        put(760, 1);
                        put(-538, 6);
                        put(-770, 4);
                        put(-358, 0);
                        put(1003, 2);
                        put(-721, 4);
                        put(-543, 3);
                        put(-802, 3);
                        put(24, 5);
                        put(45, 1);
                        put(36, 0);
                        put(363, 4);
                        put(513, 6);
                        put(458, 2);
                        put(-902, 1);
                        put(291, 3);
                        put(452, 1);
                        put(905, 1);
                        put(59, 0);
                        put(-491, 0);
                        put(324, 2);
                        put(-48, 2);
                        put(530, 2);
                        put(690, 0);
                        put(-935, 0);
                        put(-305, 0);
                        put(959, 1);
                        put(516, 1);
                        put(-10, 0);
                        put(522, 0);
                        put(316, 0);
                        put(1001, 0);
                        put(-653, 1);
                        put(-161, 0);
                        put(-697, 0);
                        put(-816, 2);
                        put(425, 0);
                        put(-824, 1);
                        put(876, 0);
                        put(334, 0);
                        put(-541, 0);
                        put(-850, 0);
                        put(729, 0);
                    }
                }));
        GOOD_TREES.add(new TreeTestInput(new int[]{
                -253,
                                795,
                                -53,
                                -167,
                                621,
                                597,
                                199,
                                179,
                                -673,
                                -320,
                                -517,
                                -404,
                                125,
                                -96,
                                -619,
                                -805,
                                599,
                                -519,
                                456,
                                944,
                                -768,
                                7,
                                609,
                                -659,
                                -333,
                                -771,
                                -204,
                                976,
                                -909,
                                -708,
                                -234,
                                -650,
                                317,
                                -143,
                                -792,
                                9,
                                60,
                                -596,
                                -220,
                                -386,
                                470,
                                -851,
                                -350,
                                -149,
                                48,
                                100,
                                699,
                                239,
                                -866,
                                -452,
                                -518,
                                35
                 }, 976, -909,
                new LinkedHashMap<Integer, Boolean>() {
                    {
                        put(308, false);
                        put(-929, false);
                        put(-925, false);
                        put(468, false);
                        put(-400, false);
                        put(-404, true);
                        put(-650, true);
                        put(312, false);
                        put(432, false);
                        put(332, false);
                        put(-771, true);
                        put(870, false);
                        put(687, false);
                        put(9, true);
                        put(-193, false);
                        put(-452, true);
                        put(-53, true);
                        put(-673, true);
                        put(60, true);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(-253, 0);
                        put(795, 1);
                        put(-53, 2);
                        put(-167, 3);
                        put(621, 3);
                        put(597, 4);
                        put(199, 5);
                        put(179, 6);
                        put(-673, 1);
                        put(-320, 2);
                        put(-517, 3);
                        put(-404, 4);
                        put(125, 7);
                        put(-96, 4);
                        put(-619, 4);
                        put(-805, 2);
                        put(599, 5);
                        put(-519, 5);
                        put(456, 6);
                        put(944, 2);
                        put(-768, 3);
                        put(7, 8);
                        put(609, 6);
                        put(-659, 5);
                        put(-333, 5);
                        put(-771, 4);
                        put(-204, 4);
                        put(976, 3);
                        put(-909, 3);
                        put(-708, 4);
                        put(-234, 5);
                        put(-650, 6);
                        put(317, 7);
                        put(-143, 5);
                        put(-792, 5);
                        put(9, 9);
                        put(60, 10);
                        put(-596, 6);
                        put(-220, 6);
                        put(-386, 6);
                        put(470, 7);
                        put(-851, 4);
                        put(-350, 7);
                        put(-149, 6);
                        put(48, 11);
                        put(100, 11);
                        put(699, 4);
                        put(239, 8);
                        put(-866, 5);
                        put(-452, 5);
                        put(-518, 6);
                        put(35, 12);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(-253, 51);
                        put(795, 28);
                        put(-53, 25);
                        put(-167, 6);
                        put(621, 17);
                        put(597, 15);
                        put(199, 12);
                        put(179, 7);
                        put(-673, 21);
                        put(-320, 12);
                        put(-517, 11);
                        put(-404, 4);
                        put(125, 6);
                        put(-96, 2);
                        put(-619, 5);
                        put(-805, 7);
                        put(599, 1);
                        put(-519, 2);
                        put(456, 3);
                        put(944, 1);
                        put(-768, 3);
                        put(7, 5);
                        put(609, 0);
                        put(-659, 1);
                        put(-333, 2);
                        put(-771, 1);
                        put(-204, 2);
                        put(976, 0);
                        put(-909, 2);
                        put(-708, 0);
                        put(-234, 1);
                        put(-650, 0);
                        put(317, 1);
                        put(-143, 1);
                        put(-792, 0);
                        put(9, 4);
                        put(60, 3);
                        put(-596, 0);
                        put(-220, 0);
                        put(-386, 1);
                        put(470, 0);
                        put(-851, 1);
                        put(-350, 0);
                        put(-149, 0);
                        put(48, 1);
                        put(100, 0);
                        put(699, 0);
                        put(239, 0);
                        put(-866, 0);
                        put(-452, 0);
                        put(-518, 0);
                        put(35, 0);
                    }
                }));
        GOOD_TREES.add(new TreeTestInput(new int[]{
                488,
                                -572,
                                -222,
                                -636,
                                281,
                                707,
                                649,
                                452,
                                -428,
                                -652,
                                555,
                                371,
                                544,
                                -1002,
                                -219,
                                347,
                                -794,
                                -677,
                                830,
                                285,
                                685,
                                397,
                                436,
                                792,
                                864,
                                -474,
                                -167,
                                -342,
                                -36,
                                -489,
                                189,
                                -67,
                                461,
                                328,
                                -358,
                                -871,
                                -372,
                                -732,
                                298,
                                894,
                                -842,
                                -539
                 }, 894, -1002,
                new LinkedHashMap<Integer, Boolean>() {
                    {
                        put(-358, true);
                        put(-182, false);
                        put(371, true);
                        put(461, true);
                        put(-608, false);
                        put(460, false);
                        put(792, true);
                        put(544, true);
                        put(397, true);
                        put(487, false);
                        put(-652, true);
                        put(-842, true);
                        put(-1002, true);
                        put(-428, true);
                        put(-765, false);
                        put(-428, true);
                        put(-67, true);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(488, 0);
                        put(-572, 1);
                        put(-222, 2);
                        put(-636, 2);
                        put(281, 3);
                        put(707, 1);
                        put(649, 2);
                        put(452, 4);
                        put(-428, 3);
                        put(-652, 3);
                        put(555, 3);
                        put(371, 5);
                        put(544, 4);
                        put(-1002, 4);
                        put(-219, 4);
                        put(347, 6);
                        put(-794, 5);
                        put(-677, 6);
                        put(830, 2);
                        put(285, 7);
                        put(685, 3);
                        put(397, 6);
                        put(436, 7);
                        put(792, 3);
                        put(864, 3);
                        put(-474, 4);
                        put(-167, 5);
                        put(-342, 4);
                        put(-36, 6);
                        put(-489, 5);
                        put(189, 7);
                        put(-67, 7);
                        put(461, 5);
                        put(328, 8);
                        put(-358, 5);
                        put(-871, 6);
                        put(-372, 6);
                        put(-732, 7);
                        put(298, 9);
                        put(894, 4);
                        put(-842, 7);
                        put(-539, 6);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(488, 41);
                        put(-572, 31);
                        put(-222, 22);
                        put(-636, 7);
                        put(281, 14);
                        put(707, 8);
                        put(649, 3);
                        put(452, 8);
                        put(-428, 6);
                        put(-652, 6);
                        put(555, 1);
                        put(371, 6);
                        put(544, 0);
                        put(-1002, 5);
                        put(-219, 4);
                        put(347, 3);
                        put(-794, 4);
                        put(-677, 1);
                        put(830, 3);
                        put(285, 2);
                        put(685, 0);
                        put(397, 1);
                        put(436, 0);
                        put(792, 0);
                        put(864, 1);
                        put(-474, 2);
                        put(-167, 3);
                        put(-342, 2);
                        put(-36, 2);
                        put(-489, 1);
                        put(189, 0);
                        put(-67, 0);
                        put(461, 0);
                        put(328, 1);
                        put(-358, 1);
                        put(-871, 1);
                        put(-372, 0);
                        put(-732, 0);
                        put(298, 0);
                        put(894, 0);
                        put(-842, 0);
                        put(-539, 0);
                    }
                }));
        GOOD_TREES.add(new TreeTestInput(new int[]{
                709,
                                511,
                                -248,
                                829,
                                -357,
                                323,
                                -106,
                                83,
                                173,
                                591
                 }, 829, -357,
                new LinkedHashMap<Integer, Boolean>() {
                    {
                        put(83, true);
                        put(802, false);
                        put(-216, false);
                        put(246, false);
                        put(829, true);
                        put(-718, false);
                        put(173, true);
                        put(511, true);
                        put(173, true);
                        put(829, true);
                        put(-442, false);
                        put(829, true);
                        put(-201, false);
                        put(-113, false);
                        put(591, true);
                        put(-106, true);
                        put(83, true);
                        put(-247, false);
                        put(323, true);
                        put(709, true);
                        put(456, false);
                        put(-656, false);
                        put(83, true);
                        put(323, true);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(709, 0);
                        put(511, 1);
                        put(-248, 2);
                        put(829, 1);
                        put(-357, 3);
                        put(323, 3);
                        put(-106, 4);
                        put(83, 5);
                        put(173, 6);
                        put(591, 2);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(709, 9);
                        put(511, 7);
                        put(-248, 5);
                        put(829, 0);
                        put(-357, 0);
                        put(323, 3);
                        put(-106, 2);
                        put(83, 1);
                        put(173, 0);
                        put(591, 0);
                    }
                }));
        GOOD_TREES.add(new TreeTestInput(new int[]{
                -981,
                                -894,
                                -770,
                                -302,
                                -468,
                                -546,
                                -797,
                                -739,
                                378,
                                42,
                                700,
                                997,
                                -429,
                                432,
                                -261,
                                779,
                                -226,
                                -621,
                                -782,
                                936,
                                -321,
                                438,
                                -52,
                                80,
                                733,
                                226,
                                -169,
                                -367,
                                -584,
                                -948,
                                -798,
                                159,
                                -171
                 }, 997, -981,
                new LinkedHashMap<Integer, Boolean>() {
                    {
                        put(-169, true);
                        put(779, true);
                        put(725, false);
                        put(953, false);
                        put(-468, true);
                        put(378, true);
                        put(-546, true);
                        put(-312, false);
                        put(436, false);
                        put(-981, true);
                        put(-782, true);
                        put(733, true);
                        put(432, true);
                        put(-581, false);
                        put(-981, true);
                        put(638, false);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(-981, 0);
                        put(-894, 1);
                        put(-770, 2);
                        put(-302, 3);
                        put(-468, 4);
                        put(-546, 5);
                        put(-797, 3);
                        put(-739, 6);
                        put(378, 4);
                        put(42, 5);
                        put(700, 5);
                        put(997, 6);
                        put(-429, 5);
                        put(432, 6);
                        put(-261, 6);
                        put(779, 7);
                        put(-226, 7);
                        put(-621, 7);
                        put(-782, 4);
                        put(936, 8);
                        put(-321, 6);
                        put(438, 7);
                        put(-52, 8);
                        put(80, 6);
                        put(733, 8);
                        put(226, 7);
                        put(-169, 9);
                        put(-367, 7);
                        put(-584, 8);
                        put(-948, 2);
                        put(-798, 4);
                        put(159, 8);
                        put(-171, 10);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(-981, 32);
                        put(-894, 31);
                        put(-770, 29);
                        put(-302, 25);
                        put(-468, 7);
                        put(-546, 3);
                        put(-797, 2);
                        put(-739, 2);
                        put(378, 16);
                        put(42, 8);
                        put(700, 6);
                        put(997, 3);
                        put(-429, 2);
                        put(432, 1);
                        put(-261, 4);
                        put(779, 2);
                        put(-226, 3);
                        put(-621, 1);
                        put(-782, 0);
                        put(936, 0);
                        put(-321, 1);
                        put(438, 0);
                        put(-52, 2);
                        put(80, 2);
                        put(733, 0);
                        put(226, 1);
                        put(-169, 1);
                        put(-367, 0);
                        put(-584, 0);
                        put(-948, 0);
                        put(-798, 0);
                        put(159, 0);
                        put(-171, 0);
                    }
                }));
        GOOD_TREES.add(new TreeTestInput(new int[]{
                -403,
                                -218,
                                888,
                                -228,
                                686,
                                -843,
                                192,
                                745,
                                -162
                 }, 888, -843,
                new LinkedHashMap<Integer, Boolean>() {
                    {
                        put(-403, true);
                        put(-843, true);
                        put(-218, true);
                        put(686, true);
                        put(-511, false);
                        put(585, false);
                        put(-162, true);
                        put(888, true);
                        put(888, true);
                        put(824, false);
                        put(-228, true);
                        put(745, true);
                        put(757, false);
                        put(87, false);
                        put(745, true);
                        put(-843, true);
                        put(941, false);
                        put(-228, true);
                        put(73, false);
                        put(763, false);
                        put(-989, false);
                        put(888, true);
                        put(888, true);
                        put(-664, false);
                        put(888, true);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(-403, 0);
                        put(-218, 1);
                        put(888, 2);
                        put(-228, 2);
                        put(686, 3);
                        put(-843, 1);
                        put(192, 4);
                        put(745, 4);
                        put(-162, 5);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(-403, 8);
                        put(-218, 6);
                        put(888, 4);
                        put(-228, 0);
                        put(686, 3);
                        put(-843, 0);
                        put(192, 1);
                        put(745, 0);
                        put(-162, 0);
                    }
                }));
        GOOD_TREES.add(new TreeTestInput(new int[]{
                648,
                                41,
                                195,
                                878,
                                -779,
                                -1012,
                                -504,
                                -438,
                                372,
                                763,
                                642,
                                423,
                                -32,
                                -156,
                                15,
                                615,
                                660,
                                681,
                                668,
                                -212,
                                -946,
                                186,
                                671,
                                -228,
                                -595,
                                795,
                                -3,
                                -895,
                                -415,
                                -967,
                                -811,
                                -192,
                                -817,
                                412,
                                53,
                                354,
                                779,
                                764,
                                778,
                                -604,
                                342,
                                -873,
                                -288,
                                -576,
                                -268,
                                1001,
                                563,
                                -420,
                                -532,
                                475,
                                243,
                                805,
                                -674,
                                -410,
                                -317,
                                505,
                                -629,
                                546,
                                52,
                                481,
                                886,
                                853,
                                -162
                 }, 1001, -1012,
                new LinkedHashMap<Integer, Boolean>() {
                    {
                        put(186, true);
                        put(-674, true);
                        put(195, true);
                        put(-45, false);
                        put(608, false);
                        put(-268, true);
                        put(-415, true);
                        put(-213, false);
                        put(660, true);
                        put(900, false);
                        put(509, false);
                        put(-203, false);
                        put(671, true);
                        put(879, false);
                        put(615, true);
                        put(764, true);
                        put(-411, false);
                        put(342, true);
                        put(648, true);
                        put(243, true);
                        put(-882, false);
                        put(681, true);
                        put(933, false);
                        put(795, true);
                        put(-916, false);
                        put(-79, false);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(648, 0);
                        put(41, 1);
                        put(195, 2);
                        put(878, 1);
                        put(-779, 2);
                        put(-1012, 3);
                        put(-504, 3);
                        put(-438, 4);
                        put(372, 3);
                        put(763, 2);
                        put(642, 4);
                        put(423, 5);
                        put(-32, 5);
                        put(-156, 6);
                        put(15, 6);
                        put(615, 6);
                        put(660, 3);
                        put(681, 4);
                        put(668, 5);
                        put(-212, 7);
                        put(-946, 4);
                        put(186, 3);
                        put(671, 6);
                        put(-228, 8);
                        put(-595, 4);
                        put(795, 3);
                        put(-3, 7);
                        put(-895, 5);
                        put(-415, 9);
                        put(-967, 5);
                        put(-811, 6);
                        put(-192, 8);
                        put(-817, 7);
                        put(412, 6);
                        put(53, 4);
                        put(354, 4);
                        put(779, 4);
                        put(764, 5);
                        put(778, 6);
                        put(-604, 5);
                        put(342, 5);
                        put(-873, 8);
                        put(-288, 10);
                        put(-576, 5);
                        put(-268, 11);
                        put(1001, 2);
                        put(563, 7);
                        put(-420, 10);
                        put(-532, 6);
                        put(475, 8);
                        put(243, 6);
                        put(805, 4);
                        put(-674, 6);
                        put(-410, 11);
                        put(-317, 12);
                        put(505, 9);
                        put(-629, 7);
                        put(546, 10);
                        put(52, 5);
                        put(481, 10);
                        put(886, 3);
                        put(853, 5);
                        put(-162, 9);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(648, 62);
                        put(41, 47);
                        put(195, 16);
                        put(878, 13);
                        put(-779, 29);
                        put(-1012, 6);
                        put(-504, 21);
                        put(-438, 14);
                        put(372, 12);
                        put(763, 10);
                        put(642, 8);
                        put(423, 7);
                        put(-32, 13);
                        put(-156, 10);
                        put(15, 1);
                        put(615, 5);
                        put(660, 3);
                        put(681, 2);
                        put(668, 1);
                        put(-212, 9);
                        put(-946, 5);
                        put(186, 2);
                        put(671, 0);
                        put(-228, 6);
                        put(-595, 5);
                        put(795, 5);
                        put(-3, 0);
                        put(-895, 3);
                        put(-415, 5);
                        put(-967, 0);
                        put(-811, 2);
                        put(-192, 1);
                        put(-817, 1);
                        put(412, 0);
                        put(53, 1);
                        put(354, 2);
                        put(779, 2);
                        put(764, 1);
                        put(778, 0);
                        put(-604, 2);
                        put(342, 1);
                        put(-873, 0);
                        put(-288, 3);
                        put(-576, 1);
                        put(-268, 0);
                        put(1001, 1);
                        put(563, 4);
                        put(-420, 0);
                        put(-532, 0);
                        put(475, 3);
                        put(243, 0);
                        put(805, 1);
                        put(-674, 1);
                        put(-410, 1);
                        put(-317, 0);
                        put(505, 2);
                        put(-629, 0);
                        put(546, 0);
                        put(52, 0);
                        put(481, 0);
                        put(886, 0);
                        put(853, 0);
                        put(-162, 0);
                    }
                }));
        GOOD_TREES.add(new TreeTestInput(new int[]{
                507,
                                680,
                                328,
                                823,
                                941,
                                975,
                                918,
                                392,
                                -350,
                                922,
                                -162,
                                929,
                                84,
                                -826,
                                462,
                                -301,
                                530,
                                -201,
                                541,
                                493,
                                -345,
                                6,
                                579,
                                545,
                                341,
                                227,
                                990,
                                630,
                                250,
                                406,
                                -859,
                                -241,
                                -821,
                                836,
                                -674
                 }, 990, -859,
                new LinkedHashMap<Integer, Boolean>() {
                    {
                        put(-162, true);
                        put(823, true);
                        put(-350, true);
                        put(630, true);
                        put(973, false);
                        put(823, true);
                        put(630, true);
                        put(545, true);
                        put(941, true);
                        put(-345, true);
                        put(-674, true);
                        put(541, true);
                        put(914, false);
                        put(250, true);
                        put(350, false);
                        put(-201, true);
                        put(922, true);
                        put(823, true);
                        put(-201, true);
                        put(392, true);
                        put(-821, true);
                        put(406, true);
                        put(462, true);
                        put(74, false);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(507, 0);
                        put(680, 1);
                        put(328, 1);
                        put(823, 2);
                        put(941, 3);
                        put(975, 4);
                        put(918, 4);
                        put(392, 2);
                        put(-350, 2);
                        put(922, 5);
                        put(-162, 3);
                        put(929, 6);
                        put(84, 4);
                        put(-826, 3);
                        put(462, 3);
                        put(-301, 4);
                        put(530, 2);
                        put(-201, 5);
                        put(541, 3);
                        put(493, 4);
                        put(-345, 5);
                        put(6, 5);
                        put(579, 4);
                        put(545, 5);
                        put(341, 3);
                        put(227, 5);
                        put(990, 5);
                        put(630, 5);
                        put(250, 6);
                        put(406, 4);
                        put(-859, 4);
                        put(-241, 6);
                        put(-821, 4);
                        put(836, 5);
                        put(-674, 5);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(507, 34);
                        put(680, 13);
                        put(328, 19);
                        put(823, 7);
                        put(941, 6);
                        put(975, 1);
                        put(918, 3);
                        put(392, 4);
                        put(-350, 13);
                        put(922, 1);
                        put(-162, 8);
                        put(929, 0);
                        put(84, 3);
                        put(-826, 3);
                        put(462, 2);
                        put(-301, 3);
                        put(530, 4);
                        put(-201, 1);
                        put(541, 3);
                        put(493, 0);
                        put(-345, 0);
                        put(6, 0);
                        put(579, 2);
                        put(545, 0);
                        put(341, 0);
                        put(227, 1);
                        put(990, 0);
                        put(630, 0);
                        put(250, 0);
                        put(406, 0);
                        put(-859, 0);
                        put(-241, 0);
                        put(-821, 1);
                        put(836, 0);
                        put(-674, 0);
                    }
                }));
        GOOD_TREES.add(new TreeTestInput(new int[]{
                222,
                                486,
                                -140,
                                -598,
                                78,
                                -23,
                                -44,
                                970,
                                564,
                                618,
                                663,
                                -143,
                                -418,
                                -543,
                                525,
                                243,
                                374,
                                -496,
                                650,
                                301,
                                -391,
                                -914,
                                459,
                                -760,
                                -283,
                                136,
                                -713,
                                -352,
                                403,
                                757,
                                806,
                                708,
                                -362,
                                -250,
                                -446,
                                565,
                                -147
                 }, 970, -914,
                new LinkedHashMap<Integer, Boolean>() {
                    {
                        put(810, false);
                        put(222, true);
                        put(243, true);
                        put(650, true);
                        put(136, true);
                        put(275, false);
                        put(486, true);
                        put(403, true);
                        put(424, false);
                        put(486, true);
                        put(301, true);
                        put(-250, true);
                        put(486, true);
                        put(-143, true);
                        put(-901, false);
                        put(25, false);
                        put(525, true);
                        put(-161, false);
                        put(-181, false);
                        put(-629, false);
                        put(380, false);
                        put(522, false);
                        put(686, false);
                        put(-332, false);
                        put(-408, false);
                        put(757, true);
                        put(243, true);
                        put(-914, true);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(222, 0);
                        put(486, 1);
                        put(-140, 1);
                        put(-598, 2);
                        put(78, 2);
                        put(-23, 3);
                        put(-44, 4);
                        put(970, 2);
                        put(564, 3);
                        put(618, 4);
                        put(663, 5);
                        put(-143, 3);
                        put(-418, 4);
                        put(-543, 5);
                        put(525, 4);
                        put(243, 2);
                        put(374, 3);
                        put(-496, 6);
                        put(650, 6);
                        put(301, 4);
                        put(-391, 5);
                        put(-914, 3);
                        put(459, 4);
                        put(-760, 4);
                        put(-283, 6);
                        put(136, 3);
                        put(-713, 5);
                        put(-352, 7);
                        put(403, 5);
                        put(757, 6);
                        put(806, 7);
                        put(708, 7);
                        put(-362, 8);
                        put(-250, 7);
                        put(-446, 7);
                        put(565, 5);
                        put(-147, 8);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(222, 36);
                        put(486, 15);
                        put(-140, 19);
                        put(-598, 14);
                        put(78, 3);
                        put(-23, 1);
                        put(-44, 0);
                        put(970, 9);
                        put(564, 8);
                        put(618, 6);
                        put(663, 4);
                        put(-143, 10);
                        put(-418, 9);
                        put(-543, 2);
                        put(525, 0);
                        put(243, 4);
                        put(374, 3);
                        put(-496, 1);
                        put(650, 0);
                        put(301, 0);
                        put(-391, 5);
                        put(-914, 2);
                        put(459, 1);
                        put(-760, 1);
                        put(-283, 4);
                        put(136, 0);
                        put(-713, 0);
                        put(-352, 1);
                        put(403, 0);
                        put(757, 2);
                        put(806, 0);
                        put(708, 0);
                        put(-362, 0);
                        put(-250, 1);
                        put(-446, 0);
                        put(565, 0);
                        put(-147, 0);
                    }
                }));
        GOOD_TREES.add(new TreeTestInput(new int[]{
                -30,
                                -709,
                                -424,
                                -203,
                                376,
                                960,
                                -800,
                                -138,
                                40,
                                882,
                                -827,
                                701,
                                -277,
                                -400,
                                706,
                                -462,
                                563,
                                156,
                                862,
                                -220,
                                761,
                                -393,
                                -639,
                                -986,
                                565,
                                -813,
                                250,
                                190,
                                284,
                                -107,
                                -487,
                                759,
                                -607
                 }, 960, -986,
                new LinkedHashMap<Integer, Boolean>() {
                    {
                        put(704, false);
                        put(-639, true);
                        put(-393, true);
                        put(84, false);
                        put(934, false);
                        put(-272, false);
                        put(701, true);
                        put(-986, true);
                        put(-692, false);
                        put(250, true);
                        put(849, false);
                        put(-139, false);
                        put(376, true);
                        put(759, true);
                        put(590, false);
                        put(-138, true);
                        put(380, false);
                        put(40, true);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(-30, 0);
                        put(-709, 1);
                        put(-424, 2);
                        put(-203, 3);
                        put(376, 1);
                        put(960, 2);
                        put(-800, 2);
                        put(-138, 4);
                        put(40, 2);
                        put(882, 3);
                        put(-827, 3);
                        put(701, 4);
                        put(-277, 4);
                        put(-400, 5);
                        put(706, 5);
                        put(-462, 3);
                        put(563, 5);
                        put(156, 3);
                        put(862, 6);
                        put(-220, 5);
                        put(761, 7);
                        put(-393, 6);
                        put(-639, 4);
                        put(-986, 4);
                        put(565, 6);
                        put(-813, 4);
                        put(250, 4);
                        put(190, 5);
                        put(284, 5);
                        put(-107, 5);
                        put(-487, 5);
                        put(759, 8);
                        put(-607, 6);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(-30, 32);
                        put(-709, 16);
                        put(-424, 11);
                        put(-203, 6);
                        put(376, 14);
                        put(960, 8);
                        put(-800, 3);
                        put(-138, 1);
                        put(40, 4);
                        put(882, 7);
                        put(-827, 2);
                        put(701, 6);
                        put(-277, 3);
                        put(-400, 1);
                        put(706, 3);
                        put(-462, 3);
                        put(563, 1);
                        put(156, 3);
                        put(862, 2);
                        put(-220, 0);
                        put(761, 1);
                        put(-393, 0);
                        put(-639, 2);
                        put(-986, 0);
                        put(565, 0);
                        put(-813, 0);
                        put(250, 2);
                        put(190, 0);
                        put(284, 0);
                        put(-107, 0);
                        put(-487, 1);
                        put(759, 0);
                        put(-607, 0);
                    }
                }));
        GOOD_TREES.add(new TreeTestInput(new int[]{
                -314,
                                94,
                                929,
                                -416,
                                806,
                                -445,
                                -909,
                                -241,
                                469,
                                -728,
                                998,
                                -139,
                                645,
                                -378,
                                -699,
                                244,
                                -622,
                                726,
                                867,
                                -886,
                                589,
                                -1019,
                                -256,
                                -1007,
                                595,
                                24,
                                368,
                                665,
                                -402,
                                -22,
                                713,
                                -246,
                                537,
                                -362,
                                -234,
                                123,
                                -409,
                                83,
                                -583,
                                -885,
                                999,
                                822,
                                -979,
                                894,
                                88,
                                -881,
                                -642,
                                425,
                                497,
                                -206,
                                335,
                                326,
                                828,
                                666,
                                252,
                                4
                 }, 999, -1019,
                new LinkedHashMap<Integer, Boolean>() {
                    {
                        put(-445, true);
                        put(252, true);
                        put(894, true);
                        put(55, false);
                        put(-427, false);
                        put(645, true);
                        put(665, true);
                        put(-983, false);
                        put(-170, false);
                        put(822, true);
                        put(828, true);
                        put(-448, false);
                        put(-445, true);
                        put(-22, true);
                        put(252, true);
                        put(-852, false);
                        put(680, false);
                        put(625, false);
                        put(713, true);
                        put(595, true);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(-314, 0);
                        put(94, 1);
                        put(929, 2);
                        put(-416, 1);
                        put(806, 3);
                        put(-445, 2);
                        put(-909, 3);
                        put(-241, 2);
                        put(469, 4);
                        put(-728, 4);
                        put(998, 3);
                        put(-139, 3);
                        put(645, 5);
                        put(-378, 2);
                        put(-699, 5);
                        put(244, 5);
                        put(-622, 6);
                        put(726, 6);
                        put(867, 4);
                        put(-886, 5);
                        put(589, 6);
                        put(-1019, 4);
                        put(-256, 3);
                        put(-1007, 5);
                        put(595, 7);
                        put(24, 4);
                        put(368, 6);
                        put(665, 7);
                        put(-402, 3);
                        put(-22, 5);
                        put(713, 8);
                        put(-246, 4);
                        put(537, 7);
                        put(-362, 3);
                        put(-234, 4);
                        put(123, 6);
                        put(-409, 4);
                        put(83, 5);
                        put(-583, 7);
                        put(-885, 6);
                        put(999, 4);
                        put(822, 5);
                        put(-979, 6);
                        put(894, 5);
                        put(88, 6);
                        put(-881, 7);
                        put(-642, 7);
                        put(425, 7);
                        put(497, 8);
                        put(-206, 5);
                        put(335, 7);
                        put(326, 8);
                        put(828, 6);
                        put(666, 9);
                        put(252, 9);
                        put(4, 6);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(-314, 55);
                        put(94, 36);
                        put(929, 24);
                        put(-416, 17);
                        put(806, 21);
                        put(-445, 12);
                        put(-909, 11);
                        put(-241, 10);
                        put(469, 16);
                        put(-728, 7);
                        put(998, 1);
                        put(-139, 7);
                        put(645, 8);
                        put(-378, 3);
                        put(-699, 3);
                        put(244, 6);
                        put(-622, 2);
                        put(726, 3);
                        put(867, 3);
                        put(-886, 2);
                        put(589, 3);
                        put(-1019, 2);
                        put(-256, 1);
                        put(-1007, 1);
                        put(595, 0);
                        put(24, 4);
                        put(368, 4);
                        put(665, 2);
                        put(-402, 1);
                        put(-22, 1);
                        put(713, 1);
                        put(-246, 0);
                        put(537, 1);
                        put(-362, 0);
                        put(-234, 1);
                        put(123, 0);
                        put(-409, 0);
                        put(83, 1);
                        put(-583, 0);
                        put(-885, 1);
                        put(999, 0);
                        put(822, 1);
                        put(-979, 0);
                        put(894, 0);
                        put(88, 0);
                        put(-881, 0);
                        put(-642, 0);
                        put(425, 0);
                        put(497, 0);
                        put(-206, 0);
                        put(335, 2);
                        put(326, 1);
                        put(828, 0);
                        put(666, 0);
                        put(252, 0);
                        put(4, 0);
                    }
                }));
        GOOD_TREES.add(new TreeTestInput(new int[]{
                618,
                                1012,
                                -981,
                                886,
                                1024,
                                198,
                                256,
                                695,
                                -390,
                                368,
                                142,
                                624,
                                721,
                                486,
                                554,
                                896,
                                -62,
                                -928,
                                -600,
                                -747,
                                -38,
                                -489,
                                365,
                                -631,
                                47,
                                -523,
                                424,
                                -444,
                                817,
                                408,
                                917,
                                -815,
                                125,
                                494
                 }, 1024, -981,
                new LinkedHashMap<Integer, Boolean>() {
                    {
                        put(-69, false);
                        put(-172, false);
                        put(-38, true);
                        put(-631, true);
                        put(235, false);
                        put(214, false);
                        put(-631, true);
                        put(198, true);
                        put(-747, true);
                        put(364, false);
                        put(-364, false);
                        put(171, false);
                        put(-62, true);
                        put(-141, false);
                        put(886, true);
                        put(981, false);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(618, 0);
                        put(1012, 1);
                        put(-981, 1);
                        put(886, 2);
                        put(1024, 2);
                        put(198, 2);
                        put(256, 3);
                        put(695, 3);
                        put(-390, 3);
                        put(368, 4);
                        put(142, 4);
                        put(624, 4);
                        put(721, 4);
                        put(486, 5);
                        put(554, 6);
                        put(896, 3);
                        put(-62, 5);
                        put(-928, 4);
                        put(-600, 5);
                        put(-747, 6);
                        put(-38, 6);
                        put(-489, 6);
                        put(365, 5);
                        put(-631, 7);
                        put(47, 7);
                        put(-523, 7);
                        put(424, 6);
                        put(-444, 7);
                        put(817, 5);
                        put(408, 7);
                        put(917, 4);
                        put(-815, 7);
                        put(125, 8);
                        put(494, 7);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(618, 33);
                        put(1012, 8);
                        put(-981, 23);
                        put(886, 6);
                        put(1024, 0);
                        put(198, 22);
                        put(256, 7);
                        put(695, 3);
                        put(-390, 13);
                        put(368, 6);
                        put(142, 4);
                        put(624, 0);
                        put(721, 1);
                        put(486, 4);
                        put(554, 1);
                        put(896, 1);
                        put(-62, 3);
                        put(-928, 7);
                        put(-600, 6);
                        put(-747, 2);
                        put(-38, 2);
                        put(-489, 2);
                        put(365, 0);
                        put(-631, 0);
                        put(47, 1);
                        put(-523, 0);
                        put(424, 1);
                        put(-444, 0);
                        put(817, 0);
                        put(408, 0);
                        put(917, 0);
                        put(-815, 0);
                        put(125, 0);
                        put(494, 0);
                    }
                }));
        GOOD_TREES.add(new TreeTestInput(new int[]{
                190,
                                -210,
                                -764,
                                -521,
                                258,
                                880,
                                493,
                                299,
                                962,
                                100,
                                278,
                                894,
                                92,
                                147,
                                -150,
                                1019,
                                -678,
                                554,
                                720,
                                -248,
                                203,
                                -909,
                                -973,
                                10,
                                -37,
                                -119,
                                -249,
                                822,
                                -799,
                                -881,
                                436,
                                234,
                                107,
                                1000,
                                -54,
                                18,
                                -821,
                                -19,
                                -124,
                                286,
                                -761,
                                -708,
                                -952,
                                -694,
                                -84,
                                671,
                                -453,
                                -999,
                                693,
                                -626,
                                383,
                                709,
                                -978,
                                976,
                                -743,
                                -828
                 }, 1019, -999,
                new LinkedHashMap<Integer, Boolean>() {
                    {
                        put(-40, false);
                        put(30, false);
                        put(-868, false);
                        put(383, true);
                        put(100, true);
                        put(100, true);
                        put(286, true);
                        put(-788, false);
                        put(-210, true);
                        put(-453, true);
                        put(463, false);
                        put(-828, true);
                        put(976, true);
                        put(-521, true);
                        put(561, false);
                        put(-881, true);
                        put(-210, true);
                        put(-761, true);
                        put(92, true);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(190, 0);
                        put(-210, 1);
                        put(-764, 2);
                        put(-521, 3);
                        put(258, 1);
                        put(880, 2);
                        put(493, 3);
                        put(299, 4);
                        put(962, 3);
                        put(100, 2);
                        put(278, 5);
                        put(894, 4);
                        put(92, 3);
                        put(147, 3);
                        put(-150, 4);
                        put(1019, 4);
                        put(-678, 4);
                        put(554, 4);
                        put(720, 5);
                        put(-248, 4);
                        put(203, 2);
                        put(-909, 3);
                        put(-973, 4);
                        put(10, 5);
                        put(-37, 6);
                        put(-119, 7);
                        put(-249, 5);
                        put(822, 6);
                        put(-799, 4);
                        put(-881, 5);
                        put(436, 5);
                        put(234, 3);
                        put(107, 4);
                        put(1000, 5);
                        put(-54, 8);
                        put(18, 6);
                        put(-821, 6);
                        put(-19, 7);
                        put(-124, 8);
                        put(286, 6);
                        put(-761, 5);
                        put(-708, 6);
                        put(-952, 5);
                        put(-694, 7);
                        put(-84, 9);
                        put(671, 6);
                        put(-453, 6);
                        put(-999, 5);
                        put(693, 7);
                        put(-626, 5);
                        put(383, 6);
                        put(709, 8);
                        put(-978, 6);
                        put(976, 6);
                        put(-743, 7);
                        put(-828, 7);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(190, 55);
                        put(-210, 33);
                        put(-764, 19);
                        put(-521, 9);
                        put(258, 20);
                        put(880, 17);
                        put(493, 11);
                        put(299, 4);
                        put(962, 4);
                        put(100, 12);
                        put(278, 1);
                        put(894, 0);
                        put(92, 9);
                        put(147, 1);
                        put(-150, 8);
                        put(1019, 2);
                        put(-678, 5);
                        put(554, 5);
                        put(720, 4);
                        put(-248, 2);
                        put(203, 1);
                        put(-909, 8);
                        put(-973, 3);
                        put(10, 7);
                        put(-37, 5);
                        put(-119, 3);
                        put(-249, 1);
                        put(822, 0);
                        put(-799, 3);
                        put(-881, 2);
                        put(436, 1);
                        put(234, 0);
                        put(107, 0);
                        put(1000, 1);
                        put(-54, 1);
                        put(18, 0);
                        put(-821, 1);
                        put(-19, 0);
                        put(-124, 0);
                        put(286, 0);
                        put(-761, 3);
                        put(-708, 2);
                        put(-952, 0);
                        put(-694, 0);
                        put(-84, 0);
                        put(671, 2);
                        put(-453, 0);
                        put(-999, 1);
                        put(693, 1);
                        put(-626, 0);
                        put(383, 0);
                        put(709, 0);
                        put(-978, 0);
                        put(976, 0);
                        put(-743, 0);
                        put(-828, 0);
                    }
                }));
        GOOD_TREES.add(new TreeTestInput(new int[]{
                898,
                                238,
                                -90,
                                736,
                                384,
                                -86,
                                656,
                                -819,
                                52,
                                -674,
                                121,
                                -934,
                                -467,
                                86,
                                887,
                                -993,
                                -616,
                                -347,
                                148,
                                -360,
                                194,
                                -102,
                                903,
                                450,
                                -346,
                                160,
                                594,
                                807,
                                602,
                                671,
                                144,
                                -823,
                                -503,
                                -184,
                                -219,
                                -948,
                                -151,
                                877,
                                399,
                                720,
                                634
                 }, 903, -993,
                new LinkedHashMap<Integer, Boolean>() {
                    {
                        put(-357, false);
                        put(-976, false);
                        put(-823, true);
                        put(-345, false);
                        put(-130, false);
                        put(399, true);
                        put(-151, true);
                        put(1022, false);
                        put(-219, true);
                        put(-149, false);
                        put(656, true);
                        put(16, false);
                        put(-327, false);
                        put(579, false);
                        put(724, false);
                        put(898, true);
                        put(-823, true);
                        put(-674, true);
                        put(802, false);
                        put(671, true);
                        put(428, false);
                        put(898, true);
                        put(148, true);
                        put(-725, false);
                        put(-864, false);
                        put(903, true);
                        put(850, false);
                        put(902, false);
                        put(788, false);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(898, 0);
                        put(238, 1);
                        put(-90, 2);
                        put(736, 2);
                        put(384, 3);
                        put(-86, 3);
                        put(656, 4);
                        put(-819, 3);
                        put(52, 4);
                        put(-674, 4);
                        put(121, 5);
                        put(-934, 4);
                        put(-467, 5);
                        put(86, 6);
                        put(887, 3);
                        put(-993, 5);
                        put(-616, 6);
                        put(-347, 6);
                        put(148, 6);
                        put(-360, 7);
                        put(194, 7);
                        put(-102, 7);
                        put(903, 1);
                        put(450, 5);
                        put(-346, 8);
                        put(160, 8);
                        put(594, 6);
                        put(807, 4);
                        put(602, 7);
                        put(671, 5);
                        put(144, 7);
                        put(-823, 5);
                        put(-503, 7);
                        put(-184, 9);
                        put(-219, 10);
                        put(-948, 6);
                        put(-151, 10);
                        put(877, 5);
                        put(399, 6);
                        put(720, 6);
                        put(634, 8);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(898, 40);
                        put(238, 38);
                        put(-90, 24);
                        put(736, 12);
                        put(384, 8);
                        put(-86, 7);
                        put(656, 7);
                        put(-819, 15);
                        put(52, 6);
                        put(-674, 10);
                        put(121, 5);
                        put(-934, 3);
                        put(-467, 9);
                        put(86, 0);
                        put(887, 2);
                        put(-993, 1);
                        put(-616, 1);
                        put(-347, 6);
                        put(148, 3);
                        put(-360, 0);
                        put(194, 1);
                        put(-102, 4);
                        put(903, 0);
                        put(450, 4);
                        put(-346, 3);
                        put(160, 0);
                        put(594, 2);
                        put(807, 1);
                        put(602, 1);
                        put(671, 1);
                        put(144, 0);
                        put(-823, 0);
                        put(-503, 0);
                        put(-184, 2);
                        put(-219, 0);
                        put(-948, 0);
                        put(-151, 0);
                        put(877, 0);
                        put(399, 0);
                        put(720, 0);
                        put(634, 0);
                    }
                }));
        GOOD_TREES.add(new TreeTestInput(new int[]{
                303,
                                551,
                                -477,
                                545,
                                759,
                                380,
                                -554,
                                -516,
                                823,
                                -437,
                                673,
                                -60,
                                -464,
                                204,
                                -792,
                                579,
                                42,
                                -275,
                                -49,
                                391,
                                -400,
                                -963,
                                -527,
                                165,
                                -590,
                                386,
                                128,
                                -38,
                                345,
                                -984,
                                -385,
                                -185,
                                253,
                                -28,
                                -560,
                                92,
                                -879,
                                -946,
                                20,
                                -289,
                                192,
                                778,
                                -423,
                                986,
                                616,
                                956,
                                -732,
                                -903,
                                212,
                                207,
                                360,
                                -1012,
                                693,
                                -602,
                                126,
                                -857,
                                -352,
                                -921,
                                189,
                                920,
                                913,
                                454,
                                -303,
                                -476
                 }, 986, -1012,
                new LinkedHashMap<Integer, Boolean>() {
                    {
                        put(-506, false);
                        put(-1001, false);
                        put(-924, false);
                        put(-281, false);
                        put(-886, false);
                        put(673, true);
                        put(-1012, true);
                        put(260, false);
                        put(-516, true);
                        put(-477, true);
                        put(-289, true);
                        put(435, false);
                        put(778, true);
                        put(-28, true);
                        put(802, false);
                        put(10, false);
                        put(-522, false);
                        put(-385, true);
                        put(1009, false);
                        put(616, true);
                        put(-737, false);
                        put(986, true);
                        put(207, true);
                        put(778, true);
                        put(281, false);
                        put(-712, false);
                        put(-425, false);
                        put(830, false);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(303, 0);
                        put(551, 1);
                        put(-477, 1);
                        put(545, 2);
                        put(759, 2);
                        put(380, 3);
                        put(-554, 2);
                        put(-516, 3);
                        put(823, 3);
                        put(-437, 2);
                        put(673, 3);
                        put(-60, 3);
                        put(-464, 3);
                        put(204, 4);
                        put(-792, 3);
                        put(579, 4);
                        put(42, 5);
                        put(-275, 4);
                        put(-49, 6);
                        put(391, 4);
                        put(-400, 5);
                        put(-963, 4);
                        put(-527, 4);
                        put(165, 6);
                        put(-590, 4);
                        put(386, 5);
                        put(128, 7);
                        put(-38, 7);
                        put(345, 4);
                        put(-984, 5);
                        put(-385, 6);
                        put(-185, 5);
                        put(253, 5);
                        put(-28, 8);
                        put(-560, 5);
                        put(92, 8);
                        put(-879, 5);
                        put(-946, 6);
                        put(20, 9);
                        put(-289, 7);
                        put(192, 7);
                        put(778, 4);
                        put(-423, 6);
                        put(986, 4);
                        put(616, 5);
                        put(956, 5);
                        put(-732, 5);
                        put(-903, 7);
                        put(212, 6);
                        put(207, 7);
                        put(360, 5);
                        put(-1012, 6);
                        put(693, 4);
                        put(-602, 6);
                        put(126, 9);
                        put(-857, 6);
                        put(-352, 8);
                        put(-921, 8);
                        put(189, 8);
                        put(920, 6);
                        put(913, 7);
                        put(454, 5);
                        put(-303, 9);
                        put(-476, 4);
                    }
                },
                new LinkedHashMap<Integer, Integer>() {
                    {
                        put(303, 63);
                        put(551, 18);
                        put(-477, 43);
                        put(545, 6);
                        put(759, 10);
                        put(380, 5);
                        put(-554, 15);
                        put(-516, 1);
                        put(823, 5);
                        put(-437, 26);
                        put(673, 3);
                        put(-60, 23);
                        put(-464, 1);
                        put(204, 14);
                        put(-792, 12);
                        put(579, 1);
                        put(42, 10);
                        put(-275, 7);
                        put(-49, 3);
                        put(391, 2);
                        put(-400, 5);
                        put(-963, 7);
                        put(-527, 0);
                        put(165, 5);
                        put(-590, 3);
                        put(386, 0);
                        put(128, 2);
                        put(-38, 2);
                        put(345, 1);
                        put(-984, 1);
                        put(-385, 3);
                        put(-185, 0);
                        put(253, 2);
                        put(-28, 1);
                        put(-560, 0);
                        put(92, 1);
                        put(-879, 4);
                        put(-946, 2);
                        put(20, 0);
                        put(-289, 2);
                        put(192, 1);
                        put(778, 0);
                        put(-423, 0);
                        put(986, 3);
                        put(616, 0);
                        put(956, 2);
                        put(-732, 1);
                        put(-903, 1);
                        put(212, 1);
                        put(207, 0);
                        put(360, 0);
                        put(-1012, 0);
                        put(693, 0);
                        put(-602, 0);
                        put(126, 0);
                        put(-857, 0);
                        put(-352, 1);
                        put(-921, 0);
                        put(189, 0);
                        put(920, 1);
                        put(913, 0);
                        put(454, 0);
                        put(-303, 0);
                        put(-476, 0);
                    }
                }));
        /* END AUTOGENERATED CODE */

        BAD_TREES.add(new TreeTestInput(new int[]{1, 1, 3}));
        /* BEGIN AUTOGENERATED CODE */
        BAD_TREES.add(new TreeTestInput(new int[]{
            1014,
            97,
            265,
            -30,
            -975,
            -993,
            -377,
            -721,
            -156,
            -205,
            716,
            -205,
            -786,
            -140,
            -68,
            63,
            168,
            525,
            492
        }));
        BAD_TREES.add(new TreeTestInput(new int[]{
            -619,
            -499,
            -825,
            513,
            -140,
            -926,
            -306,
            36,
            -150,
            -646,
            736,
            1023,
            424,
            -554,
            392,
            -411,
            -1015,
            -226,
            -141,
            -656,
            492,
            688,
            -43,
            144,
            630,
            883,
            868,
            -137,
            699,
            1019,
            -804,
            -604,
            223,
            -972,
            -183,
            -554,
            177,
            67,
            238,
            922,
            -820,
            -276,
            172
        }));
        BAD_TREES.add(new TreeTestInput(new int[]{
            257,
            -650,
            -609,
            -823,
            -864,
            -50,
            -33,
            485,
            -698,
            -540,
            -669,
            -980,
            -762,
            1,
            -320,
            -933,
            -879,
            525,
            136,
            -683,
            941,
            -899,
            203,
            -983,
            20,
            766,
            1001,
            766,
            284,
            756
        }));
        BAD_TREES.add(new TreeTestInput(new int[]{
            546,
            -979,
            -662,
            -349,
            448,
            -973,
            -480,
            -376,
            676,
            -853,
            -527,
            644,
            -508,
            -578,
            484,
            -21,
            -299,
            -714,
            -810,
            246,
            -885,
            1011,
            449,
            370,
            674,
            850,
            -469,
            966,
            926,
            -380,
            -677,
            -690,
            -639,
            -721,
            -237,
            -655,
            65,
            -561,
            177,
            -197,
            -80,
            416,
            -511,
            -993,
            -885,
            505,
            480,
            -100
        }));
        BAD_TREES.add(new TreeTestInput(new int[]{
            287,
            344,
            846,
            258,
            26,
            746,
            737,
            -31,
            662,
            935,
            231,
            181,
            249,
            790,
            -692,
            604,
            636,
            -792,
            197,
            -720,
            594,
            215,
            120,
            215,
            -418,
            666,
            601,
            520,
            -624
        }));
        /* END AUTOGENERATED CODE */
    };
}
