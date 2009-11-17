package minderupt.spectacular.executor.euc;

import java.lang.reflect.Method;

/**
 *
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
