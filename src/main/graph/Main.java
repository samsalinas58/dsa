package main.graph;

import main.graph.exceptions.*;

class Main {
    public static void main(String[] args) {
        DirectedGraph g = new DirectedGraph();
        try {
            g.insert(4);
            g.insert(5);
            g.insert(1);
            g.insert(2);
            g.insert(3);
            g.insert(6);
            g.insert(7);
            g.addEdge(4,1,1);
            g.addEdge(4,2,2);
            g.addEdge(4,3,3);
            g.addEdge(4,5,10);
            g.addEdge(1,3,9);
            g.addEdge(5, 4, 5);
            g.addEdge(3,4,9);
            g.addEdge(3,6,15);
            g.addEdge(5,1,16);
            g.addEdge(1,7,18);
            g.addEdge(7,2, 20);
            g.addEdge(7,4, 21);
            //g.printBFS(3);
            //g.dfs(4);
            int min = g.dijkstra(4,2); // should return 2
            System.out.println(min);
            min = g.dijkstra(4,7); // returns
            System.out.println(min);

        } catch (NodeExistsException | EdgeExistsException | NodeNotFoundException _) { }
    }
}