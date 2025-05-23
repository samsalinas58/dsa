package Arrays;

import java.util.EmptyStackException;
import java.util.Vector;

class Stack {
    private int[] arr;
    private int size = 0;

    Stack() {
        int capacity = 1;
        arr = new int[capacity];
    }

    Stack(int value) {
        size = 1;
        arr = new int[]{value};
    }

    // TODO: Implement later when I can properly implement capacity
    Stack (int[] arr) {

    }

    Stack push(int value) {
        if (size == arr.length) {
            int capacity = size * 2;
            int[] newArr = new int[capacity];
            System.arraycopy(arr, 0, newArr, 0, size);
            newArr[size] = value;
            arr = newArr;
        }
        else arr[size] = value;
        size += 1;
        return this;
    }

    int size() { return size; }

    int top() throws EmptyStackException {
        if (size == 0) throw new EmptyStackException();
        return arr[size - 1];
    }

    // lazy deletion!
    Stack pop() {
        if (size == 0) return null;
        size -= 1;
        return this;
    }

    void print() {
        if (this.size() == 0)  {
            System.out.println("[]");
            return;
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) sb.append(arr[i]).append(", ");
        sb.setLength(sb.length() - 2);
        sb.append("]");
        System.out.println(sb);
    }
}
