package main.arrays;

import main.arrays.Stack;
import main.arrays.Queue;
import main.arrays.Sort;

class Main {
    public static void main(String[] args) {
     //   Stack s = new Stack(1);
     //   s.print();
     //   s.push(2).push(3).push(4);
     //   s.print();

     //   System.out.println(s.top());
     //   s.pop().pop().pop().pop();
     //   s.print();

     //   s.pop(); // EmptyStackException!

      //   Queue q = new Queue(1);
      //   q.print();

      //   q.push(2).push(3).push(4);
      //   q.pop();
      //   q.push(1);
      //   q.push(5);
      //   q.pop();
      //   q.push(6);
      //   q.print();
      //   q.pop().pop().pop().pop().pop();
      //   q.print();

        int[] arr = {9,8,7,6,5,4,3,2,1};
        int[] sorted = Sort.bubbleSort(arr);
        System.out.println(arrToString(sorted));

        arr = new int[]{9,9,8,8,7,7,6,6,5,5,4,4,3,3,2,2,1,1};
        sorted = Sort.bubbleSort(arr);
        System.out.println(arrToString(sorted));

        arr = new int[]{8, 3, 2, 0, 10};
        sorted = Sort.bubbleSort(arr);
        System.out.println(arrToString(sorted));

        arr = new int[]{9,8,7,6,5,4,3,2,1};
        sorted = Sort.selectionSort(arr);
        System.out.println(arrToString(sorted));

        arr = new int[]{9,9,8,8,7,7,6,6,5,5,4,4,3,3,2,2,1,1};
        sorted = Sort.selectionSort(arr);
        System.out.println(arrToString(sorted));

        arr = new int[]{9,8,7,6,5,4,3,2,1};
        sorted = Sort.insertionSort(arr);
        System.out.println(arrToString(sorted));

        arr = new int[]{9,9,8,8,7,7,6,6,5,5,4,4,3,3,2,2,1,1};
        sorted = Sort.insertionSort(arr);
        System.out.println(arrToString(sorted));

        arr = new int[]{8, 3, 2, 0, 10};
        sorted = Sort.insertionSort(arr);
        System.out.println(arrToString(sorted));

        arr = new int[]{8, 3, 2, 0, 10};
        sorted = Sort.mergeSort(arr);
        System.out.println(arrToString(sorted));

        arr = new int[]{9,9,8,8,7,7,6,6,5,5,4,4,3,3,2,2,1,1};
        sorted = Sort.mergeSort(arr);
        System.out.println(arrToString(sorted));

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