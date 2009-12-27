package minderupt.spectacular.executor.euc.selenium;

import com.thoughtworks.selenium.CommandProcessor;
import com.thoughtworks.selenium.DefaultSelenium;

/**
 *
 */
public class SpectacularSelenium extends DefaultSelenium {

    public SpectacularSelenium(String serverHost, int serverPort, String browserStartCommand, String browserURL) {
        super(serverHost, serverPort, browserStartCommand, browserURL);
    }

    public SpectacularSelenium(CommandProcessor processor) {
        super(processor);   
    }

    public void executeSeleneseCommand(String commandName, String[] args) {
        this.commandProcessor.doCommand(commandName, args);
    }

}
