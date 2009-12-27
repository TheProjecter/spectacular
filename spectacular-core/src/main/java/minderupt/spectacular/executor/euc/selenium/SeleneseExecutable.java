package minderupt.spectacular.executor.euc.selenium;

import com.thoughtworks.selenium.Selenium;
import minderupt.spectacular.executor.euc.Context;
import minderupt.spectacular.executor.euc.Executable;
import minderupt.spectacular.executor.euc.selenium.SelenesePart;
import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;
import java.util.List;

/**
 *
 */
public class SeleneseExecutable implements Executable {

    private static Logger LOGGER = Logger.getLogger(SeleneseExecutable.class);
    private static final String SELENIUM_KEY = "selenium";
    private SelenesePart selenesePart = null;
    private SpectacularSelenium seleniumInstance;


    public void executeSelenese(Context context, Object...params) {

        // get selenium, or create a new one
        SpectacularSelenium selenium = (SpectacularSelenium)context.get(SELENIUM_KEY);
        if(selenium == null) {
            if(LOGGER.isDebugEnabled()) LOGGER.debug("Creating new selenium instance.");
            selenium = createSeleniumInstance(context);
            selenium.start();
            context.put(SELENIUM_KEY, selenium);
        }

        for(List<String> command : selenesePart.getCommands()) {


            String[] commandArr = new String[command.size()];
            System.arraycopy(command.toArray(), 0, commandArr, 0, command.size());



            String commandName = commandArr[0];
            String[] commandParams = (String[]) ArrayUtils.subarray(commandArr, 1, commandArr.length);

            if(LOGGER.isDebugEnabled()) LOGGER.debug("Executing command:  " + commandName + " " + ArrayUtils.toString(commandParams));
            selenium.executeSeleneseCommand(commandName, commandParams);

        }


    }

    private SpectacularSelenium createSeleniumInstance(Context context) {

        if(this.seleniumInstance != null) {
            return(this.seleniumInstance);
        }

        return(null);

    }

    public void setSeleniumInstance(SpectacularSelenium ss) {
        this.seleniumInstance = ss;
    }


    public SeleneseExecutable(SelenesePart part) {
        this.selenesePart = part;
    }


    public SelenesePart getSelenesePart() {
        return(selenesePart);
    }


    @Override
    public Method getExecutableMethod() {
        Method execMethod = null;
        try {
            execMethod = getClass().getMethod("executeSelenese", Context.class, Object[].class);
        } catch(NoSuchMethodException nsme) {
            LOGGER.fatal("Unable to extract method from my own class?", nsme);
        }

        return(execMethod);
    }

    @Override
    public void setExecutableMethod(Method executableMethod) {
        throw(new UnsupportedOperationException());
    }

    @Override
    public Object getExecutableObject() {
        return(this);
    }

    @Override
    public void setExecutableObject(Object executableObject) {
        throw(new UnsupportedOperationException());
    }
}
