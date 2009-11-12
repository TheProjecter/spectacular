package minderupt.spectacular.executor.euc;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Oct 22, 2009
 * Time: 9:59:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class StepIndex {

    public Map<Pattern, Executable> flowIndex = new HashMap<Pattern, Executable>();
    public Map<Pattern, Executable> expectationIndex = new HashMap<Pattern, Executable>();

    public void addToFlowIndex(Map<Pattern, Executable> flowSteps) {
        flowIndex.putAll(flowSteps);
    }

    public void addToExpectationIndex(Map<Pattern, Executable> expectSteps) {
        expectationIndex.putAll(expectSteps);
    }

    public PatternExecutablePair findFlowExecutable(String spec) {

        // take spec and iterate through flow steps for a match
        Set<Pattern> patternSet = flowIndex.keySet();
        for(Pattern pattern : patternSet) {

            Matcher matcher = pattern.matcher(spec);
            if(matcher.matches()) {
                return(new PatternExecutablePair(pattern, flowIndex.get(pattern)));
            }

        }

        return(null);


    }

    public PatternExecutablePair findExpectationExecutable(String spec) {

        // take spec and iterate through flow steps for a match
        Set<Pattern> patternSet = expectationIndex.keySet();
        for(Pattern pattern : patternSet) {

            Matcher matcher = pattern.matcher(spec);
            if(matcher.matches()) {
                return(new PatternExecutablePair(pattern, expectationIndex.get(pattern)));
            }

        }

        return(null);


    }




}
