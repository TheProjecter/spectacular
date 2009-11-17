package minderupt.spectacular.executor.euc.script;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.regex.Pattern;

import minderupt.spectacular.executor.euc.Executable;
import minderupt.spectacular.executor.euc.JavaClassExecutable;


public class ScriptIndexerTest {

    

    @Test
    public void testIndexScripts() throws Exception {

        List<String> scriptList = new LinkedList<String>();
        Map<Pattern, Executable> flowMap = new HashMap<Pattern, Executable>();
        Map<Pattern, Executable> expectationMap = new HashMap<Pattern, Executable>();


        scriptList.add("src/test/ruby/SampleSteps.rb");
        ScriptIndexer indexer = new ScriptIndexer();
        indexer.indexScripts(scriptList, flowMap, expectationMap);

        // assertTrue(flowMap.size() > 0);


    }

}
