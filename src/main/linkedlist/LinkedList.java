package main.linkedlist;

public class LinkedList {
    private static class Node {
        int value;
        Node next;

        Node(int val) {
            this.value = val;
            this.next = null;
        }

        Node(int val, Node next) {
            this.value = val;
            this.next = next;
        }

        void print() {
            StringBuilder sb = new StringBuilder("Node(");
            sb.append("value: ").append(this.value).append(", ");
            if (next != null)
                sb.append("next: ").append(this.next.value).append(")");
            else
                sb.append("next: ").append((Object) null).append(")");
            System.out.println(sb.toString());
        }
    }

    Node root;
    int size = 0;

    public LinkedList() {
        root = null;
    }

    public LinkedList(int value) {
        root = new Node(value);
        size = 1;
    }

    public LinkedList(int[] arr) {
        if (arr.length == 0) {
            root = null;
            return;
        }
        root = new Node(arr[0]);
        Node head = root;
        for (int i = 1; i < arr.length; i++) {
            head.next = new Node(arr[i]);
            head = head.next;
        }
        size = arr.length;
    }

    public int size() {
        return size;
    }

    public LinkedList insert(int value, int index) {
        if (index > this.size || index < 0) return this;
        if (index == 0) {
            this.root = new Node(value, root);
            size += 1;
            return this;
        }

        Node head = this.root;
        int curr = 0;
        while (curr != index - 1) {
            curr += 1;
            head = head.next;
        }
        head.next = new Node(value, head.next);
        size += 1;
        return this;
    }

    public LinkedList append(int value) {
        if (root == null)  {
            root = new Node(value);
            size = 1;
            return this;
        }
        Node head = root;
        while (head.next != null) head = head.next;
        head.next = new Node(value);
        size += 1;
        return this;
    }

    public LinkedList appendList(LinkedList l) {
        if (root == null) {
            root = l.root;
            size = l.size;
            return this;
        }
        if (l.root == null) return this;
        Node head = root;
        while (head.next != null) head = head.next;
        head.next = l.root;
        size += l.size;
        return this;
    }

    public LinkedList prependList(LinkedList l) {
        if (root == null) {
            size = l.size;
            root = l.root;
            return this;
        }
        if (l.root == null) return this;

        Node head = l.root;
        while (head.next != null) head = head.next;

        head.next = root;
        root = l.root;
        size += l.size;
        return this;
    }

    public LinkedList pop_back() {
        if (root == null) return this;

        Node head = root;
        Node prev = null;
        while (head.next != null) {
            prev = head;
            head = head.next;
        }
        if (prev != null) prev.next = null;
        else root = null;
        size -= 1;
        return this;
    }

    public LinkedList pop_front() {
        if (root == null) return this;
        root = root.next;
        size -= 1;
        return this;
    }

    public Integer front() {
        if (root == null) return null;
        return root.value;
    }

    public Integer back() {
        if (root == null) return null;
        Node head = root;
        while (head.next != null) head = head.next;
        return head.value;
    }

    public LinkedList delete(int index) {
        if (root == null) return this;
        if (index >= size || index < 0) return this;
        if (index == 0) return this.pop_front();
        if (index == size - 1) return this.pop_back();

        Node head = root;
        Node prev = root;
        for (int i = 0; i < index; i++) {
            prev = head;
            head = head.next;
        }
        prev.next = head.next;
        size -= 1;
        return this;
    }

    public LinkedList replace(int index, int value) {
        if (root == null) return this;

        // Ideally we'd throw an error here but for the simplicity of this project
        // this is just returning the base list.
        if (index >= size || index < 0) return this;

        Node head = root;
        for (int i = 0; i < index; i++) head = head.next;
        head.value = value;

        return this;
    }

    public boolean equals(LinkedList l) {
        if (l.size != this.size) return false;
        if (root == null && l.root == null) return true;
        if (root == null || l.root == null) return false;

        Node head1 = root, head2 = l.root;
        while (head1 != null) {
            if (head1.value != head2.value) return false;
            head1 = head1.next;
            head2 = head2.next;
        }

        return true;
    }

    public Integer get(int idx) {
        if (idx >= size || root == null) return null;
        Node head = root;
        for (int i = 0; i != idx; i++) head = head.next;
        return head.value;
    }

    public boolean isEmpty() { return size == 0; }

    public void print() {
        StringBuilder sb = new StringBuilder("LinkedList(list: ");
        if (root == null) {
            sb.append("[], ").append("size: ").append(size).append(")");
            System.out.println(sb);
            return;
        }
        sb.append("[");
        Node head = root;
        while (head != null){
            String prefix = ", ";
            sb.append(head.value).append(prefix);
            head = head.next;
        }
        sb.setLength(sb.length() - 2);
        sb.append(']').append(", size: ").append(size).append(')');

        System.out.println(sb);
    }

}
