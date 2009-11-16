package minderupt.spectacular.executor.euc.script;

import org.junit.Test;

import java.util.List;
import java.util.LinkedList;


public class ScriptIndexerTest {

    

    @Test
    public void testIndexScripts() throws Exception {

        List<String> scriptList = new LinkedList<String>();
        scriptList.add("");
        ScriptIndexer indexer = new ScriptIndexer();
        indexer.indexScripts(scriptList);

    }

}
