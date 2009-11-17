package minderupt.spectacular.executor.euc.script;

import minderupt.spectacular.executor.euc.Context;
import minderupt.spectacular.executor.euc.Executable;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.Invocable;

/**
 *
 */
public class RubyExecutable implements Executable {


    private static Logger LOGGER = Logger.getLogger(RubyExecutable.class);


    public void executeRubyClosure(Context context, Object...arguments) {

        // get JRuby up
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("jruby");

        Invocable invoke = (Invocable) engine;

        try {
            invoke.invokeMethod(getExecutableObject(), "executeBlock", arguments);
        } catch(Exception e) {
            LOGGER.fatal("Error:  ", e);
        }


        
        
    }


    public Method getExecutableMethod() {

        Method execMethod = null;
        try {
            execMethod = getClass().getMethod("executeRubyClosure", null);
        } catch(NoSuchMethodException nsme) {
            LOGGER.fatal("Unable to extract method from my own class?", nsme);
        }

        return(execMethod);
    }

    public void setExecutableMethod(Method executableMethod) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Object getExecutableObject() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setExecutableObject(Object executableObject) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
