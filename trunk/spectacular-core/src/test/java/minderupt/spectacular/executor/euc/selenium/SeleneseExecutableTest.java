package minderupt.spectacular.executor.euc.selenium;

import minderupt.spectacular.executor.euc.Context;
import minderupt.spectacular.executor.euc.script.RubyExecutable;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 *
 */
public class SeleneseExecutableTest {

    @Test
    public void testSimpleFlowStep() throws Exception {

        // set up selenese part
        List<String> commandOpen = new LinkedList<String>();
        commandOpen.add("open");
        commandOpen.add("/some/url");

        List<String> commandClick = new LinkedList<String>();
        commandClick.add("click");
        commandClick.add("id=someButtonName");

        SelenesePart part = new SelenesePart();
        part.setStep("User wants to do something useful");
        part.getCommands().add(commandOpen);
        part.getCommands().add(commandClick);

        // mock out selenium
        SpectacularSelenium selenium = mock(SpectacularSelenium.class);
        SeleneseExecutable exec = new SeleneseExecutable(part);
        exec.setSeleniumInstance(selenium);

        exec.executeSelenese(new Context(), null);

        verify(selenium).executeSeleneseCommand("open", new String[]{"/some/url"});
        verify(selenium).executeSeleneseCommand("click", new String[]{"id=someButtonName"});


    }


    @Test
    public void testGetExecutableMethod() throws Exception {

        SeleneseExecutable exec = new SeleneseExecutable(null);
        Method method = exec.getExecutableMethod();
        assertNotNull(method);
        assertEquals("executeSelenese", method.getName());

    }

    @Test
    public void testGetExecutableObject() throws Exception {

        SeleneseExecutable exec = new SeleneseExecutable(null);
        Object object = exec.getExecutableObject();
        assertNotNull(object);
        assertEquals("minderupt.spectacular.executor.euc.selenium.SeleneseExecutable", object.getClass().getName());

    }


}
