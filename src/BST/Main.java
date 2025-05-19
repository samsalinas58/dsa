package BST;

public class Main {
    public static void main(String[] args) {
        int[] bst = {20, 1, 0, 2, 40, 21, 41};

        BST b = new BST(bst);
        b.print();
        b.printVerbose();

    }
}