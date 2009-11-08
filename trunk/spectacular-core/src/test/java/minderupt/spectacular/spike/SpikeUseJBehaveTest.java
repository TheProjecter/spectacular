package minderupt.spectacular.spike;

import static org.junit.Assert.*;
import org.junit.Test;
import org.jbehave.scenario.parser.PatternScenarioParser;
import org.jbehave.scenario.definition.StoryDefinition;
import org.jbehave.scenario.definition.ScenarioDefinition;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Oct 16, 2009
 * Time: 5:01:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class SpikeUseJBehaveTest {

    private static final String LINESEP = System.getProperty("line.separator");

    @Test
    public void testCanUsePatternScenarioParser() throws Exception {

        String scenario = "Scenario: Do something useful" + LINESEP +
                          "  Given a user with something useful to do" + LINESEP +
                          "    And a system capable of running the test" + LINESEP +
                          "  When the user tries to parse this scenario" + LINESEP +
                          "    And waits for a response" + LINESEP +
                          "  Then assert this thing as a BDD JBehave test!";

        PatternScenarioParser parser = new PatternScenarioParser();
        StoryDefinition storyDef = parser.defineStoryFrom(scenario);

        assertNotNull(storyDef);

        List<ScenarioDefinition> scenarios = storyDef.getScenarios();
        assertNotNull(scenarios);
        assertEquals(1, scenarios.size());
        assertEquals("Do something useful", scenarios.get(0).getTitle());
        


    }


    @Test
    public void testCanFilterMSWordQuotes() throws Exception {

        String oddQuotes = "this is a string with “weird quotes“ and stuff";
        String what = oddQuotes.replaceAll("“", "#");
        assertEquals("this is a string with #weird quotes# and stuff", what);



    }


}
