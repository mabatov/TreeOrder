package Tree;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XMLParse {

    public static String relatDescr;
    public static String groups;
    public static String projects;
    public static String users;
    public static String relations;

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


    public static String setUs() {

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                users = eElement.getElementsByTagName("users").item(0).getTextContent();
            }
        }
        return users;
    }

    public static String setRD() {

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                relatDescr = eElement.getElementsByTagName("relatDescr").item(0).getTextContent();
            }
        }
        return relatDescr;
    }

    public static String setR() {

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                relations = eElement.getElementsByTagName("relations").item(0).getTextContent();
            }
        }
        return relations;
    }

    public static String setGr() {

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                groups = eElement.getElementsByTagName("groups").item(0).getTextContent();
            }
        }
        return groups;
    }

    public static String setPr() {

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                projects = eElement.getElementsByTagName("projects").item(0).getTextContent();
            }
        }
        return projects;
    }
}
