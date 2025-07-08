package main.graph;

import main.graph.exceptions.*;

import java.util.*;

public class DirectedGraph extends Graph {

    private final HashMap<Integer, HashSet<Integer>> edges; // <node, set<nodes>>
    private final HashMap<Integer, Integer> weights; // <hashCode(edge), weight>

    public DirectedGraph() {
        this.edges = new HashMap<>();
        this.weights = new HashMap<>();
    }

    public void dfs(int start) throws NodeNotFoundException {
        if (!edges.containsKey(start)) throw new NodeNotFoundException("Node not found in graph when attempting depth-first search.");

        HashSet<Integer> visited = new HashSet<>();
        Stack<Integer> stk = new Stack<>();

        for (int i: edges.get(start)) stk.push(i);
        visited.add(start);

        System.out.println("Starting at node: [" + start + "]");
        while (!stk.empty()) {
            int x = stk.pop();
            if (visited.contains(x)) continue;
            visited.add(x);
            for (int y: edges.get(x)) if (!visited.contains(y)) stk.push(y);
            System.out.println(x);
        }
    }

    // Finds the shortest path starting from vertex 'start' to vertex 'end'
    public Integer dijkstra(int start, int end) throws NodeNotFoundException {
        if (!edges.containsKey(start) || !edges.containsKey(end))
            throw new NodeNotFoundException("Starting position or ending position does not exist in graph.");

        if (start == end) return 0;

        // initialization phase: created unvisited set and set all vertexes to INFINITY (or in this case, INT_MAX)
        // except for starting node
        HashSet<Integer> unvisited = new HashSet<>();
        HashMap<Integer, Integer> distances = new HashMap<>();
        for (int i: edges.keySet()) {
            distances.put(i, Integer.MAX_VALUE);
            unvisited.add(i);
        }
        distances.put(start, 0);

        int current = start;
        while (unvisited.contains(end)) {
            if (!unvisited.contains(current)) continue;
            unvisited.remove(current);
            for (int i: edges.get(current)) {
                Edge e = new Edge(current, i);
                int weight = weights.get(e.hashCode());
                int min_distance = Math.min(distances.get(i), distances.get(current) + weight);
                distances.put(i, min_distance);
            }

            int min = Integer.MAX_VALUE;
            for (Map.Entry<Integer, Integer> i: distances.entrySet()) {
                if (!unvisited.contains(i.getKey()) || i.getKey() == start) continue;
                if (i.getValue() <= min) {
                    current = i.getKey();
                    min = i.getValue();
                }
            }
            if (min == Integer.MAX_VALUE) break;
        }
        return distances.get(end);
    }

    // returns the breadth first search result in array form. each index of the nested vector corresponds to
    // its depth in the graph relative to the start
    public Vector<Vector<Edge>> bfs(int start) throws NodeNotFoundException {
        if (!edges.containsKey(start)) throw new NodeNotFoundException("Node not found in Graph.");
        Vector<Vector<Edge>> res = new Vector<>();
        ArrayDeque<Vector<Integer>> q = new ArrayDeque<>();
        HashSet<Integer> visited = new HashSet<>();

        Vector<Integer> v = new Vector<>();
        v.add(start);
        q.add(v);

        while (!q.isEmpty()) {
            Vector<Integer> to_visit = q.remove();
            Vector<Integer> q_vec = new Vector<>();
            Vector<Edge> _edges = new Vector<>();

            for (int x: to_visit) {
                visited.add(x);
                for (int y: edges.get(x)) {
                    if (!visited.contains(y) && !to_visit.contains(y)) q_vec.add(y);
                    Edge e = new Edge(x,y);
                    _edges.add(e);
                }
            }

            if (!q_vec.isEmpty()) q.add(q_vec);
            if (!_edges.isEmpty()) res.add(_edges);
        }

        return res;
    }

    public void printBFS(int start) throws NodeNotFoundException {
        if (edges.isEmpty()) return;
        if (!edges.containsKey(start)) throw new NodeNotFoundException("Node not found in Graph.");

        StringBuilder sb = new StringBuilder("Paths starting from [").append(start).append("]:").append("\n");
        Vector<Vector<Edge>> bfs = bfs(start);
        int depth = 0;
        for (Vector<Edge> v: bfs) {
            sb.append("\tDepth ").append(depth).append(":\n");
            for (Edge e: v) {
                int weight = weights.get(e.hashCode());
                sb.append("\t\tEdge([").append(e.getX()).append(" --> ").append(e.getY()).append("], ")
                        .append("weight: ").append(weight).append(")").append("\n");
            }
            depth += 1;
        }

        System.out.println(sb);
    }

