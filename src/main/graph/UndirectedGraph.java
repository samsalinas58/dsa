package main.graph;

import main.graph.exceptions.*;
import java.util.Vector;

public class UndirectedGraph extends Graph {
    public void printDFS(int start) {

    }

    public void printBFS(int start) {

    }

    public Vector<Vector<Edge>> bfs(int start) {

        return null;
    }

    public UndirectedGraph insert(int x) throws NodeExistsException {
        return this;
    }

    public UndirectedGraph remove(int x) throws NodeNotFoundException {
        return this;
    }

    public UndirectedGraph addEdge(int x, int y, int weight) throws NodeNotFoundException, EdgeExistsException {
        return this;
    }

    public UndirectedGraph removeEdge(int x, int y) throws EdgeNotFoundException, NodeNotFoundException {
        return this;
    }

    public int getWeight(int x, int y) throws EdgeNotFoundException {
        return 0;
    }

}
