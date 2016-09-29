/**
 * Created by alex on 6/3/15.
 * Class for standard SAX parser.
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;


public class MySAXParser extends DefaultHandler {

     /**
     * List of all parsed objects of class Plane.
     */
     private List<Plane> l=new ArrayList<Plane>();

     /**
     * Temporary variable for single Plane Object
     */
     private Plane p;

     /**
     * flag variable needed for proper parsing using SAX parser.
     */
     private int flag=0;

     public List<Plane> getPlanes(){
            return l;
        }

     @Override
     public void startDocument(){
            System.out.println("start parsing...");
        }

     @Override
     public void endDocument(){
            System.out.println("end parsing.");
        }
     @Override
     public void startElement(String uri,String localName,
                                 String qName, Attributes attrs) {
//            System.out.println("uri "+uri);
            //uri = "http://www.w3.org/2001/XMLSchema-instance";
            //uri = "http://www.yazatebe.org.ua";
            //uri = "http://www.w3.org";
            //localName = "params";

         if (qName.equals("plane")) {
                p = new Plane();
                p.setId(attrs.getValue("id"));
         }
         if (qName.equals("model")) {
                flag =1;
         }
         if (qName.equals("origin")) {
                flag = 2;
         }
         if (qName == "type") {
                flag = 3;
         }
         if (qName == "parameters") {
                p.parameters = new Plane.Params();
         }
         if (qName == "params:Length") {
                flag = 4;
         }
         if (qName == "params:Width") {
                flag = 5;
         }
         if (qName == "params:Height") {
                flag = 6;
         }
            if (qName == "price") {
                flag = 7;
         }

     }

      @Override
      public void characters(char[] text,int start, int len) {
            String s=new String(text,start,len);

            switch(flag){
                case 1:{
                    p.setModel(s);
                    break;
                }
                case 2:{
                    p.setOrigin(s);
                    break;
                }
                case 3:{
                    p.setType(s);
                    break;
                }
                case 4:{
                    p.parameters.setLength(Integer.valueOf(s));
                    break;
                }
                case 5:{
                    p.parameters.setWidth(Integer.valueOf(s));
                    break;
                }
                case 6:{
                    p.parameters.setHeight(Integer.valueOf(s));
                    break;
                }
                case 7:{
                    p.setPrice(Integer.valueOf(s));
                    break;
                }

            }
            flag=0;
      }

      @Override
      public void endElement(String uri,String localName,
                               String qName) {
            if (qName == "plane"){
                l.add(p);
                p=null;
                flag=0;

            }
      }

    //http://www.edankert.com/validate.html
    //http://www.journaldev.com/895/how-to-validate-xml-against-xsd-in-java
    /**
     * Method "isValid" is a validator of chosen xml file
     * against xsd schema
     * @param fileXml - string containing xml file to validate
     * @param fileXsd - string containing required xsd schema
     * @return boolean Returns result of validation.
     */
    public static boolean isValid(String fileXml, String fileXsd) {
        try {
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(fileXsd));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(fileXml)));
        } catch ( SAXException e) {
            System.out.println("Exception: "+e.getMessage());
            return false;
        } catch (IOException e) {
            System.out.println("file not found");
            return false;
        }
        return true;
    }

    public void print() {
        for(Plane p:l){
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

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
            SAXParser parser =
                    SAXParserFactory.newInstance().newSAXParser();
            MySAXParser myParser=new MySAXParser();

            System.out.println(MySAXParser.isValid("Final/worklist.xml", "Final/planes.xsd"));

            parser.parse("Final/worklist.xml", myParser);

            //List<Plane> listPlanes=myParser.getPlanes();
            myParser.print();

    }

}
