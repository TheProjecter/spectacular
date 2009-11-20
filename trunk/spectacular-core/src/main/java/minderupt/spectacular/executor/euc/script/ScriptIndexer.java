package minderupt.spectacular.executor.euc.script;

import org.apache.log4j.Logger;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.Invocable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import minderupt.spectacular.executor.euc.Executable;
import minderupt.spectacular.executor.euc.util.PatternUtils;

/**
 *
 */
public class ScriptIndexer {


    private static final String RUBY_SCRIPT_STEP_LOADER = "Spectacular/StepLoader.rb";
    private static Logger LOGGER = Logger.getLogger(ScriptIndexer.class);


    public void indexScripts(List<String> scriptList, Map<Pattern, Executable> flowMap, Map<Pattern, Executable> expectationMap) {

        String stepLoaderScript = null;
        try {
            if (LOGGER.isInfoEnabled()) LOGGER.info("Loading StepLoader script");
            stepLoaderScript = loadStepLoaderScript();
            if (LOGGER.isInfoEnabled()) LOGGER.info("StepLoader Script:  " + stepLoaderScript);
        } catch (Exception e) {
            LOGGER.fatal("Unable to load StepLoader", e);
            return;
        }

        // get StepLoader
        if (LOGGER.isInfoEnabled()) LOGGER.info("Loading JRUBY environment");
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("jruby");

        if (scriptEngine == null) {
            LOGGER.fatal("Unable to load JRUBY Script Engine");
            return;
        }


        if (LOGGER.isInfoEnabled()) LOGGER.info("Evaluating StepLoader");
        Object stepLoader = null;
        try {
            stepLoader = scriptEngine.eval(stepLoaderScript);
        } catch (Exception e) {
            LOGGER.fatal("Unable to load StepLoader into engine", e);
            return;
        }

        if (LOGGER.isInfoEnabled()) LOGGER.info("Setting Java Callback");
        JavaCallback callback = new JavaCallback();
        Invocable invoke = (Invocable) scriptEngine;
        try {
            invoke.invokeMethod(stepLoader, "setJavaCallback", callback);
        } catch (Exception e) {
            LOGGER.fatal("Unable to set java callback in Ruby script", e);
            return;
        }

        for (String script : scriptList) {

            try {
                if (LOGGER.isInfoEnabled()) LOGGER.info("Loading steps for:  " + script);
                invoke.invokeMethod(stepLoader, "loadSteps", script);
            } catch (Exception e) {
                LOGGER.error("Unable to load steps for script:  " + script, e);
            }

        }

        Map<String, Object> flowBlocks = callback.getFlowBlocks();
        Map<String, Object> expectationBlocks = callback.getExpectationBlocks();

        // do flows first

        Set<String> flowSet = flowBlocks.keySet();
        for (String flow : flowSet) {

            RubyExecutable rubyExec = new RubyExecutable();

            Pattern pattern = PatternUtils.convertToPattern(flow);
            Object closure = flowBlocks.get(flow);
            rubyExec.setClosure(closure);

            flowMap.put(pattern, rubyExec);

        }

        // expectations next
        Set<String> expectationSet = expectationBlocks.keySet();
        for (String expect : expectationSet) {

            RubyExecutable rubyExec = new RubyExecutable();

            Pattern pattern = PatternUtils.convertToPattern(expect);
            Object closure = flowBlocks.get(expect);
            rubyExec.setExecutableObject(closure);

            expectationMap.put(pattern, rubyExec);

        }


    }


    private String loadStepLoaderScript() throws Exception {

        StringBuilder script = new StringBuilder();

        ClassLoader classLoader = ScriptIndexer.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(RUBY_SCRIPT_STEP_LOADER);
        if (inputStream != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = reader.readLine();
            while (line != null) {
                script.append(line);
                script.append(System.getProperty("line.separator"));
                line = reader.readLine();
            }

            reader.close();
        }

        return (script.toString());
    }


}
