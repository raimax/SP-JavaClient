import org.xmlunit.validation.Languages;
import org.xmlunit.validation.ValidationResult;
import org.xmlunit.validation.Validator;

import javax.xml.transform.stream.StreamSource;
import java.io.File;

/**
 * A class for validating xml files
 */
public class SchemaValidator {

    /**
     * Validates xml file against xsd
     * @param xmlFileName xml file name
     * @param xsdFileName xsd file name
     * @return {@link true} if code is valid, {@link false} otherwise
     */
    public static boolean isXmlValid(String xmlFileName, String xsdFileName) {
        Validator validator = Validator.forLanguage(Languages.W3C_XML_SCHEMA_NS_URI);
        validator.setSchemaSource(new StreamSource(xsdFileName));
        ValidationResult validationResult = validator.validateInstance(new StreamSource(new File(xmlFileName)));
        return validationResult.isValid();
    }
}
