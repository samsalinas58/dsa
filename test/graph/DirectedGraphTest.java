package graph;

import main.graph.DirectedGraph;
import main.graph.exceptions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertThrows;

class DirectedGraphTest {
    @Test
    void testInsertNewNode() {
        DirectedGraph g = new DirectedGraph();
        try {
            g.insert(4);
        } catch (NodeExistsException n) { assert(false); }
        assert(true);
    }

    @Test
    void testInsertNodeExists() {
        DirectedGraph g = new DirectedGraph();
        try {
            g.insert(4);
            assertThrows(NodeExistsException.class, () -> g.insert(4));
        } catch (NodeExistsException _) { assert(false); }
    }

    @Test
    void testaddEdgeDoesNotExist() {

    }

    @Test
    void testAddEdgeAlreadyExists() {

    }

}