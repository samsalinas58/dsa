package main.binarytree;

import java.util.Vector;

public class AVLTree {

    private static class AVLNode {
        int value;
        int height = 0;
        AVLNode left = null;
        AVLNode right = null;

        AVLNode(int value){
            this.value = value;
        }

        AVLNode(int value, AVLNode left, AVLNode right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        void print() {
            StringBuilder sb = new StringBuilder("AVLNode(");
            sb.append("value: ").append(value).append(", ");
            sb.append("height: ").append(height).append(", ");

            sb.append("left: ");
            if (left != null) sb.append(left.value);
            else sb.append((Object) null);
            sb.append(", ");

            sb.append("right: ");
            if (right != null) sb.append(right.value);
            else sb.append((Object) null);
            sb.append(")");

            System.out.println(sb);
        }
    }

    AVLNode root = null;

    public AVLTree() { root = null; }

    public AVLTree(int value) {
        root = new AVLNode(value);
    }

    public AVLTree(int[] arr) {
        for (int i: arr) this.insert(i);
    }

    // callable api function!
    public AVLTree insert(int value) {
        return this.insert_and_rotate(value);
    }

    // Private function used during rotations to update the heights of the rotated nodes.
    private void updateHeight(AVLNode node) {
        if (node == null) return;
        int leftHeight = -1, rightHeight = -1;
        if (node.left != null) leftHeight = node.left.height;
        if (node.right != null) rightHeight = node.right.height;
        node.height = Math.max(leftHeight, rightHeight) + 1;
    }

    private AVLTree insert_and_rotate(int value) {
        if (root == null) {
            root = new AVLNode(value);
            return this;
        }
        if (root.value == value) return this;
        AVLNode head = root;

        AVLTree ret;
        if (value < head.value) ret = _insert_and_rotate(value, head.left, head);
        else ret = _insert_and_rotate(value, head.right, head);
        if (ret == null) return this;

        root = rotateIfNecessary(head);
        return this;
    }

    private AVLTree _insert_and_rotate(int value, AVLNode head, AVLNode parent) {
        // Insertion
        if (head != null) {
            if (head.value == value) return null;
            AVLTree ret;
            if (value < head.value) ret = _insert_and_rotate(value, head.left, head);
            else ret = _insert_and_rotate(value, head.right, head);
            // we cannot do anything if the node already exists in the tree.
            // we must make sure nothing is updated
            if (ret == null) return this;
        }
        else {
            head = new AVLNode(value);
            if (value < parent.value) parent.left = head;
            else parent.right = head;
            // we are at a leaf node. do not rotate at the leaf!
            return this;
        }

        //update the heights as we traverse back up the tree and then rotate if needed
        AVLNode rotated = rotateIfNecessary(head);
        if (parent.left == head) parent.left = rotated;
        else parent.right = rotated;

        return this;
    }

    // rotates the AVLTree (subtree) with the AVLNode pivot
    private AVLNode rotateRight(AVLNode pivot) {
        assert(pivot.left != null);
        AVLNode subPivot = pivot.left;
        // left subtree must be checked before we rotate right!
        int leftRightSubtreeHeight = -1;
        int leftLeftSubtreeHeight = -1;

        //rotate subPivot to the left if necessary
        if (subPivot.right != null) leftRightSubtreeHeight = subPivot.right.height;
        if (subPivot.left != null) leftLeftSubtreeHeight = subPivot.left.height;

        if (leftRightSubtreeHeight > leftLeftSubtreeHeight) {
            AVLNode newSubPivot = subPivot.right;
            subPivot.right = newSubPivot.left;
            updateHeight(subPivot); // recalculate the heights as we rotate!
            newSubPivot.left = subPivot;
            updateHeight(newSubPivot);
            subPivot = newSubPivot;
        }
        // now rotate on the pivot. subPivot takes the place of the pivot.
        pivot.left = subPivot.right;
        updateHeight(pivot);
        subPivot.right = pivot;
        updateHeight(subPivot);
        pivot = subPivot;

        return pivot;
    }

    private AVLNode rotateLeft(AVLNode pivot) {
        assert(pivot.right != null);
        AVLNode subPivot = pivot.right;
        // left subtree must be checked before we rotate right!
        int rightRightSubtreeHeight = -1;
        int rightLeftSubtreeHeight = -1;

        if (subPivot.right != null) rightRightSubtreeHeight = subPivot.right.height;
        if (subPivot.left != null) rightLeftSubtreeHeight = subPivot.left.height;

        //rotate subPivot to the right if necessary
        if (rightLeftSubtreeHeight > rightRightSubtreeHeight) {
            AVLNode newSubPivot = subPivot.left;
            subPivot.left = newSubPivot.right;
            updateHeight(subPivot);
            newSubPivot.right = subPivot;
            updateHeight(newSubPivot);
            subPivot = newSubPivot;
        }
        // now rotate on the pivot. subPivot takes the place of the pivot.
        pivot.right = subPivot.left;
        updateHeight(pivot);
        subPivot.left = pivot;
        updateHeight(subPivot);
        pivot = subPivot;

        return pivot;
    }

    // if the tree has a balance factor > 1, where balance = abs(leftHeight - rightHeight) and null nodes
    // being treated as -1, then the tree will be rotated. if not, then the pivot is returned
    // as it was given.
    private AVLNode rotateIfNecessary(AVLNode pivot) {
        assert(pivot != null);
        int leftHeight = -1, rightHeight = -1;
        if (pivot.left != null) leftHeight = pivot.left.height;
        if (pivot.right != null) rightHeight = pivot.right.height;
        int balance = Math.abs(leftHeight - rightHeight);

        if (balance > 1) {
            if (rightHeight > leftHeight) pivot = rotateLeft(pivot);
            else if (leftHeight > rightHeight) pivot = rotateRight(pivot);
        }
        updateHeight(pivot);
        return pivot;
    }

