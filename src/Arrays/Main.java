package Arrays;

import Arrays.Stack;

class Main {
    public static void main(String[] args) {
        Stack s = new Stack(1);
        s.print();
        s.push(2).push(3).push(4);
        s.print();

        System.out.println(s.top());
        s.pop().pop().pop().pop();
        s.print();

        s.pop(); // EmptyStackException!
    }
}