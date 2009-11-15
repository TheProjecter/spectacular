package minderupt.spectacular.reporting.html.fit;

import minderupt.spectacular.reporting.ArtifactReportBuilder;
import minderupt.spectacular.executor.ArtifactExecutionResults;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Nov 8, 2009
 * Time: 10:44:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class FITArtifactReportBuilderHTML implements ArtifactReportBuilder {

    public String build(ArtifactExecutionResults results) {

        // this is easy, as the fit framework outputs its results as HTML by default
        return(results.getRawResults());

    }
}