    public DirectedGraph insert(int x) throws NodeExistsException {
        if (edges.containsKey(x)) throw new NodeExistsException("Attempted to insert a node that already exists in Graph.");
        HashSet<Integer> e = new HashSet<>();
        edges.put(x, e);
        return this;
    }

    public DirectedGraph remove(int x) throws NodeNotFoundException {
        if (!edges.containsKey(x)) throw new NodeNotFoundException("Attempted to remove node that does not exist in graph.");
        // remove every edge that points to this node first, and then
        // finally remove all the edges this node points to and their weights
        for (Integer n: edges.keySet()) {
            if (x == n) continue;
            HashSet<Integer> nPointsTo = edges.get(n);
            nPointsTo.remove(x);

            Edge e = new Edge(n, x);
            int key = e.hashCode();
            weights.remove(key);
        }
        edges.remove(x);

        return this;
    }

    public DirectedGraph addEdge(int x, int y, int weight) throws EdgeExistsException, NodeNotFoundException {
        if (!edges.containsKey(x) || !edges.containsKey(y)) throw new NodeNotFoundException("Node not found in Graph. " +
                "Attempted to add an edge with a node that does not exist.");

        HashSet<Integer> x_edges = edges.get(x);
        if (x_edges.contains(y)) throw new EdgeExistsException("Edge already exists in graph.");

        x_edges.add(y);

        Edge e = new Edge(x,y);
        int edgeID = e.hashCode();
        weights.put(edgeID, weight);

        return this;
    }

    public DirectedGraph removeEdge(int x, int y) throws EdgeNotFoundException {
        String m = "Edge does not exist in graph.";
        if (!edges.containsKey(x) || !edges.containsKey(y)) throw new EdgeNotFoundException(m);
        HashSet<Integer> x_edges = edges.get(x);

        if (!x_edges.contains(y)) throw new EdgeNotFoundException(m);

        x_edges.remove(y);
        Edge e = new Edge(x,y);
        int key = e.hashCode();
        weights.remove(key);

        return this;
    }

    public int getWeight(int x, int y) throws EdgeNotFoundException {
        Edge e = new Edge(x,y);
        int edgeID = e.hashCode();
        if (weights.get(edgeID) == null) throw new EdgeNotFoundException("Edge not found in Graph.");
        return weights.get(edgeID);
    }

    public int getWeight(Edge e) throws EdgeNotFoundException {
        if (!weights.containsKey(e.getX())) throw new EdgeNotFoundException("Edge not found in graph.");
        return weights.get(e.hashCode());
    }

    public void printNodeEdges(int x) throws NodeNotFoundException {
        if (!edges.containsKey(x)) throw new NodeNotFoundException("Attempted to print node that does not exist.");

        StringBuilder sb = new StringBuilder("Node ").append("[").append(x).append("]").append(": (").append("\n");
        HashSet<Integer> pointsTo = edges.get(x);
        for (Integer y: pointsTo){
            sb.append("\t").append("Edge(").append(x).append(" --> ").append(y);
            Edge e = new Edge(x,y);
            int weight = weights.get(e.hashCode());
            sb.append(", ").append("weight: ").append(weight).append(")").append("\n");
        }
        sb.append(")");
        System.out.println(sb);
    }

    public void print() {
        if (edges.isEmpty()) return;

        StringBuilder sb = new StringBuilder("Graph (");
        for (int x: edges.keySet()) {
            sb.append("\n");
            sb.append("\t").append("Node ").append("[").append(x).append("]").append(": (").append("\n");
            HashSet<Integer> pointsTo = edges.get(x);
            for (Integer y: pointsTo){
                sb.append("\t\t").append("Edge(").append("[").append(x).append("]")
                        .append(" --> ").append("[").append(y).append("]");
                Edge e = new Edge(x,y);
                int weight = weights.get(e.hashCode());
                sb.append(", ").append("weight: ").append(weight).append(")").append("\n");
            }
            sb.append("\t),");
        }
        sb.setLength(sb.length() - 1); // remove the comma!
        sb.append("\n)");

        System.out.println(sb);
    }

}
