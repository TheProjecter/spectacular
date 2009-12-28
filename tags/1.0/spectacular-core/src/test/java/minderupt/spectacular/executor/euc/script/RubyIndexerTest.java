package minderupt.spectacular.executor.euc.script;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.regex.Pattern;

import minderupt.spectacular.executor.euc.Executable;


public class RubyIndexerTest {

    

    @Test
    public void testIndexScripts() throws Exception {

        List<String> scriptList = new LinkedList<String>();
        Map<Pattern, Executable> flowMap = new HashMap<Pattern, Executable>();
        Map<Pattern, Executable> expectationMap = new HashMap<Pattern, Executable>();


        scriptList.add("src/test/ruby/SampleSteps.rb");
        RubyIndexer indexer = new RubyIndexer();
        indexer.indexScripts(scriptList, flowMap, expectationMap);

        assertTrue(flowMap.size() == 1);


    }

}
