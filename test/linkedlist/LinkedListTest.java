package linkedlist;

import main.linkedlist.LinkedList;
import org.junit.jupiter.api.Test;

class LinkedListTest {

    @Test
    void testIsEmptyOnEmptyList() {
        LinkedList l = new LinkedList();
        boolean actual = l.isEmpty();
        boolean expected = true;

        assert(actual == expected);
    }

    @Test
    void testIsEmptyOnNonEmptyList() {
        LinkedList l = new LinkedList(1);
        boolean actual = l.isEmpty();
        boolean expected = false;

        assert(actual == expected);
    }

    @Test
    void testGetOnEmptyList() {
        LinkedList l = new LinkedList();
        Integer actual = l.get(0);
        Integer expected = null;

        assert(actual == expected);
    }

    @Test
    void testGetOnFilledListValidIndex() {
        int[] arr = {1,2,3};
        LinkedList l = new LinkedList();
        for (int i: arr) l.append(i);
        Integer actual = l.get(1);
        Integer expected = 2;

        assert(actual.equals(expected));
    }

    @Test
    void testGetOnFilledListInvalidIndex() {
        int[] arr = {1,2,3};
        LinkedList l = new LinkedList();
        for (int i: arr) l.append(i);
        Integer actual = l.get(3);
        Integer expected = null;

        assert(actual == expected);
    }

    @Test
    void testAppend() {
        int[] arr = {1,2,3};
        LinkedList l = new LinkedList();

        for (int i: arr) l.append(i);

        for (int i = 0; i < l.size(); i++) {
            assert (l.get(i) == arr[i]);
        }
    }

    @Test
    void testEqualsBothEmpty() {
        LinkedList l = new LinkedList();
        LinkedList l2 = new LinkedList();
        boolean actual = l.equals(l2);
        boolean expected = true;

        assert(actual == expected);
    }

    @Test
    void testEqualsSameLists() {
        int[] arr = {1,2,3};
        LinkedList l = new LinkedList();
        LinkedList l2 = new LinkedList();
        for (int i: arr) {
            l.append(i);
            l2.append(i);
        }

        boolean actual = l.equals(l2) == l2.equals(l);
        boolean expected = true;

        assert(actual == expected);
    }

    @Test
    void testEqualsDifferentLists() {
        int[] arr = {1,2,3};
        LinkedList l = new LinkedList();
        LinkedList l2 = new LinkedList();
        for (int i: arr) l.append(i);

        boolean actual = l.equals(l2);
        boolean expected = false;

        assert(actual == expected);
    }

    @Test
    void testInsertEmptyListInvalidIndex() {
        int index = 1;
        LinkedList l = new LinkedList();
        l.insert(1, index);
        LinkedList q = new LinkedList();
        boolean actual = l.equals(q);
        boolean expected = true;

        assert(actual == expected);
    }

    @Test
    void testInsertNewRoot() {
        int[] arr = {0,1};
        int index = 0;
        LinkedList l = new LinkedList(1);
        l.insert(0, index);

        for (int i = 0; i < l.size(); i++) assert l.get(i) == arr[i];
    }

    @Test
    void testInsertMiddleOfList() {
        int[] arr = {0,2};
        int index = 1;
        LinkedList l = new LinkedList();
        for (int i: arr) l.append(i);
        l.insert(1,index);

        int[] expected = {0,1,2};
        for (int i = 0; i < expected.length; i++) {
            assert l.get(i) == expected[i];
        }
    }

    @Test
    void testInsertEndOfList() {
        int[] arr = {0,1,2,3};
        int index = arr.length;
        int value = arr.length;
        LinkedList l = new LinkedList();
        for (int i: arr) l.append(i);
        l.insert(value, index);

        int[] expected = {0,1,2,3,4};
        for (int i = 0; i < expected.length; i++){
            assert l.get(i) == expected[i];
        }
    }

    @Test
    void testInsertNegativeIndex() {
        LinkedList l = new LinkedList();
        int index = -1;
        l.insert(0, index);
        boolean actual = l.equals(l);
        boolean expected = true;

        assert(actual == expected);
    }

