package binarytree;

import main.binarytree.BST;
import main.binarytree.BST.Node;
import org.junit.jupiter.api.Test;

import java.util.Stack;

class BSTTest {

    @Test
    void testInsertEmptyTree() {
        BST b = new BST();
        BST c = new BST();
        b.insert(1);
        c.insert(1);
        boolean actual = b.equals(c);
        boolean expected = true;

        assert(actual == expected);
    }

    @Test
    void testInsertCreatesValidBST() {
        int[] tree = {4,8,2,16,5,3,0,1,15,0};
        BST b = new BST(tree);
        Stack<Node> stk = new Stack<>();
        stk.push(b.getRoot());
        while (!stk.empty()) {
            Node top = stk.peek();
            Node left = top.getLeft(), right = top.getRight();
            stk.pop();
            if (right != null) {
                assert right.getValue() > top.getValue();
                stk.push(right);
            }
            if (left != null) {
                assert left.getValue() < top.getValue();
                stk.push(left);
            }
        }
    }

    @Test
    void testEqualsEmptyTree() {
        BST b = new BST();
        BST c = new BST();
        boolean actual = b.equals(c);
        boolean expected = true;

        assert(actual == expected);
    }

    @Test
    void testEqualsDifferentRoot() {
        BST b = new BST(1);
        BST c = new BST(2);
        boolean actual = b.equals(c);
        boolean expected = false;

        assert(actual == expected);
    }

    @Test
    void testEqualsDifferentSubtree() {
        int[] tree1 = {2,1,3};
        int[] tree2 = {2,1,4};
        BST b = new BST(tree1);
        BST c = new BST(tree2);
        boolean actual = b.equals(c);
        boolean expected = false;

        assert(actual == expected);
    }

    @Test
    void testEqualsSameRootSameSubtree() {
        int[] tree = {2,1,3};
        BST b = new BST(tree);
        BST c = new BST(tree);
        boolean actual = b.equals(c);
        boolean expected = true;

        assert(actual == expected);
    }

    @Test
    void testDeletionEmptyTree() {
        BST b = new BST();
        BST c = new BST();
        b.delete(4);
        boolean actual = b.equals(c);
        boolean expected = true;

        assert(expected == actual);
    }

    @Test
    void testDeletionOnlyRoot() {
        BST b = new BST(4);
        BST c = new BST(4);
        b.delete(4);
        boolean actual = b.equals(c);
        boolean expected = false;

        assert(expected == actual);
    }

    @Test
    void testDeletionValueDoesntExist() {
        int[] tree = {4,8,2,16,5,3,0,1,15,0};
        BST b = new BST(tree);
        BST c = new BST(tree);
        b.delete(100);
        boolean actual = b.equals(c);
        boolean expected = true;

        assert(expected == actual);
    }

    @Test
    void testDeletionLeaf() {
        int[] tree = {4,2,6};
        int[] expected_tree = {4,6};
        BST b = new BST(tree);
        BST c = new BST(expected_tree);
        b.delete(2);
        boolean actual = b.equals(c);
        boolean expected = true;

        assert(expected == actual);
    }

    @Test
    void testDeletionInorderReplacesRoot() {
        int[] tree = {4,2,3,6};
        int[] expected_tree = {3,2,6};
        BST b = new BST(tree);
        BST c = new BST(expected_tree);
        b.delete(4);
        boolean actual = b.equals(c);
        boolean expected = true;

        assert(expected == actual);
    }

    @Test
    void testDeletionInorderReplacesSubtree() {
        int[] tree = {4,8,2,16,5,3,0,1,15,0};
        int[] expected_tree = {4,2,0,1,3,5,16,15};
        BST b = new BST(tree);
        BST c = new BST(expected_tree);
        b.delete(8);
        boolean actual = b.equals(c);
        boolean expected = true;

        assert(expected == actual);
    }

    @Test
    void testMax() {
        int[] tree = {4,8,2,16,5,3,0,1,15,0};
        BST b = new BST(tree);
        int actual = b.max();
        int expected = 16;

        assert(actual == expected);
    }

    @Test
    void testMin() {
        int[] tree = {4,8,2,16,5,3,0,1,15,0};
        BST b = new BST(tree);
        int actual = b.min();
        int expected = 0;

        assert(actual == expected);
    }

    @Test
    void testGetNodeReturnsNull() {
        int[] tree = {4,8,2,16,5,3,0,1,15,0};
        int value = 9;
        BST b = new BST(tree);
        Node actual = b.getNode(value);
        Node expected = null;

        assert(actual == expected);
    }

}