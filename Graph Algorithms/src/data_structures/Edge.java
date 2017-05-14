package data_structures;

/**
 * Created by nicola on 13/05/17.
 */
public class Edge {
    private int weight;
    private Node src, dst;

    //Constructor
    public Edge(int weight, Node src, Node dst) {
        this.weight = weight;
        this.src = src;
        this.dst = dst;
    }

    //Getters and setters
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Node getSrc() {
        return src;
    }

    public void setSrc(Node src) {
        this.src = src;
    }

    public Node getDst() {
        return dst;
    }

    public void setDst(Node dst) {
        this.dst = dst;
    }
}
