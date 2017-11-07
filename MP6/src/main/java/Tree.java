/**
 * A class that implements a basic binary tree storing integers.
 * <p>
 * You need to complete this class by adding methods to insert values, compute the minimum and
 * maximum, search for a value, and compute a value's depth in the tree and number of descendants.
 * You will also need to complete several constructors used during tree creation and value
 * insertion.
 * <p>
 * Our binary tree is an example of a <i>recursive data structure</i>, since each tree instance
 * refers to several other tree instances to complete the entire structure. As you might expect,
 * recursive algorithms are particularly useful on recursive data structures.
 *
 * @see <a href="https://cs125.cs.illinois.edu/MP/6/">MP6 Documentation</a>
 * @see <a href="https://en.wikipedia.org/wiki/Binary_tree">Binary Tree</a>
 */
public class Tree {

    /* Current node's parent. May be null if I'm the root of the tree. */
    private Tree parent;

    /* Current node's left child, or null if none. */
    private Tree left;

    /* Current node's left child, or null if none. */
    private Tree right;

    /* Current node's value. */
    private int value;
}
