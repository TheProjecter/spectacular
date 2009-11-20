package minderupt.spectacular.executor.euc;

import java.util.regex.Pattern;

/**
 *
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
