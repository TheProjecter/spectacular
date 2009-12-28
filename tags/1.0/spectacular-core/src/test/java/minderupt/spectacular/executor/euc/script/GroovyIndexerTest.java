package minderupt.spectacular.executor.euc.script;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.regex.Pattern;

import minderupt.spectacular.executor.euc.Executable;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Dec 1, 2009
 * Time: 5:44:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class GroovyIndexerTest {

    @Test
    public void testIndexGroovySteps() throws Exception {

        GroovyIndexer indexer = new GroovyIndexer();

        Map<Pattern, Executable> flowSteps = new HashMap<Pattern, Executable>();
        Map<Pattern, Executable> expectationSteps = new HashMap<Pattern, Executable>();
        List<String> scriptList = new LinkedList<String>();
        scriptList.add("src/test/groovy/SimpleSteps.groovy");

        indexer.indexScripts(scriptList, flowSteps, expectationSteps);

        assertEquals(1, flowSteps.keySet().size());





    }


}
