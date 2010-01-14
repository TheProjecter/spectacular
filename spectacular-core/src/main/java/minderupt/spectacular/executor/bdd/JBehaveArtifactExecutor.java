package minderupt.spectacular.executor.bdd;

import minderupt.spectacular.data.model.Artifact;
import minderupt.spectacular.data.model.GlobalOptions;
import minderupt.spectacular.executor.ArtifactExecutionResults;
import minderupt.spectacular.executor.ArtifactExecutor;
import minderupt.spectacular.executor.bdd.config.ComponoJBehaveConfiguration;
import org.apache.log4j.Logger;
import org.jbehave.scenario.Configuration;
import org.jbehave.scenario.ScenarioRunner;
import org.jbehave.scenario.definition.ScenarioDefinition;
import org.jbehave.scenario.definition.StoryDefinition;
import org.jbehave.scenario.parser.PatternScenarioParser;
import org.jbehave.scenario.steps.CandidateSteps;

import java.util.List;

/**
 *
 */
public class JBehaveArtifactExecutor implements ArtifactExecutor {

    private static Logger LOGGER = Logger.getLogger(JBehaveArtifactExecutor.class);

    public ArtifactExecutionResults executeArtifact(final GlobalOptions globalOptions, final Artifact artifact) {

        ArtifactExecutionResults results = new ArtifactExecutionResults();
        results.setOriginalArtifact(artifact);

        // header should have step class
        List<String> headers = artifact.getHeaders();
        String stepClass = headers.get(0);

        // load step class
        if(LOGGER.isInfoEnabled()) LOGGER.info("Loading step class:  " + stepClass);
        CandidateSteps steps = null;
        try {
            steps = (CandidateSteps) Class.forName(stepClass).newInstance();
        } catch(Exception e) {
            LOGGER.error("Unable to load step class: " + stepClass, e);
            results.put(0, 0, "Unable to load step class:  " + stepClass + " " + ArtifactExecutor.RESULT_FAIL);
            results.put(1, 0, artifact.getRawArtifact());
            results.setPass(false);
            results.printTableContents();
            return results;
        }

        // parse story
        if(LOGGER.isInfoEnabled()) LOGGER.info("Parsing Scenario(s).");
        String story = artifact.getTableContent().get(0).get(0);
        if(LOGGER.isInfoEnabled()) LOGGER.info("Raw Story:  " + story);

        PatternScenarioParser parser = new PatternScenarioParser();
        StoryDefinition storyDefinition = parser.defineStoryFrom(story);
        if(LOGGER.isInfoEnabled()) logStoryDefinition(storyDefinition);

        // set up configuration
        Configuration config = new ComponoJBehaveConfiguration(results);
        

        ScenarioRunner runner = new ScenarioRunner();
        try {
            runner.run(storyDefinition, config, steps);
        } catch(Throwable e) {
            LOGGER.error("Exception while executing story.", e);
            results.setPass(false);
            results.setRawResults(story + "Exception while executing story:  " + e);

        }

        // ok to go?
        results.put(0, 0, stepClass);
        // results.put(1, 0, story);
        


        return results;  
    }

    private void logStoryDefinition(StoryDefinition storyDefinition) {

        List<ScenarioDefinition> scenarioDefinitions = storyDefinition.getScenarios();
        for(ScenarioDefinition scenarioDefinition : scenarioDefinitions) {
            LOGGER.info("\t TITLE:  " + scenarioDefinition.getTitle());
            List<String> stepsList = scenarioDefinition.getSteps();
            for(String step : stepsList) {
                LOGGER.info("\t\t STEP:  " + step);
            }
        }

    }


}
