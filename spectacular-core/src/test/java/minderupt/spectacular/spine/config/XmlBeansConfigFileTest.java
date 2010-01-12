package minderupt.spectacular.spine.config;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: dowlinmi
 * Date: Jan 12, 2010
 * Time: 11:19:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class XmlBeansConfigFileTest {

    @Test
    public void testReadXmlIntoJava() throws Exception {
        String specOptions = "<!-- this is a comment -->\n<spectacular>\n" +
                  "\n" +
                  "    <for-spec spec=\"file:spec_location.html\">\n" +
                  "\n" +
                  "        <option name=\"seleniumRCHost\" value=\"localhost\" />\n" +
                  "        <option name=\"seleniumRCPort\" value=\"4444\" />\n" +
                  "        <option name=\"seleniumRCStartupCommand\" value=\"*safari\" />\n" +
                  "        <option name=\"seleniumRCInitialUrl\" value=\"http://www.cnet.com/\" />\n" +
                  "\n" +
                  "    </for-spec>\n" +
                  "\n" +
                  "\n" +
                  "\n" +
                  "</spectacular>";

        SpectacularDocument spec = SpectacularDocument.Factory.parse(specOptions);
        assertNotNull(spec);
        assertEquals("seleniumRCHost", spec.getSpectacular().getForSpecArray()[0].getOptionArray()[0].getName());
        assertEquals("localhost", spec.getSpectacular().getForSpecArray()[0].getOptionArray()[0].getValue());

    }


}
