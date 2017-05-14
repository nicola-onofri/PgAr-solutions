package utils;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by nicola on 14/05/17.
 */
public class XMLUtils {
    /**
     * Loads data for problemA from the given XML file.
     *
     * @param f the given XML file
     */
    public static int[] loadProblemA(File f) throws FileNotFoundException, XMLStreamException {
        XMLInputFactory XMLIFactory = XMLInputFactory.newInstance();
        XMLStreamReader XMLReader = XMLIFactory.createXMLStreamReader(new FileInputStream(f));

        int[] arr = new int[3];

        //Loop through all characters in the buffer
        while (XMLReader.hasNext()) {
            int tagType = 0;
            try {
                tagType = XMLReader.next();
            } catch (XMLStreamException ex) {
                System.out.println("File is empty!");
            }

            switch (tagType) {
                case XMLStreamConstants.START_DOCUMENT:
                    break;
                case XMLStreamConstants.START_ELEMENT:
                    switch (XMLReader.getLocalName()) {
                        case "goal":
                            arr[0] = Integer.parseInt(XMLReader.getAttributeValue(0));
                            arr[1] = Integer.parseInt(XMLReader.getAttributeValue(1));
                            arr[2] = Integer.parseInt(XMLReader.getAttributeValue(2));
                            break;
                    }
                case XMLStreamConstants.END_DOCUMENT:
                    XMLReader.close();
            }
        }
        return arr;
    }

    //TODO
    public static void writeOutputA(File f, String title, int bound, int sum, int[] multiples) {

    }

    //TODO
    public static void loadProblemC(File f) {

    }

    //TODO
    public static void writeOutputC(File f, String title) {

    }

}
