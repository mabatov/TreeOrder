package Tree;

import POJO.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class XMLParse {

    public static String relatDescr;
    public static String groups;
    public static String projects;
    public static String users;
    public static String relations;

    public static String setUs() {
        try {
            File inputFile = new File("config.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            NodeList nList = doc.getElementsByTagName("links");


            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    users = eElement.getElementsByTagName("users").item(0).getTextContent();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public static String setRD() {
        try {
            File inputFile = new File("config.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            NodeList nList = doc.getElementsByTagName("links");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    relatDescr = eElement.getElementsByTagName("relatDescr").item(0).getTextContent();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return relatDescr;
    }

    public static String setR() {
        try {
            File inputFile = new File("config.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            NodeList nList = doc.getElementsByTagName("links");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    relations = eElement.getElementsByTagName("relations").item(0).getTextContent();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return relations;
    }

    public static String setGr() {
        try {
            File inputFile = new File("config.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            NodeList nList = doc.getElementsByTagName("links");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    groups = eElement.getElementsByTagName("groups").item(0).getTextContent();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return groups;
    }

    public static String setPr() {
        try {
            File inputFile = new File("config.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            NodeList nList = doc.getElementsByTagName("links");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    projects = eElement.getElementsByTagName("projects").item(0).getTextContent();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projects;
    }

}
