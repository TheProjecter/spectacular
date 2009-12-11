package minderupt.spectacular.executor.bdd;

import static org.junit.Assert.*;
import org.junit.Test;
import minderupt.spectacular.data.model.Artifact;
import minderupt.spectacular.data.model.CommandLineGlobalOptions;
import minderupt.spectacular.executor.ArtifactExecutionResults;

import java.util.List;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: dowlinmi
 * Date: Oct 20, 2009
 * Time: 11:50:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class JBehaveArtifactExecutorTest {


    private static final String LINESEP = System.getProperty("line.separator");
    private String SCENARIO_ONE = "Scenario: I want to try to unit test this thing." + LINESEP +
                                  " " + LINESEP +
                                  "  Given some kind of setup" + LINESEP +
                                  "  And something other kind of thing to do" + LINESEP +
                                  "  When I try to execute something useful to execute" + LINESEP +
                                  "  Then I want to try and see if this thing worked or not";


    @Test
    public void testExecuteJBehaveSteps() throws Exception {

        Artifact artifact = new Artifact();

        List<String> headers = new LinkedList<String>();
        headers.add("minderupt.spectacular.executor.bdd.testone.ExampleStepClass");
        artifact.setHeaders(headers);

        List<List<String>> tableContent = new LinkedList<List<String>>();
        List<String> scenarioCell = new LinkedList<String>();
        scenarioCell.add(SCENARIO_ONE);
        tableContent.add(scenarioCell);
        artifact.setTableContent(tableContent);

        JBehaveArtifactExecutor executor = new JBehaveArtifactExecutor();
        ArtifactExecutionResults results = executor.executeArtifact(new CommandLineGlobalOptions(), artifact);
        assertNotNull(results);
        // assertEquals("Given some kind of setup (SUCCESS)", results.getRawResults());
        // assertEquals("", results.getTabularResults().get(1).get(1));



        
    }


}
