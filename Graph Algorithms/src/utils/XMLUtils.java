package utils;

import data_structures.Edge;
import data_structures.Graph;
import data_structures.Node;

import javax.xml.stream.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by nicola on 13/05/17.
 */
public class XMLUtils {
    /**
     * Creates a graph structure given an external file,
     * written in XML format.
     *
     * @param f the external file containing the graph structure
     * @return a graph object,
     * @throws FileNotFoundException
     * @throws XMLStreamException
     */
    public static Graph loadGraph(File f) throws FileNotFoundException, XMLStreamException {
        XMLInputFactory XMLIFactory = XMLInputFactory.newInstance();
        XMLStreamReader XMLReader = XMLIFactory.createXMLStreamReader(new FileInputStream(f));
        ArrayList<Edge> edges = new ArrayList<>();
        ArrayList<Node> nodes = new ArrayList<>();
        ArrayList<String> nodeLabels = new ArrayList<>();

        Edge e = null;
        Node nodeSrc = null, nodeDst = null;
        Graph g = null;
        String XMLtagRead = null, txtData = "";
        int edgeCost = 0;

        //Loop through all characters in the buffer
            while (XMLReader.hasNext()) {
                int nextTagType = 0;
                try {
                    nextTagType = XMLReader.next();
                } catch (XMLStreamException ex) {
                    System.out.println("File is empty!");
                }

            //Discrimination between beginning tag, content and end tag
            switch (nextTagType) {
                //Open document
                case XMLStreamConstants.START_DOCUMENT:
                    System.out.println("Reading document...");
                    break;
                //Open tag
                case XMLStreamConstants.START_ELEMENT:
                    switch (XMLReader.getLocalName()) {
                        //New graph starting here
                        case "tree": //tree tag found
                            g = new Graph(new ArrayList<>(), null, null);
                            break;
                        case "node": //node tag found
                            nodeSrc = new Node(null);
                            String attribute = XMLReader.getAttributeLocalName(0);

                            if ("start".equals(attribute))
                                g.setStart(nodeSrc);
                            else if ("end".equals(attribute))
                                g.setEnd(nodeSrc);
                            nodes.add(nodeSrc);
                            break;
                        case "edge":  //edge tag found
                            edgeCost = Integer.parseInt(XMLReader.getAttributeValue(0));
                            break;
                        default: //label, edges tags
                            //Skip to next tag
                            break;
                    }
                    break;

                //Tag content
                case XMLStreamConstants.CHARACTERS:
                    if (XMLReader.getText().trim().length() > 0)
                        txtData = XMLReader.getText().trim();
                    break;

                //Close tag
                case XMLStreamConstants.END_ELEMENT:
                    String closingTag = XMLReader.getLocalName();
                    switch (closingTag) {
                        case "label":
                            nodeSrc.setLabel(txtData);
                            //Mark label as "already visited"
                            nodeLabels.add(nodeSrc.getLabel());
                            break;
                        case "edge":
                            //If label is not marked as "already visited" create
                            //here a new node
                            if (!nodeLabels.contains(txtData)) {
                                nodeDst = new Node(txtData);
                                edges.add(new Edge(edgeCost, nodeSrc, nodeDst));
                                nodes.add(nodeDst);
                            } //Otherwise search for the node with the given label
                            //and link it with nodeSrc
                            else {
                                String lbl = txtData;
                                nodeDst = nodes.stream()
                                        .filter(n -> n.getLabel().equals(lbl))
                                        .findFirst()
                                        .get();
                                edges.add(new Edge(edgeCost, nodeSrc, nodeDst));
                            }
                            break;
                        default: //edges, tree, node
                            //do nothing
                            break;
                    }
                    break;

                //Close document
                case XMLStreamConstants.END_DOCUMENT:
                    XMLReader.close();
                    break;
            }
        }
        g.setEdges(edges);
        return g;
    }

    /**
     * Writes the given graph on a file, using standard XML format
     *
     * @param g the graph that has to be converted and written
     * @param f the file that has to be written
     * @throws FileNotFoundException
     * @throws XMLStreamException
     */
    //TODO a better version in terms of readability would use several function
    //TODO to write different parts of the graph, all called withing for loops
    //TODO to iterate through nodes and edges -> easier to maintain and debug
    public static void writeGraph(Graph g, File f, String title) throws IOException, XMLStreamException {
        if (f.exists())
            f.delete();
        f.createNewFile();

        XMLOutputFactory XMLOFactory = XMLOutputFactory.newInstance();
        XMLStreamWriter XMLWriter = XMLOFactory.createXMLStreamWriter(new FileOutputStream(f));

        //Default XML format document
        XMLWriter.writeStartDocument();
        XMLWriter.writeCharacters("\n");
        XMLWriter.writeStartElement("order");
        XMLWriter.writeCharacters("\n");
        XMLWriter.writeStartElement("meta");
        XMLWriter.writeCharacters("\n");
        XMLWriter.writeStartElement("name");
        XMLWriter.writeCharacters(title);
        XMLWriter.writeEndElement(); //close name
        XMLWriter.writeCharacters("\n");
        XMLWriter.writeEndElement(); //close meta
        XMLWriter.writeCharacters("\n");
        XMLWriter.writeStartElement("tree");
        XMLWriter.writeCharacters("\n");

        for (Node n : g.getNodes()) {
            XMLWriter.writeStartElement("node");
            if (g.getStart().equals(n)) XMLWriter.writeAttribute("start", "true");
            if (g.getEnd().equals(n)) XMLWriter.writeAttribute("end", "true");
            XMLWriter.writeCharacters("\n");

            XMLWriter.writeStartElement("label");
            XMLWriter.writeCharacters(n.getLabel());
            XMLWriter.writeEndElement(); //close this label

            //Edges section
            if (g.getEdges().stream()
                    .anyMatch(e -> e.getSrc().equals(n))) {
                XMLWriter.writeStartElement("edges");
                XMLWriter.writeCharacters("\n");

                g.getEdges().stream()
                        .filter(e -> e.getSrc().equals(n))
                        .sorted(Comparator.comparingInt(Edge::getWeight))
                        .forEach(e -> {
                            try {
                                XMLWriter.writeStartElement("edge");
                                XMLWriter.writeAttribute("weight", String.valueOf(e.getWeight()));
                                XMLWriter.writeCharacters(e.getDst().getLabel());
                                XMLWriter.writeEndElement(); //close this edge
                                XMLWriter.writeCharacters("\n");
                            } catch (XMLStreamException e1) {
                                e1.printStackTrace();
                            }
                        });
                XMLWriter.writeEndElement(); //close edges
            } //close edges section

            XMLWriter.writeCharacters("\n");
            XMLWriter.writeEndElement(); //close this node
        }

        XMLWriter.writeEndElement(); //close tree
        XMLWriter.writeCharacters("\n");
        XMLWriter.writeEndElement(); //close order
        XMLWriter.writeCharacters("\n");
        XMLWriter.writeEndDocument(); //close document
    }
}
