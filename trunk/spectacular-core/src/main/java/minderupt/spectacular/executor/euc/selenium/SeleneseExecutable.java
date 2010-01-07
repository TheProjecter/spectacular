package minderupt.spectacular.executor.euc.selenium;

import com.thoughtworks.selenium.Selenium;
import minderupt.spectacular.data.model.GlobalOptions;
import minderupt.spectacular.executor.euc.Context;
import minderupt.spectacular.executor.euc.Executable;
import minderupt.spectacular.executor.euc.selenium.SelenesePart;
import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class SeleneseExecutable implements Executable {

    private static Logger LOGGER = Logger.getLogger(SeleneseExecutable.class);
    private static final String SELENIUM_KEY = "selenium";
    private static Pattern varNamePattern = Pattern.compile("\\$\\d");

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

        substituteCommandVariables(selenesePart, params);


        for(List<String> command : selenesePart.getCommands()) {


            String[] commandArr = new String[command.size()];
            System.arraycopy(command.toArray(), 0, commandArr, 0, command.size());



            String commandName = commandArr[0];
            String[] commandParams = (String[]) ArrayUtils.subarray(commandArr, 1, commandArr.length);


            if(LOGGER.isDebugEnabled()) LOGGER.debug("Executing command:  " + commandName + " " + ArrayUtils.toString(commandParams));
            selenium.executeSeleneseCommand(commandName, commandParams);

        }


    }

    /**
     * Substitutes the $0, $1 variables in the command params with vars grabbed during regex
     * @param part Selenese part
     * @param params Values from the regex
     */
    private void substituteCommandVariables(SelenesePart part, Object[] params) {

        if(params == null) return;


        // build map of var name to params
        int varId = 0;
        Map<Integer, String> varMap = new HashMap<Integer, String>();
        for(Object obj : params) {

            String str = (String) obj;
            varMap.put(varId, str);
            varId++;

        }


        List<List<String>> commands = part.getCommands();
        List<List<String>> subCommands = new LinkedList<List<String>>();
        for(List<String> command : commands) {

           List<String> singleCommand = new LinkedList<String>();
           for(String commandPart : command) {

               // do replace
               String newCommandPart = replaceParameters(commandPart, varMap);
               singleCommand.add(newCommandPart);

            }

            subCommands.add(singleCommand);

        }

        part.setCommands(subCommands);

    }

    private String replaceParameters(String commandPart, Map<Integer, String> varMap) {

        Set<Integer> keys = varMap.keySet();
        for(Integer varNum : keys) {
            String replaceString = "\\$" + varNum.intValue();
            commandPart = commandPart.replaceAll(replaceString, varMap.get(varNum));
        }

        return(commandPart);

    }

    private SpectacularSelenium createSeleniumInstance(Context context) {

        if(this.seleniumInstance != null) {
            return(this.seleniumInstance);
        }

        GlobalOptions options = (GlobalOptions) context.get(Context.GLOBAL_OPTIONS_KEY);
        this.seleniumInstance = new SpectacularSelenium(options.getSeleniumRCHost(), options.getSeleniumRCPort(), options.getSeleniumRCStartupCommand(), options.getSeleniumRCInitialUrl());

        return(this.seleniumInstance);

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
