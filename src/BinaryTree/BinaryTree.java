package BinaryTree;

import java.util.Stack;
import java.util.Vector;

class BST {
    protected static class Node {
        int value;
        Node left;
        Node right;

        Node() {
           value = -1;
           this.left = null;
           this.right = null;
        }

        Node(int value) {
            this.value = value;
            left = null;
            right = null;
        }

        Node(int value, Node left) {
            this.value = value;
            this.left = left;
            right = null;
        }
        Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        void print() {
            StringBuilder sb = new StringBuilder("Node(");
            sb.append("value: ").append(value).append(", ").append("left: ");
            if (left != null) sb.append(left.value);
            else sb.append((Object) null);
            sb.append(", ").append("right: ");
            if (right != null) sb.append(right.value);
            else sb.append((Object) null);
            sb.append(")");

            System.out.println(sb);
        }

        int getValue() { return this.value; }

        Node getLeft() {
            if (this.left == null) return null;
            return this.left;
        }

        Node getRight() {
            if (this.right == null) return null;
            return this.right;
        }
    }
    Node root;

    BST() {
        root = null;
    }

    BST(int value) {
        root = new Node(value);
    }

    BST(int[] arr) {
        root = null;
        for (int i: arr) this.insert(i);
    }

    Node getNode(int value) {
        if (root == null) return null;
        if (root.value == value) return root;
        Node head = root;
        if (value < root.value) return _getNode(value, head.left);
        return _getNode(value, head.right);
    }

    private Node _getNode(int value, Node head) {
        if (head == null) return null;
        if (head.value == value) return head;
        if (value < head.value) return _getNode(value, head.left);
        return _getNode(value, head.right);
    }

    boolean contains(int value) {
        return this.getNode(value) != null;
    }

    Node getParent(int value) {
        if (root == null || root.value == value) return null;
        if (root.left != null && root.left.value == value) return root;
        if (root.right != null && root.right.value == value) return root;
        Node head = root;
        Node parent = root;
        if (value < head.value) return _getParent(value, head.left, parent);
        return _getParent(value, head.right, parent);
    }

    private Node _getParent(int value, Node head, Node parent) {
        if (head == null) return null;
        if (head.value == value) return parent;
        parent = head;
        if (value < head.value) return _getParent(value, head.left, parent);
        return _getParent(value, head.right, parent);
    }

    BST insert(int value) {
        if (root == null)  {
            root = new Node(value);
            return this;
        }
        Node head = root;
        Node parent = root;

        while (head != null) {
            parent = head;
            if (value < head.value) head = head.left;
            else if (value > head.value) head = head.right;
            else return this;
        }

        if (value > parent.value) parent.right = new Node(value);
        else parent.left = new Node(value);
        return this;
    }

    BST delete(int value) {
        if (root == null) return this;
        if (root.value == value && root.left == null && root.right == null) {
            root = null;
            return this;
        }
        if (root.left == null && root.value == value) {
            root = root.right;
            return this;
        }
        if (root.right == null && root.value == value) {
            root = root.left;
            return this;
        }
        if (root.value == value) {
            Node closestPredecessor = _getClosestNode(value, root.left);
            Node closestSuccessor = _getClosestNode(value, root.right);
            Node closestNode;
            if (closestSuccessor != null && closestPredecessor != null) {
                if (Math.abs(closestSuccessor.value - value) < Math.abs(value - closestPredecessor.value))
                    closestNode = closestSuccessor;
                else closestNode = closestPredecessor;
            }
            else if (closestSuccessor == null) closestNode = closestPredecessor;
            else closestNode = closestSuccessor;

            Node closestParent = getParent(closestNode.value);
            root.value = closestNode.value;
            // NOTE: The closest successor should have no left children,
            // and the closest predecessor should have no right children.
            if (closestParent == root) {
                if (closestNode.right == null) root.left = closestNode.left;
                else if (closestNode.left == null) root.right = closestNode.right;
                return this;
            }
            else if (closestNode.left != null) closestParent.right = closestNode.left;
            else if (closestNode.right != null) closestParent.left = closestNode.right;
            else {
                if (closestParent.left.value == closestNode.value) closestParent.left = null;
                else closestParent.right = null;
            }
        }
        Node head = root;
        Node parent = root;
        if (value < head.value) _delete(head.left, parent, value);
        if (value > head.value) _delete(head.right, parent, value);
        return this;
    }

