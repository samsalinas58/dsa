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


        AVLTree a1 = new AVLTree(50);
        a1.insert(30);
        a1.insert(70);
        a1.insert(40);
        a1.insert(45);
        a1.insert(20);
        a1.insert(80);
        a1.insert(60);
        a1.insert(75);
        a1.print();
        a1.printVerbose();

        int[] arr = {50, 30, 70, 40, 45, 20, 80, 60, 75};
        AVLTree a2 = new AVLTree(arr);
        a2.print();
        a2.printVerbose();

    }
}