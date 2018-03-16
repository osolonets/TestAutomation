package Utils.FileParsers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class XMLParser
{
    Document document;

    public Map<String, Map<String, String>> getREXPurchaseDetails(String fileName)
    {
        NodeList productList, stateList;
        Node stateNode;
        Element element;
        Map<String, Map<String, String>> tempObject = new HashMap<>();
        HashMap<String, String> tempMap;
        String state, packageName, sku, coursesQuantity, price;

        document = parseXMLFile(fileName);
        productList = document.getElementsByTagName("Products");
        for (int i = 0; i < productList.getLength(); i++) {

            stateList = productList.item(i).getChildNodes();

            for (int j = 0; j < stateList.getLength(); j++) {

                stateNode = stateList.item(j);

                if (stateNode.getNodeType() == Node.ELEMENT_NODE) {

                    element = (Element) stateNode;

                    state =             element.getAttributes().getNamedItem("value").getNodeValue();
                    packageName =       element.getAttributes().getNamedItem("package").getNodeValue();
                    sku =               element.getElementsByTagName("Sku").item(0).getTextContent();
                    coursesQuantity =   element.getElementsByTagName("CoursesQuantity").item(0).getTextContent();
                    price =             element.getElementsByTagName("Price").item(0).getTextContent();

                    tempMap = new HashMap<>();
                    tempMap.put("packageName", packageName);
                    tempMap.put("sku", sku);
                    tempMap.put("coursesQuantity", coursesQuantity);
                    tempMap.put("price", price);

                    tempObject.put(state, tempMap);
                }
            }
        }
        return tempObject;
    }

    private Document parseXMLFile (String fileName) {

        File file = new File(fileName);
        Document document;
        try {
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
        } catch (Exception e) {
            throw new RuntimeException("Error during parsing XML file", e);
        }
        return document;
    }
}
