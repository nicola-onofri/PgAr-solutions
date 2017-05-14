import data_structures.Graph;
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
//        System.out.println(g.toString());

        try {
            XMLUtils.writeGraph(g, new File("res/output.xml"), "Test");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        //TODO implement Kruskal's and Dijkstra's logic
        k = null;
        d = null;

        //TODO write results on external files

    }

}
