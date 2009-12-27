package minderupt.spectacular.executor.euc.selenium;

import minderupt.spectacular.executor.euc.script.SeleneseScript;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 */
public class SeleneseScriptTest {

    private static final String LINESEP = System.getProperty("line.separator");

    @Test
    public void testParseSingleStep() throws Exception {

        String scriptBody = "Flow: User wants to do something useful" + LINESEP
                + " " + LINESEP
                + "open /" + LINESEP
                + "\tclick \"buttonName here\"" + LINESEP;


        SeleneseScript script = new SeleneseScript(scriptBody);
        assertNotNull(script.getFlowParts());
        assertEquals(1, script.getFlowParts().size());

        assertEquals("open", script.getFlowParts().get(0).getCommands().get(0).get(0));
        assertEquals("/", script.getFlowParts().get(0).getCommands().get(0).get(1));

        assertEquals("click", script.getFlowParts().get(0).getCommands().get(1).get(0));
        assertEquals("buttonName here", script.getFlowParts().get(0).getCommands().get(1).get(1));


    }

    @Test
    public void testParseMultipleSteps() throws Exception {

        String scriptBody = "Flow: User wants to do something useful" + LINESEP
                + " " + LINESEP
                + "open /" + LINESEP
                + "\tclick \"buttonName here\"" + LINESEP
                + LINESEP
                + LINESEP
                + "Expectation: User expects something great" + LINESEP
                + "\tclick \"xpath://hr/a[@class='blah']\"" + LINESEP
                + "     assertNothing what yourmom" + LINESEP
                + LINESEP
                + LINESEP
                + "Flow: Doing something else that's useful I'm sure" + LINESEP
                + LINESEP
                + "click something else" + LINESEP
                + "open \"/some/path/to/paradiso\"" + LINESEP;


        SeleneseScript script = new SeleneseScript(scriptBody);
        assertNotNull(script.getFlowParts());
        assertEquals(2, script.getFlowParts().size());
        assertEquals(1, script.getExpectationParts().size());

        assertEquals("User wants to do something useful", script.getFlowParts().get(0).getStep());

        assertEquals("open", script.getFlowParts().get(0).getCommands().get(0).get(0));
        assertEquals("/", script.getFlowParts().get(0).getCommands().get(0).get(1));

        assertEquals("click", script.getFlowParts().get(0).getCommands().get(1).get(0));
        assertEquals("buttonName here", script.getFlowParts().get(0).getCommands().get(1).get(1));

        assertEquals("Doing something else that's useful I'm sure", script.getFlowParts().get(1).getStep());

        assertEquals("click", script.getFlowParts().get(1).getCommands().get(0).get(0));
        assertEquals("something", script.getFlowParts().get(1).getCommands().get(0).get(1));
        assertEquals("else", script.getFlowParts().get(1).getCommands().get(0).get(2));

        assertEquals("open", script.getFlowParts().get(1).getCommands().get(1).get(0));
        assertEquals("/some/path/to/paradiso", script.getFlowParts().get(1).getCommands().get(1).get(1));
        

    }


}
