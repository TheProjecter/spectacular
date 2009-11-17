package minderupt.spectacular.executor.euc;

import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 * User: dowlinmi
 * Date: Nov 17, 2009
 * Time: 11:22:25 AM
 * To change this template use File | Settings | File Templates.
 */
public interface Executable {
    Method getExecutableMethod();

    void setExecutableMethod(Method executableMethod);

    Object getExecutableObject();

    void setExecutableObject(Object executableObject);
}
