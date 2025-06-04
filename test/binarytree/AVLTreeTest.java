
package binarytree;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import main.binarytree.AVLTree;

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

    // For the insertion tests, the constructor directly calls insert() when constructing the tree
    // using an array as the parameter.
    @Test
    void testAVLTreeEmptyInsertion() {
        int[] expected = {1};
        AVLTree a = new AVLTree();
        a.insert(1);

        assert(a.equals(expected));
    }

    @Test
    void testAVLTreeInsertion_valueAlreadyExists() {
        int[] tree = {100,95,105,96,104};
        int[] expected = {100,95,96,105,104};
        AVLTree a = new AVLTree(tree);
        for (int i: tree) a.insert(i);

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

    @Test
    void testAVLTreeDeletion_emptyTree() {
        int[] expected = {};
        AVLTree a = new AVLTree();

        a.delete(100);
        assert(a.equals(expected));
    }

    @Test
    void testAVLTree_valueDoesntExist() {
        int[] tree = {100,95,105,94,106};
        int[] expected = {100,95,94,105,106};
        AVLTree a = new AVLTree(tree);
        a.delete(97);
        a.delete(98);

        assert(a.equals(expected));
    }

    @Test
    void testAVLTreeDeletion_rootWIthNoSubTree() {
        int[] expected = {};
        AVLTree a = new AVLTree(100);
        a.delete(100);

        assert(a.equals(expected));
    }

    @Test
    void testAVLTreeDeletion_rootWithChildNoRotation() {
        int[] tree = {100,95};
        int[] expected = {95};
        AVLTree a = new AVLTree(tree);
        a.delete(100);

        assert(a.equals(expected));
    }

    @Test
    void testAVLTreeDeletion_leafNoRotation() {
        int[] tree = {100,95,105,90,97};
        int[] expected = {100,95,90,105};
        AVLTree a = new AVLTree(tree);
        a.delete(97);

        assert(a.equals(expected));
    }

    @Test
    void testAVLTreeDeletion_rootSubtreeNoRotation() {
        int[] tree = {100,95,105,90,97};
        int[] expected = {97,95,90,105};
        AVLTree a = new AVLTree(tree);
        a.delete(100);

        assert(a.equals(expected));
    }

    @Test
    void testAVLTreeDeletion_leftRotationAtRoot() {
        int[] tree = {100, 95, 105, 108};
        int[] expected = {105,100,108};
        AVLTree a = new AVLTree(tree);
        a.delete(95);

        assert(a.equals(expected));
    }

    @Test
    void testAVLTreeDeletion_rightRotationAtRoot() {
        int[] tree = {100,95,105,90};
        int[] expected = {95,90,100};
        AVLTree a = new AVLTree(tree);
        a.delete(105);

        assert(a.equals(expected));
    }

    @Test
    void testAVLTreeDeletion_rightLeftRotationAtRoot() {
        int[] tree = {100,99,105,103};
        int[] expected = {103,100,105};
        AVLTree a = new AVLTree(tree);
        a.delete(99);

        assert(a.equals(expected));
    }

    @Test
    void testAVLTreeDeletion_leftRightRotationAtRoot() {
        int[] tree = {100,105,97,98};
        int[] expected = {98,97,100};
        AVLTree a = new AVLTree(tree);
        a.delete(105);

        assert(a.equals(expected));
    }

    @Test
    void testAVLTreeDeletion_subtreeLeftRotation() {
        int[] tree = {100,105,95,90,101,110,111};
        int[] expected = {101,95,90,110,105,111};
        AVLTree a = new AVLTree(tree);
        a.delete(100);

        assert(a.equals(expected));
    }

    @Test
    void testAVLTreeDeletion_subtreeRightRotation() {
        int[] tree = {100,105,95,90,104,110,103};
        int[] expected = {100,95,90,104,103,105};
        AVLTree a = new AVLTree(tree);
        a.delete(110);

        assert(a.equals(expected));
    }

    @Test
    void testAVLTreeDeletion_subtreeLeftRightRotation() {
        int[] tree = {100,105,95,90,103,110,104};
        int[] expected = {100,95,90,104,103,105};
        AVLTree a = new AVLTree(tree);
        a.delete(110);

        assert(a.equals(expected));
    }

    @Test
    void testAVLTreeDeletion_subTreeRightLeftRotation() {
        int[] tree = {100,90,110,80,101,120,115};
        int[] expected = {101,90,80,115,110,120};
        AVLTree a = new AVLTree(tree);
        a.delete(100);

        assert(a.equals(expected));
    }
}