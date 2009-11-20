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
    private Object closure;


    public void executeRubyClosure(Context context, Object...arguments) {

        // get JRuby up
        if(LOGGER.isInfoEnabled()) LOGGER.info("Setting up jruby environment for step exec...");
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("jruby");
        

        Invocable invoke = (Invocable) engine;

        try {
            invoke.invokeMethod(this.closure, "setContext", context);
            invoke.invokeMethod(this.closure, "executeBlock", arguments);
        } catch(Exception e) {
            LOGGER.fatal("Error invoking Ruby", e);
            throw(new RuntimeException("Error invoking Ruby", e));
        }


        
        
    }


    public Method getExecutableMethod() {

        Method execMethod = null;
        try {
            execMethod = getClass().getMethod("executeRubyClosure", Context.class, Object[].class);
        } catch(NoSuchMethodException nsme) {
            LOGGER.fatal("Unable to extract method from my own class?", nsme);
        }

        return(execMethod);
    }

    public void setExecutableMethod(Method executableMethod) {
        throw(new UnsupportedOperationException());
    }

    public Object getExecutableObject() {
        return(this);  
    }

    public void setExecutableObject(Object executableObject) {
        throw(new UnsupportedOperationException());
    }

    public void setClosure(Object closure) {
        this.closure = closure;
    }
}
