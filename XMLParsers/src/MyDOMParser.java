/**
 * Created by alex on 6/4/15.
 * DOM Parser
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;

public class MyDOMParser {

    private List<Plane> l;
    private Document doc;
    private DOMParser domparser;

    /**
     * Comparator for ordering output objects by Plane id.
     */
    public static final Comparator<Plane> BY_ID = new ByID();

    private static class ByID implements Comparator<Plane> {
        public int compare(Plane one, Plane two){
            return one.id.compareTo(two.id);
        }
    }

    public MyDOMParser() {
        l = new ArrayList<Plane>();
        doc = null;
        domparser = new DOMParser();
    }
    /**
     * Method "parser" parses input xml file to Plane Objects
     * and add them to List l
     * @param filename - string containing xml file to parse
     */
    public void parser(String filename) throws IOException, SAXException {

        domparser.parse(filename);
        doc = domparser.getDocument();
        Element root = doc.getDocumentElement();

        NodeList nodeList = root.getElementsByTagName("plane");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element plane = (Element) nodeList.item(i);

            Plane p = new Plane();
            p.id = plane.getAttribute("id");

            Element model = (Element) plane.getElementsByTagName(
                    "model").item(0);
            p.setModel(((Text) model.getFirstChild()).getNodeValue());

            Element origin = (Element) plane.getElementsByTagName(
                    "origin").item(0);
            p.setOrigin(((Text) origin.getFirstChild()).getNodeValue());

            Element type = (Element) plane.getElementsByTagName(
                    "type").item(0);
            p.setType(((Text) origin.getFirstChild()).getNodeValue());

            Element parameters = (Element) plane.getElementsByTagName(
                    "parameters").item(0);
            Plane.Params p1 = new Plane.Params();
            p.parameters = p1;

//            Element length = (Element) parameters.getElementsByTagNameNS("http://www.w3.org/2001/XMLSchema-instance", "Length").item(0);
//            p1.length = Integer.valueOf(((Text) length.getFirstChild()).getNodeValue());

            Element length = (Element) parameters.getElementsByTagName("params:Length")
                    .item(0);
            p1.length = Integer.valueOf(((Text) length.getFirstChild()).getNodeValue());

            Element width = (Element) parameters.getElementsByTagName("params:Width")
                    .item(0);
            p1.width = Integer.valueOf(((Text) width.getFirstChild()).getNodeValue());

            Element height = (Element) parameters.getElementsByTagName(
                    "params:Height").item(0);
            p1.height = Integer.valueOf(((Text) height.getFirstChild()).getNodeValue());

            Element price = (Element) plane.getElementsByTagName(
                    "price").item(0);
            p.setPrice(Integer.valueOf(((Text) price.getFirstChild()).getNodeValue()));

            l.add(p);
        }
    }

    public void print() {

        Collections.sort(l, MyDOMParser.BY_ID);

        for (Plane p : l) {
            System.out.println("-----------------------");
            System.out.println(p.getId());
            System.out.println(p.getModel());
            System.out.println(p.getOrigin());
            System.out.println(p.getType());
            System.out.println(p.parameters.getWidth());
            System.out.println(p.parameters.getLength());
            System.out.println(p.parameters.getHeight());
            System.out.println(p.getPrice());
        }
    }

    public static void main(String[] arg) {

        MyDOMParser test = new MyDOMParser();

        try {
            test.parser("Final/worklist.xml");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        test.print();
    }
}
