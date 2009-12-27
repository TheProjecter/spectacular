package minderupt.spectacular.util;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 */
public class TokenParserText {

    @Test
    public void testTokenParserWithStandardText() throws Exception {

        String txt = "commandName param1 param2 param3";
        TokenParser parser = new TokenParser(txt);
        List<String> commands = parser.parse();

        assertNotNull(commands);
        assertEquals(4, commands.size());
        assertEquals("commandName", commands.get(0));
        assertEquals("param1", commands.get(1));
        assertEquals("param2", commands.get(2));
        assertEquals("param3", commands.get(3));

    }


    @Test
    public void testTokenParserWithQuotedPhrases() throws Exception {

        String txt = "commandName param1 \"parampart11 paramparam12\" param3";
        TokenParser parser = new TokenParser(txt);
        List<String> commands = parser.parse();

        assertNotNull(commands);
        assertEquals(4, commands.size());
        assertEquals("commandName", commands.get(0));
        assertEquals("param1", commands.get(1));
        assertEquals("parampart11 paramparam12", commands.get(2));
        assertEquals("param3", commands.get(3));


    }
}
