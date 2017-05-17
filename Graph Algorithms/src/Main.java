import data_structures.Graph;
import utils.GraphUtils;
import utils.XMLUtils;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by nicola on 13/05/17.
 */
public class Main {
    public static void main(String args[]) {
        Graph g = null, k, d;
        File f = new File("res/input.xml");
        try {
            g = XMLUtils.loadGraph(f);
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }

        k = GraphUtils.kruskal(g);

        try {
            //XMLUtils.writeGraph(g, new File("res/output.xml"), "Test");
            XMLUtils.writeGraph(k, new File("res/kruskal.xml"), "Kruskal SST for the graph @" + f.getName());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        //TODO implement Dijkstra's logic
        d = null;

    }

}