    @Test
    void testLinkedListArrayConstructorNonEmptyArray() {
        int[] arr = {1,2,3};
        LinkedList l = new LinkedList(arr);

        for (int i = 0; i < arr.length; i++) {
            assert l.get(i) == arr[i];
        }
        assert l.size() == arr.length;
    }

    @Test
    void testLinkedListArrayConstructorEmptyArray() {
        int[] arr = {};
        LinkedList l = new LinkedList(arr);

        assert l.size() == arr.length;
        for (int i = 0; i < arr.length; i++) {
            assert l.get(i) == arr[i];
        }
    }

    @Test
    void testAppendListAppendingNonEmptyListToNonEmptyList() {
        int[] arr1 = {1,2,3};
        int[] arr2 = {4,5,6};
        LinkedList l = new LinkedList(arr1);
        LinkedList l2 = new LinkedList(arr2);
        l.appendList(l2);

        int[] expected = {1,2,3,4,5,6};
        assert(expected.length == l.size());
        for (int i = 0; i < expected.length; i++) {
            assert(l.get(i) == expected[i]);
        }
    }

    @Test
    void testAppendListAppendingEmptyListToNonEmptyList() {
        int[] arr1 = {1,2,3};
        int[] arr2 = {};
        LinkedList l = new LinkedList(arr1);
        LinkedList l2 = new LinkedList(arr2);
        l.appendList(l2);

        int[] expected = {1,2,3};
        assert(expected.length == l.size());
        for (int i = 0; i < expected.length; i++) {
            assert(l.get(i) == expected[i]);
        }
    }

    @Test
    void testAppendListAppendingNonEmptyListToEmptyList() {
        int[] arr1 = {};
        int[] arr2 = {1,2,3};
        LinkedList l = new LinkedList(arr1);
        LinkedList l2 = new LinkedList(arr2);
        l.appendList(l2);

        int[] expected = {1,2,3};
        assert(expected.length == l.size());
        for (int i = 0; i < expected.length; i++) {
            assert(l.get(i) == expected[i]);
        }
    }

    @Test
    void testAppendListBothEmpty() {
        int[] arr1 = {};
        int[] arr2 = {};
        LinkedList l = new LinkedList(arr1);
        LinkedList l2 = new LinkedList(arr2);
        l.appendList(l2);

        int[] expected = {};
        assert(expected.length == l.size());
        for (int i = 0; i < expected.length; i++) {
            assert(l.get(i) == expected[i]);
        }
    }

    @Test
    void testPrependListBothEmpty() {
        int[] arr1 = {};
        int[] arr2 = {};
        LinkedList l = new LinkedList(arr1);
        LinkedList l2 = new LinkedList(arr2);
        l.prependList(l2);

        int[] expected = {};
        assert(expected.length == l.size());
        for (int i = 0; i < expected.length; i++) {
            assert(l.get(i) == expected[i]);
        }

    }

    @Test
    void testPrependListPrependingNonEmptyListToEmptyList() {
        int[] arr1 = {1,2,3};
        int[] arr2 = {};
        LinkedList l = new LinkedList(arr1);
        LinkedList l2 = new LinkedList(arr2);
        l2.prependList(l);

        int[] expected = {1,2,3};
        assert(expected.length == l2.size());
        for (int i = 0; i < expected.length; i++) {
            assert(l2.get(i) == expected[i]);
        }
    }

    @Test
    void testPrependListPrependingEmptyListToNonEmptyList() {
        int[] arr1 = {1,2,3};
        int[] arr2 = {};
        LinkedList l = new LinkedList(arr1);
        LinkedList l2 = new LinkedList(arr2);
        l.prependList(l2);

        int[] expected = {1,2,3};
        assert(expected.length == l.size());
        for (int i = 0; i < expected.length; i++) {
            assert(l.get(i) == expected[i]);
        }
    }

    @Test
    void testPrependListBothNonEmpty() {
        int[] arr1 = {1,2,3};
        int[] arr2 = {4,5,6};
        LinkedList l = new LinkedList(arr1);
        LinkedList l2 = new LinkedList(arr2);
        l2.prependList(l);

        int[] expected = {1,2,3,4,5,6};
        assert(expected.length == l2.size());
        for (int i = 0; i < expected.length; i++) {
            assert(l2.get(i) == expected[i]);
        }
    }

