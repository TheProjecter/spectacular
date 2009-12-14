package minderupt.spectacular.data.model;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: dowlinmi
 * Date: Dec 11, 2009
 * Time: 11:18:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class CommandLineGlobalOptionsTest {


    @Test
    public void testParseCommonOptions() throws Exception {

        String[] args = new String[] {"-specLocation", "/path/to/spec",
                                      "-config", "/path/to/config",
                                      "-fixtures", "some.fixture.package"};


        CommandLineGlobalOptions options = new CommandLineGlobalOptions(args);
        assertNotNull(options.getFixtures());
        assertEquals("some.fixture.package", options.getFixtures());

        assertNotNull(options.getSpecLocation());
        assertEquals("/path/to/spec", options.getSpecLocation());

        assertNotNull(options.getConfig());
        assertEquals("/path/to/config", options.getConfig());


    }

    @Test
    public void testParseBeanNameOptions() throws Exception {

        String[] args = new String[] {"-documentReader", "document reader",
                                      "-artifactExtractor", "artifact extractor"};




    }

    


}
