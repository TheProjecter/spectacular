package minderupt.spectacular.spike;

import com.thoughtworks.selenium.CommandProcessor;
import com.thoughtworks.selenium.DefaultSelenium;

/**
 *                                   
 */
public class ExternalizedCommandProcessorSelenium extends DefaultSelenium {

    public ExternalizedCommandProcessorSelenium(CommandProcessor cp) {
        super(cp);
    }

    public ExternalizedCommandProcessorSelenium(String serverHost, int port, String startCommand, String browserUrl) {
        super(serverHost, port, startCommand, browserUrl);
    }

    public String executeCommand(String commandName, String...params) {
        return(this.commandProcessor.doCommand(commandName, params));
    }

}
