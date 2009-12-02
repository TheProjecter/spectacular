package minderupt.spectacular.executor.euc.script;

import minderupt.spectacular.executor.euc.Executable;
import minderupt.spectacular.executor.euc.Context;

import java.lang.reflect.Method;

import groovy.lang.Closure;
import org.apache.log4j.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Dec 1, 2009
 * Time: 5:38:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class GroovyExecutable implements Executable {

    private static Logger LOGGER = Logger.getLogger(GroovyExecutable.class);
    private Closure closure;


    public void executeGroovyClosure(Context context, Object...params) {

        try {
            this.closure.setProperty("context", context);
            this.closure.call(params);
        } catch(Exception e) {
            LOGGER.fatal("Unable to execute groovy closure", e);
            throw(new RuntimeException("Error invoking Groovy closure.", e));
        }


    }



    public Method getExecutableMethod() {

        Method execMethod = null;
        try {
            execMethod = getClass().getMethod("executeGroovyClosure", Context.class, Object[].class);
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

    public void setClosure(Closure closure) {
        this.closure = closure;
    }



}
