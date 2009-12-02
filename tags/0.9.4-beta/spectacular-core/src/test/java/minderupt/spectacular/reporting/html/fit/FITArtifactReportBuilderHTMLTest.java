package minderupt.spectacular.reporting.html.fit;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import minderupt.spectacular.executor.ArtifactExecutionResults;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Nov 8, 2009
 * Time: 10:46:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class FITArtifactReportBuilderHTMLTest {

    @Test
    public void testBasicFITReport() throws Exception {

        ArtifactExecutionResults results = new ArtifactExecutionResults();
        results.setRawResults("<table>rawresults</table>");

        FITArtifactReportBuilderHTML reportBuilder = new FITArtifactReportBuilderHTML();
        String resultBody = reportBuilder.build(results);

        assertNotNull(resultBody);
        assertEquals("<table>rawresults</table>", resultBody);


    }

}
