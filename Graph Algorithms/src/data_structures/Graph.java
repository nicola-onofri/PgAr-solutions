package data_structures;

import java.util.ArrayList;

/**
 * Created by nicola on 13/05/17.
 */
public class Graph {
    private ArrayList<Edge> edges;
    private Node start, end;

    //Constructor
    public Graph(ArrayList<Edge> edges, Node start, Node end) {
        this.edges = edges;
        this.start = start;
        this.end = end;
    }

    /**
     * Returns all the nodes in the graph
     *
     * @return a list of nodes
     */
    public ArrayList<Node> getNodes() {
        ArrayList<Node> nodes = new ArrayList<>();
        for (Edge e : this.getEdges()) {
            if (!nodes.contains(e.getSrc()))
                nodes.add(e.getSrc());
            if (!nodes.contains(e.getDst()))
                nodes.add(e.getDst());
        }
        return nodes;
    }


    //Just for debugging
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("data_structures.data_structures.Graph: \n");
        sb.append("Start node: " + this.start.getLabel() + "\nEnd node: " + this.end.getLabel() + "\n");

        edges.stream().forEach(e -> {
            sb.append("data_structures.data_structures.Edge: " + e.getSrc().getLabel() + " to " + e.getDst().getLabel() + " costs: " + e.getWeight() + "\n");
        });
        return sb.toString();
    }

    //Getters and setters
    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }

    public Node getStart() {
        return start;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public Node getEnd() {
        return end;
    }

    public void setEnd(Node end) {
        this.end = end;
    }
}