    BST _delete(Node head, Node parent, int value) {
        if (head == null) return this;
        if (value < head.value) {
            parent = head;
            return _delete(head.left, parent, value);
        }
        if (value > head.value) {
            parent = head;
            return _delete(head.right, parent, value);
        }

        // We're at the node we want to delete.
        // Deleting leaf nodes is simple. Set the parent's reference to null
        if (head.left == null && head.right == null) {
            if (parent.right != null && parent.right.value == head.value) parent.right = null;
            else if (parent.left != null && parent.left.value == head.value) parent.left = null;
            return this;
        }
        // If no left child, the parent's new child is the deleted node's right child.
        if (head.left == null) {
            if (parent.left != null && parent.left.value == head.value) parent.left = head.right;
            else if (parent.right != null && parent.right.value == head.value) parent.right = head.right;
            return this;
        }
        // If no right child, the parent's new child is the deleted node's left child.
        if (head.right == null) {
            if (parent.left != null && parent.left.value == head.value) parent.left = head.left;
            else if (parent.right != null && parent.right.value == head.value) parent.right = head.left;
            return this;
        }
        else {
            Node closestPredecessor = _getClosestNode(value, head.left);
            Node closestSuccessor = _getClosestNode(value, head.right);
            Node closestNode;
            if (closestSuccessor != null && closestPredecessor != null) {
                if (Math.abs(closestSuccessor.value - value) < Math.abs(value - closestPredecessor.value))
                    closestNode = closestSuccessor;
                else closestNode = closestPredecessor;
            }
            else if (closestSuccessor == null) closestNode = closestPredecessor;
            else closestNode = closestSuccessor;

            Node closestParent = getParent(closestNode.value);
            head.value = closestNode.value;
            if (closestParent == head) {
                if (closestNode.right == null) head.left = closestNode.left;
                else if (closestNode.left == null) head.right = closestNode.right;
                return this;
            }
            else if (closestNode.left != null) closestParent.right = closestNode.left;
            else if (closestNode.right != null) closestParent.left = closestNode.right;
            else {
                if (closestParent.left.value == closestNode.value) closestParent.left = null;
                else closestParent.right = null;
            }
        }
        return this;
    }

    // Gets the closest inorder successor/predecessor to replace the deleted node
    private Node _getClosestNode(int value, Node head) {
        if (head == null) return null;
        if (head.left == null && head.right == null) return head;
        if (head.left != null
                && Math.abs(head.left.value - value) < Math.abs(head.value - value)) {
            return _getClosestNode(value, head.left);
        }
        else if (head.right != null
                && Math.abs(head.right.value - value) < Math.abs(head.value - value)) {
            return _getClosestNode(value, head.right);
        }
        return head;
    }

    // Long term: create a pretty print :D
    // for now, just create an array
    void print() {
        if (root == null) {
            System.out.println("[]");
            return;
        }
        Vector<Integer> res = new Vector<Integer>();
        Node head = root;
        res.addElement(head.value);
        // populate vector with _print()
        _getPreorderTraversal(head.left, res);
        _getPreorderTraversal(head.right, res);

        StringBuilder sb = new StringBuilder("[");
        for (Integer i : res)
            sb.append(i).append(", ");

        sb.setLength(sb.length() - 2);
        sb.append("]");

        System.out.println(sb);
    }

    // Preorder Traversal!
    void _getPreorderTraversal(Node head, Vector<Integer> res) {
        if (head == null) return;
        res.addElement(head.value);
        _getPreorderTraversal(head.left, res);
        _getPreorderTraversal(head.right, res);
    }

