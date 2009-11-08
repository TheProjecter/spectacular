package minderupt.spectacular.executor.euc;

import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: dowlinmi
 * Date: Oct 29, 2009
 * Time: 12:32:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class PatternExecutablePair {

    private Pattern pattern;
    private Executable executable;

    public PatternExecutablePair(Pattern pattern, Executable executable) {
        this.pattern = pattern;
        this.executable = executable;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public Executable getExecutable() {
        return executable;
    }

    public void setExecutable(Executable executable) {
        this.executable = executable;
    }
}
