
package binarytree;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import main.binarytree.*;

// README: Currently, the testing is done with my own equals() function written inside the AVLTree
// class. This is not a good practice to follow, but this is how it is for now. Should the equals()
// function ever change to follow a different design, all the tests WILL break. This is something to keep in mind
// when I design actual tests in the future for a company. If I were to NOT do this lazily, I would manually
// start from the root find the actual and expected values of each node using atomic operations, as using functions
// which are bound to change will break tests when they aren't meant to.

// Some examples:
//      assert(node.value == 5);
//      assert(node.left == null);
//      assert(node.right != null);
//      node = node.right;
//      assert(node.value == 10);

// As it stands, the equals() function grabs the preorder traversal of the tree and returns the array format,
// so the test cases must be written with that in mind.

class AVLTreeTest {

    @Test
    void testAVLTreeEmptyInsertion() {
        int[] expected = {1};
        AVLTree a = new AVLTree();
        a.insert(1);

        assert(a.equals(expected));
    }

    @Test
    void testAVLTreeInsertion_LeftRotationAtRoot() {
        int[] tree = {57,58,59};
        int[] expected = {58,57,59};
        AVLTree a = new AVLTree(tree);


        assert(a.equals(expected));
    }

    @Test
    void testAVLTreeInsertion_RightRotationAtRoot() {
        int[] tree = {59,58,57};
        int[] expected = {58,57,59};
        AVLTree a = new AVLTree(tree);

        assert(a.equals(expected));
    }

    // rotate inner subtree right, then left at the root
    @Test
    void testAVLTreeInsertion_RightLeftRotationAtRoot() {
        int[] tree = {55,54,60,58,61,57};
        int[] expected = {58,55,54,57,60,61};
        AVLTree a = new AVLTree(tree);

        assert(a.equals(expected));
    }

    // rotate inner subtree left, then rotate right at root
    @Test
    void testAVLTreeInsertion_LeftRightRotationAtRoot() {
        int[] tree = {55,50,60,45,53,52};
        int[] expected = {53,50,45,52,55,60};
        AVLTree a = new AVLTree(tree);

        assert(a.equals(expected));
    }

    @Test
    void testAVLTreeInsertion_rotateInnerTreeRight() {
        int[] tree = {55,50,60,59,58};
        int[] expected = {55,50,59,58,60};
        AVLTree a = new AVLTree(tree);

        assert(a.equals(expected));
    }

    @Test
    void testAVLTreeInsertion_rotateInnerTreeLeft() {
        int[] tree = {55,50,60,61,62};
        int[] expected = {55,50,61,60,62};
        AVLTree a = new AVLTree(tree);

        assert(a.equals(expected));
    }

    @Test
    void testAVLTreeInsertion_rotateInnerTreeLeftRight() {
        int[] tree = {55,50,60,58,59};
        int[] expected = {55,50,59,58,60};
        AVLTree a = new AVLTree(tree);

        assert(a.equals(expected));
    }

    @Test
    void testAVLTreeInsertion_rotateInnerTreeRightLeft() {
        int[] tree = {55,50,60,62,61};
        int[] expected = {55,50,61,60,62};
        AVLTree a = new AVLTree(tree);

        assert(a.equals(expected));
    }
}