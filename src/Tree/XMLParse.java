package Tree;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XMLParse {

    public static NodeList nList = exp();

    public static NodeList exp() {

        try {
            File inputFile = new File("config.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            nList = doc.getElementsByTagName("links");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return nList;
    }

    public static String setD(String attr) {

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                attr = eElement.getElementsByTagName(attr).item(0).getTextContent();
            }
        }
        return attr;
    }
}
