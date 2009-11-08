package minderupt.spectacular.executor.euc;

import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 * User: dowlinmi
 * Date: Oct 27, 2009
 * Time: 7:14:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class Executable {

    private Method executableMethod;
    private Object executableObject;

    public Executable() {

    }

    public Executable(Method m, Object o) {
        this.executableMethod = m;
        this.executableObject = o;
    }

    public Method getExecutableMethod() {
        return executableMethod;
    }

    public void setExecutableMethod(Method executableMethod) {
        this.executableMethod = executableMethod;
    }

    public Object getExecutableObject() {
        return executableObject;
    }

    public void setExecutableObject(Object executableObject) {
        this.executableObject = executableObject;
    }

    
}
