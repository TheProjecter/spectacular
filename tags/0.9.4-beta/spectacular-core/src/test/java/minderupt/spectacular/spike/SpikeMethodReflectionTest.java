package minderupt.spectacular.spike;

import org.junit.Test;
import minderupt.spectacular.executor.euc.Context;

import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Oct 29, 2009
 * Time: 6:58:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class SpikeMethodReflectionTest {

    @Test
    public void testMethodReflectionForContext() throws Exception {


        Context context = new Context();
        Class thisClass = this.getClass();
        System.out.println("CLASS=" + thisClass.getName());

        Method[] methods = thisClass.getMethods();

        for(Method method : methods) {

            System.out.println("\t METHOD=" + method.getName());

            Class[] paramTypes = method.getParameterTypes();
            for(Class paramClazz : paramTypes) {
                System.out.println("\t\t ARG=" + paramClazz.getName());

                if(paramClazz.getName().equals("minderupt.spectacular.executor.spectacular.Context")) executeMethod(method);

            }

        }



    }

    private void executeMethod(Method method) throws Exception {
        method.invoke(this, new Context());
    }


    public void callThisMethod(Context context) {
        System.out.println("hey");
    }



}
