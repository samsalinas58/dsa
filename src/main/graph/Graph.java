package main.graph;

import main.graph.exceptions.*;
import java.util.Vector;

abstract class Graph {
    public static class Edge {
        private final int x;
        private final int y;

        public Edge(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() { return x; }

        public int getY() { return y; }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Edge e)) return false;
            return e.x == x && e.y == y;
        }

        @Override
        public int hashCode() {
            return (x << 16) + y;
        }
    }

    abstract public void dfs(int start) throws NodeNotFoundException;
    abstract public void printBFS(int start) throws NodeNotFoundException;
    abstract public Vector<Vector<Edge>> bfs(int start) throws NodeNotFoundException;
    abstract public Graph insert(int x) throws NodeExistsException;
    abstract public Graph remove(int x) throws NodeNotFoundException;
    abstract public Graph addEdge(int x, int y, int weight) throws EdgeExistsException, NodeNotFoundException;
    abstract public Graph removeEdge(int x, int y) throws EdgeNotFoundException, NodeNotFoundException;
    abstract public int getWeight(int x, int y) throws EdgeNotFoundException;
}

