package main.arrays;

public class Queue {
    int[] arr = new int[1];
    int front = -1;
    int back = -1;

    public Queue() {
        arr = new int[1];
    }

    public Queue(int value) {
        front = 0;
        back = 0;
        arr[0] = value;
    }

    public Queue(int[] arr) {
        for (int i: arr) this.push(i);
    }

    public int size() {
        if (front == -1) return 0;
        if (back < front) return (arr.length - front) + (back + 1);
        return (back - front) + 1;
    }

    public Integer front() {
        if (front == -1) return null;
        return arr[front];
    }

    public Queue push(int value) {
        if (front == -1) {
            arr[0] = value;
            front = 0;
            back = 0;
            return this;
        }
        back += 1;
        if (back == arr.length) back = 0;
        if (back == front) {
            int capacity = arr.length * 2;
            int[] newArr = new int[capacity];
            for (int i = front, ct = 0; ct < arr.length; ct++, i++) {
                if (i == arr.length) i = 0;
                newArr[ct] = arr[i];
            }
            back = arr.length;
            front = 0;
            arr = newArr;
        }
        arr[back] = value;
        return this;
    }

    public Queue pop() {
        if (front == -1) return this;
        if (front == back) {
            front = -1;
            back = -1;
            return this;
        }
        front += 1;
        if (front == arr.length) front = 0;
        return this;
    }

    public void print() {
        int size = this.size();
        if (this.size() == 0) {
            System.out.println("[]");
            return;
        }

        StringBuilder sb = new StringBuilder("[");
        for (int i = front, ct = 0; ct < size; i++, ct++) {
            if (i == arr.length) i = 0;
            sb.append(arr[i]).append(", ");
        }
        sb.setLength(sb.length() - 2);
        sb.append("]");
        System.out.println(sb);
    }

    public boolean empty() {
        return front == -1;
    }

    public boolean equals(Queue p) {
        if (this.size() != p.size()) return false;

        int sz = this.size();
        for (int i = this.front, j = p.front, ct = 0;
             ct < sz;
             i++, j++, ct++) {
            if (i == this.arr.length) i = 0;
            if (j == p.arr.length) j = 0;
            if (this.arr[i] != p.arr[j]) return false;
        }
        return true;
    }
}
