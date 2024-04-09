import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import java.io.File;

public class XSLTTransform {
    public static void main(String[] args) {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Source xsl = new StreamSource(new File("employees.xsl"));
            Transformer transformer = factory.newTransformer(xsl);

            Source xml = new StreamSource(new File("employees.xml"));
            transformer.transform(xml, new StreamResult(new File("output.html")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
