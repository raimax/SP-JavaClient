package lt.viko.eif.rcepauskas.javaclient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SchemaValidatorTest {

    @Test
    void isXmlValid() {
        boolean isValid = SchemaValidator.isXmlValid(
                "src\\test\\resources\\blog.xsd",
                "src\\test\\resources\\blog.xsd");
        Assertions.assertTrue(isValid);
    }
}