    @Test
    void testPopBackEmptyList() {
        LinkedList l = new LinkedList();
        LinkedList l2 = new LinkedList();
        l.pop_back();
        boolean actual = l.equals(l2);
        boolean expected = true;

        assert (actual == expected);
    }

    @Test
    void testPopBackNonEmptyList() {
        int[] arr = {1,2,3};
        LinkedList l = new LinkedList(arr);
        l.pop_back();
        int[] expected = {1,2};
        assert expected.length == l.size();
        for (int i = 0; i < expected.length; i++) {
            assert expected[i] == l.get(i);
        }
    }

    @Test
    void testPopBackOneElement() {
        LinkedList l = new LinkedList(1);
        l.pop_back();

        assert(l.isEmpty());
        assert(l.front() == null);
    }

    @Test
    void testPopFrontEmpty() {
        LinkedList l = new LinkedList();
        l.pop_front();
        assert(l.isEmpty());
    }

    @Test
    void testPopFrontOneElement() {
        LinkedList l = new LinkedList(1);
        l.pop_front();

        assert(l.isEmpty());
        assert(l.front() == null);
    }

    @Test
    void testPopFrontManyElements() {
        int[] arr = {1,2,3};
        LinkedList l = new LinkedList(arr);
        l.pop_front();
        int actual = l.front();
        int expected = 2;

        assert(actual == expected);
    }

    @Test
    void testDeleteEmptyList() {
        LinkedList l = new LinkedList();
        LinkedList q = new LinkedList();

        int index = 0;
        l.delete(index);

        boolean actual = l.equals(q);
        boolean expected = true;

        assert (actual == expected);
    }

    @Test
    void testDeleteNonEmptyInvalidIndices() {
        LinkedList l = new LinkedList(1);
        LinkedList q = new LinkedList(1);
        l.delete(1);
        l.delete(-1);
        boolean actual = l.equals(q);
        boolean expected = true;

        assert(actual == expected);
    }

    @Test
    void testDeleteNonEmptyFirstElement() {
        int[] arr = {1,2,3};
        LinkedList l = new LinkedList(arr);
        l.delete(0);

        int[] expected = {2,3};
        assert expected.length == l.size();
        for (int i = 0; i < expected.length; i++) {
            assert expected[i] == l.get(i);
        }
    }

    @Test
    void testDeleteNonEmptyLastElement() {
        int[] arr = {1,2,3};
        LinkedList l = new LinkedList(arr);
        l.delete(l.size() - 1);

        int[] expected = {1,2};
        assert expected.length == l.size();
        for (int i = 0; i < expected.length; i++) {
            assert expected[i] == l.get(i);
        }
    }

    @Test
    void testDeleteNonEmptyMiddleElement() {
        int[] arr = {1,2,3,4,5};
        LinkedList l = new LinkedList(arr);
        l.delete(3); // delete the value 4

        int[] expected = {1,2,3,5};
        assert expected.length == l.size();
        for (int i = 0; i < expected.length; i++) {
            assert expected[i] == l.get(i);
        }
    }

    @Test
    void testReplaceEmptyList() {
        LinkedList l = new LinkedList();
        LinkedList q = new LinkedList();
        l.replace(3, 4);

        boolean actual = l.equals(q);
        boolean expected = true;

        assert (actual == expected);
    }

    @Test
    void testReplaceNonEmptyListInvalidIndices() {
        int[] arr = {1,2,3};
        LinkedList l = new LinkedList(arr);
        LinkedList q = new LinkedList(arr);
        l.replace(-1, 4);
        l.replace(3, 4);

        boolean actual = l.equals(q);
        boolean expected = true;

        assert(actual == expected);
    }

    @Test
    void testReplaceNonEmptyListValidIndices() {
        int[] arr = {1,2,3};
        int[] expected_arr = {1,4,3};
        LinkedList l = new LinkedList(arr);
        LinkedList e = new LinkedList(expected_arr);

        l.replace(1,4);

        boolean actual = l.equals(e);
        boolean expected = true;
        assert(actual == expected);
    }
}