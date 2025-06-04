package main.binarytree;

import java.util.Stack;
import java.util.Vector;

public class BST {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

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

        public void print() {
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

        public int getValue() { return this.value; }

        public Node getLeft() {
            if (this.left == null) return null;
            return this.left;
        }

        public Node getRight() {
            if (this.right == null) return null;
            return this.right;
        }
    }
    Node root;

    public BST() {
        root = null;
    }

    public BST(int value) {
        root = new Node(value);
    }

    public BST(int[] arr) {
        root = null;
        for (int i: arr) this.insert(i);
    }

    public Node getNode(int value) {
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

    public Node getRoot() {
        return root;
    }

    public boolean find(int value) {
        return this.getNode(value) != null;
    }

    public Node getParent(int value) {
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

    public BST insert(int value) {
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

    public BST delete(int value) {
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
            if (closestParent.right == closestNode) closestParent.right = closestNode.left;
            else if (closestParent.left == closestNode) closestParent.left = closestNode.right;
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
            else if (closestParent.right == closestNode) closestParent.right = closestNode.left;
            else if (closestParent.left == closestNode) closestParent.left = closestNode.right;
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
        // populate vector with _getPreorderTraversal()
        _getPreorderTraversal(head.left, res);
        _getPreorderTraversal(head.right, res);

        StringBuilder sb = new StringBuilder("[");
        for (Integer i : res)
            sb.append(i).append(", ");

        sb.setLength(sb.length() - 2);
        sb.append("]");

        System.out.println(sb);
    }

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

    public Integer max() {
        if (root == null) return null;
        Node head = root;
        while (head.right != null) head = head.right;
        return head.value;
    }

    public Integer min() {
        if (root == null) return null;
        Node head = root;
        while (head.left != null) head = head.left;
        return head.value;
    }

    boolean equals(int[] arr) {
        Vector<Integer> vec = new Vector<Integer>();
        _getPreorderTraversal(root, vec);

        if (arr.length != vec.size()) return false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != vec.get(i)) return false;
        }

        return true;
    }

    public boolean equals(BST b) {
        if (root == null && b.root == null) return true;
        if (root == null || b.root == null) return false;

        if (root.value != b.root.value) return false;

        return _equals(root.left, b.root.left) && _equals(root.right, b.root.right);
    }

    private boolean _equals(Node a, Node b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;

        if (a.value != b.value) return false;

        return _equals(a.left, b.left) && _equals(a.right, b.right);
    }
}