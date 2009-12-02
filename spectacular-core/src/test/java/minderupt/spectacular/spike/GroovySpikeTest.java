package minderupt.spectacular.spike;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import minderupt.spectacular.executor.euc.script.JavaCallback;
import groovy.lang.*;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Dec 1, 2009
 * Time: 4:29:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class GroovySpikeTest {

    @Test
    public void testInjectCallbackInScript() throws Exception {


        JavaCallback jcb = new JavaCallback();
        GroovyClassLoader groovyLoader = new GroovyClassLoader(getClass().getClassLoader());
        Class groovyClass = groovyLoader.parseClass(new File("src/test/groovy/SpikeInjectClassbackInScript.groovy"));
        GroovyObject groovyObject = (GroovyObject) groovyClass.newInstance();

        groovyObject.setProperty("javaCallback", jcb);
        groovyObject.invokeMethod("go", null);


    }


    @Test
    public void testUseGroovyClosure() throws Exception {

        GroovyShell shell = new GroovyShell();

        String closureStr = "def someClosure = { blah -> \n" +
                "println blah; \n" +
                "javaCallback.getMessage(); \n" +
                "}; \n" +
                "return someClosure";

        Object someObject = shell.evaluate(closureStr);
        assertNotNull(someObject);

        Closure closure = (Closure) someObject;
        closure.setProperty("javaCallback", new JavaCallback());
        closure.call("HEEEEYYYY");


    }


}
