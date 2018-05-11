package Tree;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Класс синтаксичкеского анализа XML-файла,
 * который содержит конфигурационные данные.
 *
 * @author  Nikita Mabatov
 * @version 1.6
 * @since   10.05.2018 */
public class XMLParsing {

    public static NodeList nodeList = getURL();

    public static NodeList getURL() {

        try {
            File configFile = new File("config.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(configFile);
            nodeList = doc.getElementsByTagName("hyperlinks");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return nodeList;
    }

    public static String setNodeName(String nodeName) {
        String text = "";
        for (int temp = 0; temp < nodeList.getLength(); temp++) {
            Node node = nodeList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                text = eElement.getElementsByTagName(nodeName).item(0).getTextContent();
            }
        }
        return text;
    }
}
