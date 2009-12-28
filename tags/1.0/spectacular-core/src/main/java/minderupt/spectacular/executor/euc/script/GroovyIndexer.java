package minderupt.spectacular.executor.euc.script;

import minderupt.spectacular.executor.euc.Executable;
import minderupt.spectacular.executor.euc.util.PatternUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.io.*;

import org.apache.log4j.Logger;
import groovy.lang.GroovyShell;
import groovy.lang.Closure;


public class GroovyIndexer {

    public static final String GROOVY_STEP_DEFINITIONS = "Spectacular/StepLoader.groovy";
    private static final String LINESEP = System.getProperty("line.separator");
    private static Logger LOGGER = Logger.getLogger(GroovyIndexer.class);

    public void indexScripts(List<String> scriptList, Map<Pattern, Executable> flowMap, Map<Pattern, Executable> expectationMap) {

        String stepLoaderDefinitions = null;
        try {
            stepLoaderDefinitions = loadStepLoaderDefinitions();
        } catch(Exception e) {
            LOGGER.error("Unable to load the Groovy step loader definitions", e);
            return;
        }

        // Load the groovy interpreter
        if(LOGGER.isDebugEnabled()) LOGGER.debug("Creating groovy interpreter");
        GroovyShell groovyShell = new GroovyShell();


        // for each groovy euc script given, load and combine with the step definition
        // then, evaluate to index
        JavaCallback javaCallback = new JavaCallback();
        for(String scriptPath : scriptList) {

            if(LOGGER.isDebugEnabled()) LOGGER.debug("Loading script:  " + scriptPath);

            try {

                String scriptBody = loadGroovyScript(scriptPath);
                scriptBody = stepLoaderDefinitions + LINESEP + scriptBody;

                if(LOGGER.isDebugEnabled()) LOGGER.debug("SCRIPT:  \n\n" + scriptBody);

                groovyShell.setProperty("javaCallback", javaCallback);
                groovyShell.evaluate(scriptBody);

            } catch(Exception e) {
                LOGGER.error("Unable to evaluate script:  " + scriptPath, e);
                continue;
            }




        }


        // index the steps
        Map<String, Object> flowSteps = javaCallback.getFlowBlocks();
        Map<String, Object> expectSteps = javaCallback.getExpectationBlocks();

        // Flows!
        Set<String> flowSet = flowSteps.keySet();
        for(String flow : flowSet) {

            GroovyExecutable groovyExec = new GroovyExecutable();

            Pattern stepPattern = PatternUtils.convertToPattern(flow);
            Closure groovyClosure = (Closure) flowSteps.get(flow);

            groovyExec.setClosure(groovyClosure);
            flowMap.put(stepPattern, groovyExec);

        }

        // Expectations!
        Set<String> expectSet = expectSteps.keySet();
        for(String expect : expectSet) {

            GroovyExecutable groovyExec = new GroovyExecutable();

            Pattern stepPattern = PatternUtils.convertToPattern(expect);
            Closure groovyClosure = (Closure) expectSteps.get(expect);

            groovyExec.setClosure(groovyClosure);
            expectationMap.put(stepPattern, groovyExec);

        }



        
    }

    private String loadGroovyScript(String scriptPath) throws Exception {

        StringBuilder body = new StringBuilder();
        File scriptFile = new File(scriptPath);
        if(!scriptFile.exists())
            throw(new FileNotFoundException("Script file not found:  " + scriptPath));

        BufferedReader reader = new BufferedReader(new FileReader(scriptFile));
        String line = reader.readLine();
        while(line != null) {
            body.append(line);
            body.append(LINESEP);
            line = reader.readLine();
        }

        reader.close();

        return(body.toString());


    }

    private String loadStepLoaderDefinitions() throws Exception {

        StringBuilder script = new StringBuilder();

        ClassLoader classLoader = GroovyIndexer.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(GROOVY_STEP_DEFINITIONS);
        if (inputStream != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = reader.readLine();
            while (line != null) {
                script.append(line);
                script.append(LINESEP);
                line = reader.readLine();
            }

            reader.close();
        }

        return (script.toString());
    }


    


}
