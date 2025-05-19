package BST;

public class Main {
    public static void main(String[] args) {
        int[] bst = {20, 1, 0, 2, 40, 23, 22, 21, 41, 24, 25};

        BST b = new BST(bst);
        b.print();
        // b.printVerbose();
        b.delete(23).print();
        System.out.println(b.contains(25)); // true
        System.out.println(b.getNode(25)); // returns a Node
        b.delete(25).print();
        System.out.println(b.contains(25)); // true
        System.out.println(b.getNode(25)); // returns a Node

        System.out.println();
        int[] bst2 = {23, 22, 21, 24, 25};
        BST c = new BST(bst2);
        System.out.println(c.contains(23)); // true
        System.out.println(c.getNode(23));
        c.delete(23); //[22,21,24,25]
        System.out.println(c.getNode(23)); // null
        System.out.println(c.contains(23)); // false
        c.print();
        c.delete(21);//[22,24,25]
        c.print();
        c.delete(24);
        c.print();//[22,25]
        c.delete(22);
    }
}