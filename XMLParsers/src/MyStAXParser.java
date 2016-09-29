/**
 * Created by alex on 6/4/15.
 * StAX parser
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class MyStAXParser {
    /**
     * List of all parsed objects of class Plane.
     */
    private List<Plane> plList;

    /**
     * Comparator for ordering output objects by Plane id.
     */
    public static final Comparator<Plane> BY_ID = new ByID();

    private static class ByID implements Comparator<Plane> {
        public int compare(Plane one, Plane two){
            return one.id.compareTo(two.id);
        }
    }

    public MyStAXParser() {
        plList = new ArrayList<Plane>();
    }

    /**
     * Method "parser" parses input xml file to Plane Objects
     * and add them to plList
     * @param filename - string containing xml file to parse
     */
    public void parser(String filename)  throws XMLStreamException {
        Plane currPlane = new Plane();
        String tagContent = null;
        Plane.Params curParams = new Plane.Params();
        int flag = 0;

        XMLInputFactory factory = XMLInputFactory.newInstance();
//        XMLStreamReader reader =
//                factory.createXMLStreamReader(
//                        ClassLoader.getSystemResourceAsStream("Final/worklist.xml"));
        XMLStreamReader reader = null;
        try {
            reader = factory.createXMLStreamReader(new FileInputStream(new File(filename)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while(reader.hasNext()){
            int event = reader.next();

            switch(event){
                case XMLStreamConstants.START_ELEMENT:
                    if ("plane".equals(reader.getLocalName())){
                        currPlane.id = reader.getAttributeValue(0);
                    }
                    if ("Length".equals(reader.getLocalName()))
                        flag=1;
                    if ("Width".equals(reader.getLocalName()))
                        flag=2;
                    if ("Height".equals(reader.getLocalName()))
                        flag=3;
//                    if ("parameters".equals(reader.getLocalName())) {
//                        curParams = new Plane.Params();
//                    }
//                    if("root".equals(reader.getLocalName())){
//                        plList = new ArrayList<Plane>();
//                    }
                    break;

                case XMLStreamConstants.CHARACTERS:
                    tagContent = reader.getText().trim();
                    if (flag == 1) {
                        curParams.setLength(Integer.valueOf(tagContent));
                    }
                    if (flag == 2)
                        curParams.width = Integer.valueOf(tagContent);
                    if (flag == 3)
                        curParams.height = Integer.valueOf(tagContent);
                    break;

                case XMLStreamConstants.END_ELEMENT: {
                    if (reader.getLocalName() == null)
                        break;

                    if (reader.getLocalName() == "plane") {
                        plList.add(currPlane);
                        currPlane = new Plane();
                    }
                    if (reader.getLocalName() == "model")
                        currPlane.setModel(tagContent);
                    if (reader.getLocalName() == "origin")
                        currPlane.setOrigin(tagContent);
                    if (reader.getLocalName() == "type")
                        currPlane.setType(tagContent);
                    if (reader.getLocalName() == "Length")
                        flag = 0;
                    //curParams.length = Integer.valueOf(tagContent);
                    if (reader.getLocalName() == "Width")
                        flag = 0;
                    //curParams.width = Integer.valueOf(tagContent);
                    if (reader.getLocalName() == "Height")
                        flag = 0;
                    //curParams.height = Integer.valueOf(tagContent);
                    if (reader.getLocalName() == "parameters") {
                        currPlane.parameters = curParams;
                        curParams = new Plane.Params();
                    }
                    if (reader.getLocalName() == "price")
                        currPlane.setPrice(Integer.valueOf(tagContent));
                    break;
                }

                case XMLStreamConstants.START_DOCUMENT:
                    //plList = new ArrayList<Plane>();
                    break;
            }

        }
    }

    //Print all parsed objects
    public void print() {
        Collections.sort(plList, MyStAXParser.BY_ID);

        for ( Plane p : plList){
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

    public static void main(String[] args) {
        MyStAXParser test = new MyStAXParser();

        try {
            test.parser("Final/worklist.xml");
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        test.print();
    }
}
