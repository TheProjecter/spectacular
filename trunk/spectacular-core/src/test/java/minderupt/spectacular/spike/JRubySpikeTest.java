package minderupt.spectacular.spike;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptContext;
import java.io.FileReader;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Nov 13, 2009
 * Time: 11:06:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class JRubySpikeTest {

    @Test
    public void testGeneralEmbeddedRuby() throws Exception {

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("jruby");
        assertNotNull(engine);

        FileReader script = new FileReader("src/test/ruby/JRubySpike.rb");
        ScriptContext vars = engine.getContext();

        vars.setAttribute("yourmom", "yourmomma", ScriptContext.ENGINE_SCOPE);
        engine.eval(script, vars);
        String what = (String) engine.getContext().getAttribute("what");

        assertNotNull(what);
        assertEquals("OMG WHAT", what);

        

    }


}
