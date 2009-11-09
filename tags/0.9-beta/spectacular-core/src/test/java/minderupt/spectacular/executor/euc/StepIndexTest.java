package minderupt.spectacular.executor.euc;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.regex.Pattern;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: dowlinmi
 * Date: Oct 29, 2009
 * Time: 12:23:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class StepIndexTest {

    @Test
    public void testSearchIndexForFlow() throws Exception {

        StepIndex stepIndex = new StepIndex();

        HashMap<Pattern, Executable> flowMap = new HashMap<Pattern, Executable>();
        Pattern pattern = Pattern.compile("This is a (.*?) matched step.");
        flowMap.put(pattern, new Executable());
        for(int i = 0 ; i < 100 ; i++) {
            Pattern somePattern = Pattern.compile("Not even close to a (.*?) match " + i);
            flowMap.put(somePattern, new Executable());
        }

        stepIndex.addToFlowIndex(flowMap);

        PatternExecutablePair exec = stepIndex.findFlowExecutable("This is a real matched step.");
        assertNotNull(exec);
        

    }


}
