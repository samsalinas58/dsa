package BST;

import java.util.Stack;
import java.util.Vector;

class BST {
    private static class Node {
        int value;
        Node left;
        Node right;

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
        Node head = root;
        Node parent = root;
        if (root.value == value) {
            Node closest = _getClosestNode(head);
        }
        if (value < head.value) _delete(head.left, parent, value);
        if (value > head.value) _delete(head.right, parent, value);
        return this;
    }

    BST _delete(Node head, Node parent, int value) {
        if (head == null) return this;
        if (head.value == value && head.left == null && head.right == null) {

        }
        return this;
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

    private Node _getClosestNode(Node head) {
        Node inOrderPredecessor, inOrderSuccessor;
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
        _getTree(head.left, res);
        _getTree(head.right, res);

        StringBuilder sb = new StringBuilder("[");
        for (Integer i : res)
            sb.append(i).append(", ");

        sb.setLength(sb.length() - 2);
        sb.append("]");

        System.out.println(sb);
    }

    private void _getTree(Node head, Vector<Integer> res) {
        if (head == null) return;
        res.addElement(head.value);
        _getTree(head.left, res);
        _getTree(head.right, res);
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

}
