import lt.viko.eif.rcepauskas.blog.Blog;
import lt.viko.eif.rcepauskas.blog.JaxbTransformer;

import javax.xml.bind.JAXBException;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        JavaClient javaClient = new JavaClient();

        try {
            javaClient.startConnection("127.0.0.1", 6666);
            javaClient.receiveFile("blog.xml");
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            javaClient.stopConnection();
        }

        try {
            if (new File("blog.xml").exists()) {
                if (SchemaValidator.isXmlValid("blog.xml", "blog.xsd")) {
                    Blog blog = (Blog) JaxbTransformer.xmlToPojo("blog.xml", Blog.class);
                    System.out.println(blog);
                }
            }
        }
        catch (JAXBException ex) {
            System.out.println("Jaxb transformation error: " + ex.getMessage());
        }
    }
}