    void printVerbose() {
        if (root == null) return;
        Node head = root;
        head.print();
        _printVerbose(head.left);
        _printVerbose(head.right);
    }

    private void _printVerbose(Node head) {
        if (head == null) return;
        head.print();
        _printVerbose(head.left);
        _printVerbose(head.right);
    }

    // I wrote this just to test if I need to set the to-be-deleted node to null
    // or if I need to set the parent node (the node containing the reference to the to-be-deleted node)
    // to null. it turns out, I need to update the parent's reference to null and not the node itself
    BST deleteLeaf() {
        if (root == null) return this;
        Node head = root;
        Node parent = root;
        while (!(head.left == null && head.right == null)) {
            parent = head;
            if (head.left != null) head = head.left;
            else head = head.right;
        }
        parent.print();
        if (parent.left == head) parent.left = null;
        else parent.right = null;
        parent.print();
        return this;
    }

    Integer max() {
        if (root == null) return null;
        Node head = root;
        while (head.right != null) head = head.right;
        return head.value;
    }

    Integer min() {
        if (root == null) return null;
        Node head = root;
        while (head.left != null) head = head.left;
        return head.value;
    }
}

class AVLTree {

    protected static class AVLNode {
        // int value;
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

    AVLTree(int value) {
        root = new AVLNode(value);
    }

    AVLTree(int[] arr) {
        for (int i: arr) this.insert(i);
    }

    // callable api function!
    public AVLTree insert(int value) {
        return this.insert_and_rotate(value);
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

        int leftHeight = -1, rightHeight = -1;
        if (head.left != null) leftHeight = head.left.height;
        if (head.right != null) rightHeight = head.right.height;
        head.height = Math.max(leftHeight, rightHeight) + 1;
        int heightDifference = Math.abs(leftHeight - rightHeight);

        if (heightDifference > 1) {
            if (leftHeight > rightHeight) root = rotateRight(head);
            else if (rightHeight > leftHeight) root = rotateLeft(head);
        }

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
        int leftHeight = -1, rightHeight = -1;
        if (head.left != null) leftHeight = head.left.height;
        if (head.right != null) rightHeight = head.right.height;
        head.height = Math.max(leftHeight, rightHeight) + 1;
        int heightDifference = Math.abs(leftHeight - rightHeight);

        // figure out how we should rotate the tree.
        if (heightDifference > 1) {
            AVLNode newChild;
            if (leftHeight > rightHeight) newChild = rotateRight(head);
            else newChild = rotateLeft(head);

            if (parent.left == head) parent.left = newChild;
            else parent.right = newChild;
        }

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
            newSubPivot.left = subPivot;
            subPivot.height -= 1;
            newSubPivot.height += 1;
            subPivot = newSubPivot;
        }
        // now rotate on the pivot. subPivot takes the place of the pivot.
        pivot.height -= 2;
        pivot.left = subPivot.right;
        subPivot.right = pivot;
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
            newSubPivot.right = subPivot;
            subPivot.height -= 1;
            newSubPivot.height += 1;
            subPivot = newSubPivot;
        }
        // now rotate on the pivot. subPivot takes the place of the pivot.
        pivot.height -= 2;
        pivot.right = subPivot.left;
        subPivot.left = pivot;
        pivot = subPivot;

        return pivot;
    }

    AVLTree delete(int value) {
        return this;
    }

    void print() {
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

    void _getPreorderTraversal(AVLNode head, Vector<Integer> res) {
        if (head == null) return;
        res.addElement(head.value);
        _getPreorderTraversal(head.left, res);
        _getPreorderTraversal(head.right, res);
    }

    void printVerbose() {
        if (root == null) return;
        root.print();
        _printVerbose(root.left);
        _printVerbose(root.right);
    }

    void _printVerbose(AVLNode head) {
        if (head == null) return;
        head.print();
        _printVerbose(head.left);
        _printVerbose(head.right);
    }


}
