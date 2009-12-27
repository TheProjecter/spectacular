package minderupt.spectacular.executor.euc.selenium;

import minderupt.spectacular.executor.euc.Executable;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

        assertEquals(1, flowMap.keySet().size());
        Pattern p = flowMap.keySet().iterator().next();

        assertNotNull(p);
        assertEquals("Some kind of flow step", p.pattern());





    }


}
