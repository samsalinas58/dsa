package arrays;

import main.arrays.Stack;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

class StackTest {

    @Test
    void testStackIsEmpty() {
        Stack stk = new Stack();
        boolean actual = stk.isEmpty();
        boolean expected = true;

        assert(actual == expected);
    }

    @Test
    void testStackIsEmptyOnNonEmptyStack() {
        Stack stk = new Stack(1);
        boolean actual = stk.isEmpty();
        boolean expected = false;

        assert(actual == expected);
    }

    @Test
    void testStackPopEmptyStack() {
        Stack stk = new Stack();
        try {
            stk.pop();
        }
        catch (EmptyStackException e) {
            assert true;
            return;
        }
        assert false;
    }

    @Test
    void testStackPopNonEmptyStack() {
        Stack stk = new Stack(1);
        try {
            stk.pop();
        }
        catch (EmptyStackException e) {
            assert false;
            return;
        }
        assert true;
    }

    @Test
    void testStackTopEmptyStack() {
        Stack stk = new Stack();
        try {
            stk.top();
        } catch (EmptyStackException e) {
            assert(true);
            return;
        }
        assert(false);
    }

    @Test
    void testStackTopFilledStack() {
        int[] l = {1,2,3};
        Stack stk = new Stack();
        for (int i: l) stk.push(i);
        boolean actual = stk.top() == 3;
        boolean expected = true;

        assert(actual == expected);
    }

    @Test
    void testStackSize() {
        int[] l = {1,2,3};
        Stack stk = new Stack();
        for (int i: l) stk.push(i);
        boolean actual = stk.size() == 3;
        boolean expected = true;

        assert(actual == expected);
    }

    @Test
    void testStackPush() {
        int[] l = {1,2,3,4,5,6,7,8};
        Stack stk = new Stack();
        for (int i: l) stk.push(i);
    }

}