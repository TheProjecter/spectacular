package minderupt.spectacular.spike;

import org.apache.log4j.Logger;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.Invocable;
import javax.script.ScriptException;


/**
 * Created by IntelliJ IDEA.
 * User: dowlinmi
 * Date: Nov 16, 2009
 * Time: 9:20:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class SampleIndexer {

    private static Logger LOGGER = Logger.getLogger(SampleIndexer.class);

    public void indexPackage(String packageName, Object block) {
        LOGGER.info("PACKAGE:  " + packageName);

        LOGGER.info("Creating JRuby Script Engine");
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("jruby");

        LOGGER.info("Can I execute this block?");
        Invocable blockInvoke = (Invocable) engine;

        LOGGER.info("Attempting to execute block...");
        try {
            blockInvoke.invokeMethod(block, "executeBlock", "Something");
        } catch(Exception se) {
            LOGGER.error("Unable to exec block", se);
        }
        


    }


}
