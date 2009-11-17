package minderupt.spectacular.executor.euc.script;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 * User: dowlinmi
 * Date: Nov 17, 2009
 * Time: 11:58:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class RubyExecutableTest {

    @Test
    public void testGetExecutableMethod() throws Exception {

        RubyExecutable exec = new RubyExecutable();
        Method method = exec.getExecutableMethod();
        assertNotNull(method);
        assertEquals("executeRubyClosure", method.getName());

    }

    @Test
    public void testGetExecutableObject() throws Exception {

        RubyExecutable exec = new RubyExecutable();
        Object object = exec.getExecutableObject();
        assertNotNull(object);
        assertEquals("minderupt.spectacular.executor.euc.script.RubyExecuable", object.getClass().getName());

    }

}
