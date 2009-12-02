package minderupt.spectacular.executor.euc.script;

import minderupt.spectacular.executor.euc.Executable;

import java.lang.reflect.Method;

import groovy.lang.Closure;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Dec 1, 2009
 * Time: 5:38:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class GroovyExecutable implements Executable {

    private Closure closure;


    public Method getExecutableMethod() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
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

    public void setClosure(Closure closure) {
        this.closure = closure;
    }



}
