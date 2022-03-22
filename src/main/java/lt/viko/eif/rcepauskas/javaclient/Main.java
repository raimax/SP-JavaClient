package lt.viko.eif.rcepauskas.javaclient;

import lt.viko.eif.rcepauskas.blog.Blog;
import lt.viko.eif.rcepauskas.blog.JaxbTransformer;

import javax.xml.bind.JAXBException;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        JavaClient javaClient = new JavaClient();

        try {
            javaClient.startConnection("127.0.0.1", 6666);
            javaClient.receiveFile("src\\main\\resources\\blog.xml");
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            javaClient.stopConnection();
        }

        try {
            if (new File("src\\main\\resources\\blog.xml").exists()) {
                if (SchemaValidator.isXmlValid("src\\main\\resources\\blog.xml", "src\\main\\resources\\blog.xsd")) {
                    Blog blog = (Blog) JaxbTransformer.xmlToPojo("src\\main\\resources\\blog.xml", Blog.class);
                    System.out.println(blog);
                }
                else {
                    System.out.println("Xml validation failed");
                }
            }
        }
        catch (JAXBException ex) {
            System.out.println("Jaxb transformation error: " + ex.getMessage());
        }
    }
}
