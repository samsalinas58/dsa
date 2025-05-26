package Arrays;

import Arrays.Stack;
import Arrays.Queue;

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

        Queue q = new Queue(1);
        q.print();

        q.push(2).push(3).push(4);
        q.pop();
        q.push(1);
        q.push(5);
        q.pop();
        q.push(6);
        q.print();
        q.pop().pop().pop().pop().pop();
        q.print();

    }
}