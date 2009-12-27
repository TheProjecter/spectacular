package minderupt.spectacular.executor.euc.selenium;

import com.thoughtworks.selenium.CommandProcessor;
import com.thoughtworks.selenium.DefaultSelenium;
import org.apache.log4j.Logger;

/**
 *
 */
public class SpectacularSelenium extends DefaultSelenium {

    private static Logger LOGGER = Logger.getLogger(SpectacularSelenium.class);


    public SpectacularSelenium(String serverHost, int serverPort, String browserStartCommand, String browserURL) {
        super(serverHost, serverPort, browserStartCommand, browserURL);

        LOGGER.info("Creating Selenium with options:");
        LOGGER.info("\tServer Host:  " + serverHost);
        LOGGER.info("\tServer Port:  " + serverPort);
        LOGGER.info("\tStartup Command:  " + browserStartCommand);
        LOGGER.info("\tBrowser URL:  " + browserURL);


    }

    public SpectacularSelenium(CommandProcessor processor) {
        super(processor);   
    }

    public void executeSeleneseCommand(String commandName, String[] args) {
        this.commandProcessor.doCommand(commandName, args);
    }

}
