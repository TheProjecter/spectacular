package minderupt.spectacular.executor.euc.selenium;

import minderupt.spectacular.executor.euc.Executable;
import minderupt.spectacular.executor.euc.selenium.SelenesePart;
import org.junit.Test;

import java.util.*;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 */
public class SeleneseIndexerTest {

    @Test
    public void testSingleSeleneseFlowStep() throws Exception {

        List<String> scriptList = new LinkedList<String>();
        scriptList.add("src/test/selenese/testSingleSeleneseFlowStep.selenese");

        Map<Pattern, Executable> flowMap = new HashMap<Pattern, Executable>();
        Map<Pattern, Executable> expectMap = new HashMap<Pattern, Executable>();


        SeleneseIndexer indexer = new SeleneseIndexer();
        indexer.indexScripts(scriptList, flowMap, expectMap);

        assertNotNull(flowMap);
        assertNotNull(expectMap);

        assertEquals(0, expectMap.keySet().size());
        assertEquals(1, flowMap.keySet().size());
        Pattern p = flowMap.keySet().iterator().next();

        assertNotNull(p);
        assertEquals("Some kind of flow step", p.pattern());

        SelenesePart part = ((SeleneseExecutable) flowMap.get(p)).getSelenesePart();
        assertNotNull(part);
        assertEquals(2, part.getCommands().size());

        assertEquals("open", part.getCommands().get(0).get(0));
        assertEquals("/", part.getCommands().get(0).get(1));

        assertEquals("click", part.getCommands().get(1).get(0));
        assertEquals("xpath://hr/a[@class='what']", part.getCommands().get(1).get(1));


    }

    @Test
    public void testMultipleSeleneseFlowSteps() throws Exception {

        List<String> scriptList = new LinkedList<String>();
        scriptList.add("src/test/selenese/testMultipleSeleneseFlowSteps.selenese");

        Map<Pattern, Executable> flowMap = new HashMap<Pattern, Executable>();
        Map<Pattern, Executable> expectMap = new HashMap<Pattern, Executable>();


        SeleneseIndexer indexer = new SeleneseIndexer();
        indexer.indexScripts(scriptList, flowMap, expectMap);

        assertNotNull(flowMap);
        assertNotNull(expectMap);

        assertEquals(2, expectMap.keySet().size());
        assertEquals(2, flowMap.keySet().size());

        


    }


}
