package minderupt.spectacular.reporting.html.euc;

import minderupt.spectacular.executor.ArtifactExecutor;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import minderupt.spectacular.executor.ArtifactExecutionResults;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Nov 7, 2009
 * Time: 1:16:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class EUCArtifactReportBuilderHTMLTest {


    @Test
    public void testBuildSuccessTable() throws Exception {

        String expectedResults = "<table border=\"1\"> \n" +
                "<tr><td>User Action</td><td>Expectation</td><td>Comments</td></tr> \n" +
                "<tr><td style=\"background-color: green\">User does 1,0 (SUCCESS)</td><td style=\"background-color: green\">User expects 1,1 (SUCCESS)</td><td>Comment at 1,2</td></tr> \n" +
                "<tr><td style=\"background-color: green\">User does 2,0 (SUCCESS)</td><td style=\"background-color: green\">User expects 2,1 (SUCCESS)</td><td>Comment at 2,2</td></tr> \n" +
                "<tr><td style=\"background-color: green\">User does 3,0 (SUCCESS)</td><td style=\"background-color: green\">User expects 3,1 (SUCCESS)</td><td>Comment at 3,2</td></tr> \n" +
                "</table>";

        // build out table result
        ArtifactExecutionResults results = new ArtifactExecutionResults();
        results.put(0, 0, "User Action");
        results.put(0, 1, "Expectation");
        results.put(0, 2, "Comments");

        results.put(1, 0, "User does 1,0 " + ArtifactExecutor.RESULT_SUCCESS);
        results.put(1, 1, "User expects 1,1 " + ArtifactExecutor.RESULT_SUCCESS);
        results.put(1, 2, "Comment at 1,2");

        results.put(2, 0, "User does 2,0 " + ArtifactExecutor.RESULT_SUCCESS);
        results.put(2, 1, "User expects 2,1 " + ArtifactExecutor.RESULT_SUCCESS);
        results.put(2, 2, "Comment at 2,2");

        results.put(3, 0, "User does 3,0 " + ArtifactExecutor.RESULT_SUCCESS);
        results.put(3, 1, "User expects 3,1 " + ArtifactExecutor.RESULT_SUCCESS);
        results.put(3, 2, "Comment at 3,2");

        EUCArtifactReportBuilderHTML reportBuilder = new EUCArtifactReportBuilderHTML();
        reportBuilder.setPassedTestColor("green");
        reportBuilder.setFailedTestColor("red");
        reportBuilder.setPendingTestColor("yellow");
        reportBuilder.setNotPerformedTestColor("white");
        String report = reportBuilder.build(results);

        assertNotNull(report);
        assertEquals(expectedResults, report);


    }

}
