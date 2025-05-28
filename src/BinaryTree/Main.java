package BinaryTree;

import BinaryTree.BST;

public class Main {
    public static void main(String[] args) {
   //      int[] bst = {20, 1, 0, 2, 40, 23, 22, 21, 41, 24, 25};

   //      BST b = new BST(bst);
   //      b.print();
   //      // b.printVerbose();
   //      b.delete(23).print();
   //      System.out.println(b.contains(25)); // true
   //      System.out.println(b.getNode(25)); // returns a Node
   //      b.delete(25).print();
   //      System.out.println(b.contains(25)); // true
   //      System.out.println(b.getNode(25)); // returns a Node

   //      System.out.println();
   //      int[] bst2 = {23, 22, 21, 24, 25};
   //      BST c = new BST(bst2);
   //      System.out.println(c.contains(23)); // true
   //      System.out.println(c.getNode(23));
   //      c.delete(23); //[22,21,24,25]
   //      System.out.println(c.getNode(23)); // null
   //      System.out.println(c.contains(23)); // false
   //      c.print();
   //      c.delete(21);//[22,24,25]
   //      c.print();
   //      c.delete(24);
   //      c.print();//[22,25]
   //      c.delete(22);



        int[] arr = {50, 30, 70, 40, 45, 20, 80, 60, 75};
        System.out.println("Creating new AVL tree inserting in order: " + arrToString(arr));
        AVLTree a2 = new AVLTree(arr);


        while (!a2.isEmpty()) {
            a2.print();
            int root = a2.getRoot();
            a2.delete(root);
        } // works good!

        System.out.println("Creating new AVL tree inserting in order: " + arrToString(arr));
        AVLTree a3 = new AVLTree(arr);
        a3.print();
        a3.delete(60);
        a3.print();
        a3.delete(75);
        a3.print();
        a3.delete(30);
        a3.print();
    }

    static String arrToString(int[] arr) {
        if (arr.length == 0) return "[]";

        StringBuilder sb = new StringBuilder("[");
        for (int i: arr) sb.append(i).append(", ");

        sb.setLength(sb.length() - 2);
        sb.append("]");
        return sb.toString();
    }
}