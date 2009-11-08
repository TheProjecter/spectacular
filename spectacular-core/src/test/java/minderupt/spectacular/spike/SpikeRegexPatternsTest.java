package minderupt.spectacular.spike;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Oct 16, 2009
 * Time: 4:01:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class SpikeRegexPatternsTest {

    @Test
    public void testAnyCharRegex() throws Exception {

        Pattern p = Pattern.compile("<.*?>", Pattern.DOTALL);
        Matcher m = p.matcher("<hello attribute='hey'>wah</hello>");

        assertTrue(m.matches());

        String replaced = m.replaceAll("");
        assertEquals("wah", replaced);

        m = p.matcher("<hello" + System.getProperty("line.separator") + " attribute='hey'>wah2</hello>");

        assertTrue(m.matches());

        replaced = m.replaceAll("");
        assertEquals("wah2", replaced);


    }


}
