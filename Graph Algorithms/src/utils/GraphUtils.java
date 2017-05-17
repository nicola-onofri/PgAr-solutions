package utils;

import data_structures.Edge;
import data_structures.Graph;
import data_structures.Node;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Created by nicola on 13/05/17.
 */
public class GraphUtils {
    /**
     * Implements Kruskal's algorithm logic
     * Note that the returned graph represents the shortest spanning tree
     * extracted from the graph that is passed to the function as parameter.
     * The first and the last node of the generated graph are the same as the
     * given one, but in this case they won't have the same meaning.
     *
     * @param g the graph that has to be processed to extract the SST
     * @return SST extracted from the given graph
     */
    public static Graph kruskal(Graph g) {
        //We only need n-1 links to generate the SST,
        //where n is the number of nodes of the given graph
        int k_bound = g.getNodes().size() - 1;
        int edge_index = 0;
        ArrayList<Edge> k_edges = new ArrayList<>();
        //HashMap<Node, ArrayList<Node>> adjacency_matrix = getAdjacencyMatrix(g);

        //Method reference in Java 8
        ArrayList<Edge> sorted_edges = g.getEdges().stream()
                .sorted(Comparator.comparingInt(Edge::getWeight))
                .collect(Collectors.toCollection(ArrayList::new));

        //Start selecting edges from the graph
        while (k_edges.size() != k_bound) {
            k_edges.add(sorted_edges.get(edge_index));

            //If the current graph contains a cycle then remove the last edge
            //and skip to the next one, until the graph reaches the correct
            //number of edges
            if (isCyclic(new Graph(k_edges, null, null)))
                k_edges.remove(k_edges.size() - 1);

            edge_index++;
        }

        return new Graph(k_edges, g.getStart(), g.getEnd());
    }

    /**
     * Checks if the given graph contains a cycle.
     *
     * @param g the graph that has to be checked
     * @return true if the graph is cyclic, false otherwise.
     */
    public static boolean isCyclic(Graph g) {
        if (g.getEdges().size() == 1) return false;
        else {
            HashMap<Node, ArrayList<Node>> adjacency_matrix = getAdjacencyMatrix(g);

            // Mark all the vertices as not visited
            HashMap<Node, Boolean> visited_nodes = new HashMap<>();
            g.getNodes().forEach(n -> visited_nodes.put(n, false));

            // Call the recursive helper function to detect cycle in
            // different DFS trees
            for (Node n : g.getNodes())
                if (!visited_nodes.get(n)) // Don't recur for index_node if already visited
                    if (isCyclicUtil(adjacency_matrix, visited_nodes, n, null))
                        return true;
            return false;
        }
    }

    /**
     * @param adjacency_matrix
     * @param visited_nodes
     * @param current_node
     * @param parent_node
     * @return
     */
    public static boolean isCyclicUtil(HashMap<Node, ArrayList<Node>> adjacency_matrix, HashMap<Node, Boolean> visited_nodes, Node current_node, Node parent_node) {
        // Mark the current node as visited
        visited_nodes.put(current_node, true);

        // Recur for all the vertices adjacent to this vertex
        ArrayList<Node> adjacent_nodes = adjacency_matrix.get(current_node);

        for (Node adjacent_node : adjacent_nodes) {
            // If an adjacent is not visited, then recur for that adjacent
            if (!visited_nodes.get(adjacent_node)) {
                if (isCyclicUtil(adjacency_matrix, visited_nodes, adjacent_node, current_node))
                    return true;
            }

            // If an adjacent is visited and not parent of current
            // vertex, then there is a cycle. This control excludes the case in which
            // we visit the parent of the node we are analyzing.
            if (!adjacent_node.equals(parent_node) && parent_node != null)
                return true;
        }
        return false;
    }

    /**
     * Builds the adjacency matrix for the given graph, which is easier to use
     * in order to check whether the graph contains cycles or not.
     * In this case we assume that the graph is undirected, so that the matrix we'll
     * expect as output will be symmetrical because each edge will link his src and dst node
     * symmetrically.
     *
     * @param g the given graph
     * @return the adjacency matrix for the given graph
     */
    public static HashMap<Node, ArrayList<Node>> getAdjacencyMatrix(Graph g) {
        HashMap<Node, ArrayList<Node>> adj_matrix = new HashMap<>();

        for (Node n : g.getNodes()) {
            //Check how many lines of code this has just saved us!
            adj_matrix.put(n, g.getEdges().stream()
                    .filter(e -> e.getSrc().equals(n))
                    .map(e -> e.getDst())
                    .collect(Collectors.toCollection(ArrayList::new)));
        }

        //And the symmetrical links!
        for (Node n : g.getNodes()) {
            //Check how many lines of code this has just saved us!
            adj_matrix.get(n).addAll(g.getEdges().stream()
                    .filter(e -> e.getDst().equals(n))
                    .map(e -> e.getSrc())
                    .collect(Collectors.toCollection(ArrayList::new)));
        }

        return adj_matrix;
    }

    public static Graph dijkstra(Graph g) {
        return null;
    }
}
