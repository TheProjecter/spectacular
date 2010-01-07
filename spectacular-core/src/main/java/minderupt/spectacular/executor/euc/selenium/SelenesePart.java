package minderupt.spectacular.executor.euc.selenium;

import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class SelenesePart {

    private String step;
    private List<List<String>> commands;

    public SelenesePart() {
        this.commands = new LinkedList<List<String>>();
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public void addCommand(List<String> command) {
        this.commands.add(command);
    }

    public List<List<String>> getCommands() {
        return(this.commands);
    }

    public void setCommands(List<List<String>> commands) {
        this.commands = commands;
    }
}
