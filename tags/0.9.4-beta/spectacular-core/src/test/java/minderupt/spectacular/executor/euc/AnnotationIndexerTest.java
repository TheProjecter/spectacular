package minderupt.spectacular.executor.euc;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Map;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Created by IntelliJ IDEA.
 * User: dowlinmi
 * Date: Oct 26, 2009
 * Time: 9:49:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class AnnotationIndexerTest {

    @Test
    public void testIndexFlowPackage() throws Exception {

        Map<Pattern, Executable> flowMap = new HashMap<Pattern, Executable>();
        Map<Pattern, Executable> expectationMap = new HashMap<Pattern, Executable>();

        AnnotationIndexer indexer = new AnnotationIndexer();
        indexer.indexPackage("minderupt.spectacular.executor.euc.testone", flowMap, expectationMap);

        assertEquals(1, flowMap.keySet().size());
        assertEquals("This is my flow step", flowMap.keySet().iterator().next().pattern());
        assertEquals("myFlowMethod", flowMap.get(flowMap.keySet().iterator().next()).getExecutableMethod().getName());


    }

    @Test
    public void testIndexFlowAndExpectationPackage() throws Exception {

        Map<Pattern, Executable> flowMap = new HashMap<Pattern, Executable>();
        Map<Pattern, Executable> expectationMap = new HashMap<Pattern, Executable>();

        AnnotationIndexer indexer = new AnnotationIndexer();
        indexer.indexPackage("minderupt.spectacular.executor.euc.testtwo", flowMap, expectationMap);

        assertEquals(2, flowMap.keySet().size());
        assertEquals(2, flowMap.keySet().size());


    }

    @Test
    public void testRegexReplace() throws Exception {

        String str = "Replace ${me} with something else";
        Pattern p = Pattern.compile("\\$\\{(.*?)\\}");
        Matcher m = p.matcher(str);
        String replaced = m.replaceAll("(.*?)");

        assertNotNull(replaced);
        assertEquals("Replace (.*?) with something else", replaced);

    }


}
