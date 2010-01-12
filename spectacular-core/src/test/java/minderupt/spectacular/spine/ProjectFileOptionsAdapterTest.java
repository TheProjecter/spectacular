package minderupt.spectacular.spine;

import minderupt.spectacular.data.model.GlobalOptions;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 */
public class ProjectFileOptionsAdapterTest {

    @Test
    public void testParseSingleSpecOptions() throws Exception {

        String specOptions = "<spectacular>\n" +
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

        ProjectFileOptionsAdapter adapter = new ProjectFileOptionsAdapter();
        List<GlobalOptions> specOptionsList = adapter.getSpecOptions(specOptions);

        assertNotNull(specOptionsList);
        assertEquals(1, specOptionsList.size());
        assertEquals("localhost", specOptionsList.get(0).getSeleniumRCHost());
        assertEquals(4444, specOptionsList.get(0).getSeleniumRCPort());
        assertEquals("*safari", specOptionsList.get(0).getSeleniumRCStartupCommand());
        assertEquals("http://www.cnet.com/", specOptionsList.get(0).getSeleniumRCInitialUrl());


    }

    @Test
    public void testParseSpecWithCommentBeforeRootOptions() throws Exception {

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

        ProjectFileOptionsAdapter adapter = new ProjectFileOptionsAdapter();
        List<GlobalOptions> specOptionsList = adapter.getSpecOptions(specOptions);

        assertNotNull(specOptionsList);
        assertEquals(1, specOptionsList.size());
        assertEquals("localhost", specOptionsList.get(0).getSeleniumRCHost());
        assertEquals(4444, specOptionsList.get(0).getSeleniumRCPort());
        assertEquals("*safari", specOptionsList.get(0).getSeleniumRCStartupCommand());
        assertEquals("http://www.cnet.com/", specOptionsList.get(0).getSeleniumRCInitialUrl());


    }

    @Test
    public void testParseMultipleSpecOptions() throws Exception {

        String specOptions = "<spectacular>\n" +
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
                "    <for-spec spec=\"http://code.google.com/p/spectacular/wiki/SpecLocation\">\n" +
                "\n" +
                "        <option name=\"documentReader\" value=\"blahReader\" />\n" +
                "        <option name=\"reportWriter\" value=\"blahWriter\" />\n" +
                "\n" +
                "    </for-spec>\n" +
                "\n" +
                "\n" +
                "\n" +
                "</spectacular>";


        ProjectFileOptionsAdapter adapter = new ProjectFileOptionsAdapter();
        List<GlobalOptions> specOptionsList = adapter.getSpecOptions(specOptions);

        assertNotNull(specOptionsList);
        assertEquals(2, specOptionsList.size());

        assertEquals("localhost", specOptionsList.get(0).getSeleniumRCHost());
        assertEquals(4444, specOptionsList.get(0).getSeleniumRCPort());
        assertEquals("*safari", specOptionsList.get(0).getSeleniumRCStartupCommand());
        assertEquals("http://www.cnet.com/", specOptionsList.get(0).getSeleniumRCInitialUrl());

        assertEquals("blahReader", specOptionsList.get(1).getDocumentReaderBeanName());
        assertEquals("blahWriter", specOptionsList.get(1).getReportWriterBeanName());


    }


}
