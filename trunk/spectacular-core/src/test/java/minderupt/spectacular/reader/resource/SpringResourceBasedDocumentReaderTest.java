package minderupt.spectacular.reader.resource;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: dowlinmi
 * Date: Nov 4, 2009
 * Time: 10:44:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class SpringResourceBasedDocumentReaderTest {

    @Test
    public void testLoadFileFromClasspath() throws Exception {

        SpringResourceBasedDocumentReader docReader = new SpringResourceBasedDocumentReader();
        String fileContents = docReader.read("classpath:testdocs/RealDoc1.html");

        assertNotNull(fileContents);
        assertEquals("<html>hello</html>", fileContents.trim());

    }


}