    public AVLTree delete(int value) {
        if (root == null) return this;
        if (root.value == value && root.left == null && root.right == null) {
            root = null;
            return this;
        }
        AVLNode head = root;
        return this.delete_and_rotate(value, head, null);
    }

    private int getBalance(AVLNode head) {
        if (head == null) return -1;
        int leftHeight = -1, rightHeight = -1;
        if (head.left != null) leftHeight = head.left.height;
        if (head.right != null) rightHeight = head.right.height;

        return Math.abs(rightHeight - leftHeight);
    }

    public boolean isEmpty() { return root == null; }

    public Integer getRoot() {
        if (root == null) return null;
        return root.value;
    }

    // Go to the node we are trying to delete.
    // After we have arrived, grab it's closest inorder node's value.
    // Go back to the node we grabbed, and delete it from the tree, giving the parent a new child and adjusting the
    // height of the parent.
    // travel back up the tree and rotate as necessary.
    private AVLTree delete_and_rotate(int value, AVLNode head, AVLNode parent) {
        if (head == null) return this;
        if (value < head.value) delete_and_rotate(value, head.left, head);
        else if (value > head.value) delete_and_rotate(value, head.right, head);

            // we are at the node we want to replace.
        else {
            AVLNode closest = getClosestInorderNode(head);
            if (closest == null) {
                if (parent.left == head) parent.left = null;
                else parent.right = null;
                return this;
            }
            if (closest.value < value) removeClosestInorderNodeAndRotate(closest.value, head.left, head);
            else removeClosestInorderNodeAndRotate(closest.value, head.right, head);
            head.value = closest.value;
        }

        updateHeight(head);
        AVLNode rotated = rotateIfNecessary(head);

        if (root == head) root = rotated;
        else if (parent.left == head) parent.left = rotated;
        else if (parent.right == head) parent.right = rotated;

        return this;
    }

    private AVLTree removeClosestInorderNodeAndRotate(int value, AVLNode head, AVLNode parent) {
        assert(head != null);

        if (value < head.value) removeClosestInorderNodeAndRotate(value, head.left, head);
        else if (value > head.value) removeClosestInorderNodeAndRotate(value, head.right, head);

            // we're at the node we want to delete!
        else {
            // parent is either original node being overwritten OR in left subtree
            if (parent.right == head) {
                // parent is the original node being overwritten
                if (head.right != null) parent.right = head.right;
                    // parent is in the subtree
                else parent.right = head.left;
            }
            // we are either at original node being overwritten OR in the right subtree
            else if (parent.left == head) {
                // parent is the original node being overwritten
                if (head.left != null) parent.left = head.left;
                    // parent is in the right subtree
                else parent.left = head.right;
            }
            return this;
        }

        updateHeight(head);
        AVLNode rotated = rotateIfNecessary(head);
        if (parent.left == head) parent.left = rotated;
        else parent.right = rotated;

        return this;
    }

    private AVLNode getClosestInorderNode(AVLNode head) {
        if (head == null) return null;

        int value = head.value;
        AVLNode leftClosest = _getClosestInorderNode(value, head.left);
        AVLNode rightClosest = _getClosestInorderNode(value, head.right);

        if (leftClosest == null && rightClosest == null) return null;
        if (leftClosest == null) return rightClosest;
        if (rightClosest == null) return leftClosest;


        int rightDifference = Math.abs(rightClosest.value - value);
        int leftDifference = Math.abs(leftClosest.value - value);

        if (leftDifference <= rightDifference) return leftClosest;
        return rightClosest;
    }

    private AVLNode _getClosestInorderNode(int value, AVLNode head) {
        if (head == null) return null;

        if (head.value < value) {
            while (head.right != null) head = head.right;
            return head;
        }
        while (head.left != null) head = head.left;
        return head;
    }

    public void print() {
        if (root == null) {
            System.out.println("[]");
            return;
        }
        Vector<Integer> res = new Vector<Integer>();
        AVLNode head = root;
        res.addElement(head.value);
        _getPreorderTraversal(head.left, res);
        _getPreorderTraversal(head.right, res);

        StringBuilder sb = new StringBuilder("[");
        for (Integer i : res)
            sb.append(i).append(", ");

        sb.setLength(sb.length() - 2);
        sb.append("]");

        System.out.println(sb);
    }

    private void _getPreorderTraversal(AVLNode head, Vector<Integer> res) {
        if (head == null) return;
        res.addElement(head.value);
        _getPreorderTraversal(head.left, res);
        _getPreorderTraversal(head.right, res);
    }

    public void printVerbose() {
        if (root == null) return;
        root.print();
        _printVerbose(root.left);
        _printVerbose(root.right);
    }

    private void _printVerbose(AVLNode head) {
        if (head == null) return;
        head.print();
        _printVerbose(head.left);
        _printVerbose(head.right);
    }

    // expected should be the preorder traversal in array form of the tree that we expect.
    // actual is going to be the preorder traversal of the current tree that is present.
    public boolean equals(int[] expected) {
        Vector<Integer> actual = new Vector<Integer>();
        _getPreorderTraversal(root, actual);

        if (actual.size() != expected.length) return false;

        for (int i = 0; i < expected.length; i++) {
            if (actual.get(i) != expected[i]) return false;
        }
        return true;
    }


}
