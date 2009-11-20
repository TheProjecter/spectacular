package minderupt.spectacular.reporting.html.bdd;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import minderupt.spectacular.executor.ArtifactExecutionResults;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Nov 8, 2009
 * Time: 10:38:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class JBehaveArtifactReportBuilderHTMLTest {

    @Test
    public void testCreateBasicPositiveReport() throws Exception {

        JBehaveArtifactReportBuilderHTML reportBuilder = new JBehaveArtifactReportBuilderHTML();

        // set up results
        ArtifactExecutionResults results = new ArtifactExecutionResults();
        results.put(0, 0, "minderupt.spectacular.spine.bdd.BDDTests");
        results.put(1, 0, "Given a user with login name (PASSED)\nWhen we do something (PASSED)\nThen we do right (PASSED)");

        String expectedResults = "<table border=\"1\">\n" +
                "<tr><td>minderupt.spectacular.spine.bdd.BDDTests</td></tr>\n" +
                "<tr><td>Given a user with login name (PASSED)<br />" +
                "When we do something (PASSED)<br />" +
                "Then we do right (PASSED)</td></tr>\n" +
                "</table>\n";

        String report = reportBuilder.build(results);
        assertNotNull(report);
        assertEquals(expectedResults, report);

    }


}
