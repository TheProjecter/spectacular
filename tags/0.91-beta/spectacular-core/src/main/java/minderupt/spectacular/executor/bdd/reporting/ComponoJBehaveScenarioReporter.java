package minderupt.spectacular.executor.bdd.reporting;

import minderupt.spectacular.executor.ArtifactExecutionResults;
import org.jbehave.scenario.definition.Blurb;
import org.jbehave.scenario.reporters.ScenarioReporter;

/**
 * Created by IntelliJ IDEA.
 * User: dowlinmi
 * Date: Oct 28, 2009
 * Time: 8:36:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class ComponoJBehaveScenarioReporter implements ScenarioReporter {

    private ArtifactExecutionResults results;
    private boolean pendingOrFailure = false;

    public ComponoJBehaveScenarioReporter(ArtifactExecutionResults results) {
        this.results = results;
        this.results.setRawResults("");
    }

    public void beforeScenario(String s) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void afterScenario() {

        if(this.pendingOrFailure) {
            this.results.setPass(false);
        } else {
            this.results.setPass(true);
        }


    }

    public void successful(String s) {

        String soFar = results.get(1, 0);
        if(soFar == null) soFar = "";

        soFar = soFar + s + " (PASSED)\n";
        results.put(1, 0, soFar);


    }

    public void pending(String s) {
        String soFar = results.get(1, 0);
        if(soFar == null) soFar = "";

        soFar = soFar + s + " (PENDING)\n";
        results.put(1, 0, soFar);
        this.pendingOrFailure = true;

    }

    public void notPerformed(String s) {
        String soFar = results.get(1, 0);
        if(soFar == null) soFar = "";

        soFar = soFar + s + " (NOT PERFORMED)\n";
        results.put(1, 0, soFar);
        this.pendingOrFailure = true;
    }

    public void failed(String s, Throwable throwable) {
        String soFar = results.get(1, 0);
        if(soFar == null) soFar = "";

        soFar = soFar + s + " (FAILED)\n";
        results.put(1, 0, soFar);
        this.pendingOrFailure = true;
    }

    public void beforeStory(Blurb blurb) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void afterStory() {


    }

    public ArtifactExecutionResults getResults() {
        return results;
    }

   
}
