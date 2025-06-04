package arrays;

import main.arrays.Queue;
import org.junit.jupiter.api.Test;

class QueueTest {

    @Test
    void testInsert() {
        int[] l = {1,2,3,4,5,6,7,8,9,10,11,12};
        Queue q = new Queue();
        for (int i: l) q.push(i);

        for (int i: l) {
            assert i == q.front();
            q.pop();
        }
        assert q.empty();
    }

    @Test
    void testSizeEmpty() {
        Queue q = new Queue();
        int actual = q.size();
        int expected = 0;

        assert(actual == expected);
    }

    @Test
    void testSizeNotEmpty() {
        int[] l = {1,2,3,4,5,6,7,8,9,10};
        Queue q = new Queue(l);
        int actual = q.size();
        int expected = 10;

        assert(actual == expected);
    }

    @Test
    void testPopEmptyQueue(){
        Queue q = new Queue();
        Queue p = new Queue();
        q.pop();

        assert(p.equals(q));
    }

    @Test
    void testPopFilledQueue() {
        int[] l = {1,2,3,4,5,6,7,8,9,10};
        int[] k = {4,5,6,7,8,9,10};
        Queue q = new Queue(l);
        Queue r = new Queue(k);

        q.pop().pop().pop();
        boolean actual = q.equals(r);
        boolean expected = true;

        assert(actual == expected);
    }

    @Test
    void testFrontEmptyQueue() {
        Queue q = new Queue();
        Integer actual = q.front();
        Integer expected = null;

        assert (actual == expected);
    }

    @Test
    void testEqualsUnevenSize() {
        int[] l = {1,2,3};
        int[] m = {1,2,3,4};
        Queue q = new Queue(l);
        Queue r = new Queue(m);
        boolean actual = q.equals(r);
        boolean expected = false;

        assert (actual == expected);
    }

    @Test
    void testEqualsSameQueueAfterPoppingAndPushing() {
        int[] l = {1,2,3,4,5,6};
        Queue q = new Queue(l);
        q.pop().pop().pop().push(7).push(8).push(9); // {4,5,6,7}
        int[] m = {8,9,10,11,4,5,6};
        Queue r = new Queue(m);
        r.pop().pop().pop().pop().push(7).push(8).push(9); // {4,5,6,7}
        boolean actual = q.equals(r);
        boolean expected = true;

        assert(actual == expected);
    }

    @Test
    void testEqualsNotEqual() {
        int[] l = {1,2,3};
        int[] m = {1,2,4};
        Queue q = new Queue(l);
        Queue r = new Queue(m);
        boolean actual = q.equals(r);
        boolean expected = false;

        assert(actual == expected);
    }

    @Test
    void testEmptyFilled() {
        Queue q = new Queue(1);
        boolean actual = q.empty();
        boolean expected = false;

        assert(actual == expected);
    }

}