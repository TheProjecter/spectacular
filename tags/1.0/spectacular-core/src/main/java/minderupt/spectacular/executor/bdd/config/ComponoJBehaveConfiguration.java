package minderupt.spectacular.executor.bdd.config;

import minderupt.spectacular.executor.ArtifactExecutionResults;
import minderupt.spectacular.executor.bdd.reporting.ComponoJBehaveScenarioReporter;
import org.jbehave.scenario.Configuration;
import org.jbehave.scenario.definition.KeyWords;
import org.jbehave.scenario.definition.ScenarioGivenWhenThenAnd;
import org.jbehave.scenario.errors.ErrorStrategy;
import org.jbehave.scenario.errors.ErrorStrategyInWhichWeTrustTheReporter;
import org.jbehave.scenario.errors.PendingErrorStrategy;
import org.jbehave.scenario.parser.ClasspathScenarioDefiner;
import org.jbehave.scenario.parser.PatternScenarioParser;
import org.jbehave.scenario.parser.ScenarioDefiner;
import org.jbehave.scenario.reporters.PrintStreamStepdocReporter;
import org.jbehave.scenario.reporters.ScenarioReporter;
import org.jbehave.scenario.reporters.StepdocReporter;
import org.jbehave.scenario.steps.DefaultStepdocGenerator;
import org.jbehave.scenario.steps.StepCreator;
import org.jbehave.scenario.steps.StepdocGenerator;
import org.jbehave.scenario.steps.UnmatchedToPendingStepCreator;

public class ComponoJBehaveConfiguration implements Configuration {

    private ArtifactExecutionResults results;

    public ComponoJBehaveConfiguration(ArtifactExecutionResults results) {
        this.results = results;
    }

    /**
     * Provides pending steps where unmatched steps exist.
     */
    public StepCreator forCreatingSteps() {
        return new UnmatchedToPendingStepCreator();
    }

    /**
     * Defines scenarios by looking for a file named after the scenario and in
     * the same package, using lower-case underscored name in place of the
     * camel-cased name - so MyScenario.java maps to my_scenario.
     */
    public ScenarioDefiner forDefiningScenarios() {
        return new ClasspathScenarioDefiner(new PatternScenarioParser(this));
    }

    /**
     * Handles errors by rethrowing them.
     * <p/>
     * <p/>
     * If there are multiple scenarios in a single story definition, this could
     * cause the story to stop after the first failing scenario.
     * <p/>
     * <p/>
     * If you want different behaviour, you might want to look at the
     * {@link ErrorStrategyInWhichWeTrustTheReporter}.
     */
    public ErrorStrategy forHandlingErrors() {
        return ErrorStrategy.RETHROW;
    }

    /**
     * Allows pending steps to pass, so that builds etc. will not fail.
     * <p/>
     * <p/>
     */
    public PendingErrorStrategy forPendingSteps() {
        return PendingErrorStrategy.PASSING;
    }

    /**
     * Reports failing or pending scenarios to System.out, while silently
     * passing scenarios.
     * <p/>
     * <p/>
     */
    public ScenarioReporter forReportingScenarios() {
        // return new PassSilentlyDecorator(new PrintStreamScenarioReporter());
        return(new ComponoJBehaveScenarioReporter(this.results));
    }

    /**
     * Provides the keywords in English
     */
    public KeyWords keywords() {
        return new ScenarioGivenWhenThenAnd();
    }

    /**
     * Generates stepdocs
     */
    public StepdocGenerator forGeneratingStepdoc() {
        return new DefaultStepdocGenerator();
    }

    /**
     * Reports stepdocs to stdout
     */
    public StepdocReporter forReportingStepdoc() {
        return new PrintStreamStepdocReporter(true);
    }

}



