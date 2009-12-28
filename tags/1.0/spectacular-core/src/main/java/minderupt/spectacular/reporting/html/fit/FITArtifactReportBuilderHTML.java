package minderupt.spectacular.reporting.html.fit;

import minderupt.spectacular.reporting.ArtifactReportBuilder;
import minderupt.spectacular.executor.ArtifactExecutionResults;

/**
 *
 */
public class FITArtifactReportBuilderHTML implements ArtifactReportBuilder {

    public String build(ArtifactExecutionResults results) {

        // this is easy, as the fit framework outputs its results as HTML by default
        return(results.getRawResults());

    }
}
