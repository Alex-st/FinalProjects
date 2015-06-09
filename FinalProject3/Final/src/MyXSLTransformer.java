/**
 * Created by alex on 6/4/15.
 * Generates html page from input xml file according to xsl schema
 */
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class MyXSLTransformer {

    public static void main(String ...args) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer(new StreamSource("Final/planes.xsl"));
        transformer.transform(new StreamSource("Final/worklist.xml"),new StreamResult("Final/worklist.html"));
    }


}
