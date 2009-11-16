package minderupt.spectacular.executor.euc.script;

import org.apache.log4j.Logger;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import java.util.List;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by IntelliJ IDEA.
 * User: dowlinmi
 * Date: Nov 16, 2009
 * Time: 11:00:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class ScriptIndexer {


    private static final String RUBY_SCRIPT_STEP_LOADER = "";
    private static Logger LOGGER = Logger.getLogger(ScriptIndexer.class);


    public void indexScripts(List<String> scriptList) {

        String stepLoaderScript = null;
        try {
            if(LOGGER.isInfoEnabled()) LOGGER.info("Loading StepLoader script");
            stepLoaderScript = loadStepLoaderScript();
            if(LOGGER.isInfoEnabled()) LOGGER.info("StepLoader Script:  " + stepLoaderScript);
        } catch (Exception e) {
            LOGGER.fatal("Unable to load StepLoader", e);
            return;
        }

        // get StepLoader
        if(LOGGER.isInfoEnabled()) LOGGER.info("Loading JRUBY environment");
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("jruby");

        if(scriptEngine == null) {
            LOGGER.fatal("Unable to load JRUBY Script Engine");
            return;
        }

        Object stepLoader = null;
        try {
            stepLoader = scriptEngine.eval(stepLoaderScript);
        } catch(Exception e) {
            LOGGER.fatal("Unable to load StepLoader into engine", e);
            return;
        }

        


    }


    private String loadStepLoaderScript() throws Exception {

        StringBuilder script = new StringBuilder();

        ClassLoader classLoader = ScriptIndexer.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("Spectacular/StepLoader.rb");
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
