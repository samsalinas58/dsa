package main.linkedlist;

public class Main {
    public static void main(String[] args) {
        LinkedList a = new LinkedList(2);
        a.append(3).append(5).append(4);
        a.print();
        a.insert(6, 2);
        a.print();
        a.insert(10, 5);
        a.print();
        a.insert(-1, 0);
        a.print();
        a.delete(2);
        a.print();
        a.delete(3);
        a.print();
        a.delete(a.size() - 1);
        a.print();
        a.delete(0);
        a.print();
        a.pop_back().pop_back().pop_back();
        a.print();
        a.pop_front().pop_front();
        a.print();
        a.replace(0, 1);
        a.print();
        a.replace(a.size(), 2);
        a.print();

        System.out.println();
        LinkedList b = new LinkedList();
        b.print();
        b.pop_back().pop_front();
        b.print();
        b.delete(-1);
        b.print();
        b.delete(100);
        b.print();
        b.delete(0);
        b.print();
        b.insert(0, 5);
        b.print();
        b.insert(10, -2);
        b.print();
        System.out.println(b.front());
        System.out.println(b.back());

        System.out.println();
        LinkedList c = new LinkedList(new int[]{1, 2, 3});
        c.print();
        LinkedList d = new LinkedList(new int[]{4,5,6});
        d.print();
        c.appendList(d);
        c.print();

        System.out.println();
        LinkedList e = new LinkedList(new int[]{1, 2, 3});
        e.print();
        LinkedList f = new LinkedList(new int[]{4,5,6});
        f.print();
        e.prependList(f);
        e.print();
    }
}