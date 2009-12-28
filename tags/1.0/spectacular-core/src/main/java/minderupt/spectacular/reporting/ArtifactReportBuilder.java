package minderupt.spectacular.reporting;

import minderupt.spectacular.executor.ArtifactExecutionResults;

/**
 *
 */
public interface ArtifactReportBuilder {

    public String build(ArtifactExecutionResults results);

}
