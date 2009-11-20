package minderupt.spectacular.reporting;

import minderupt.spectacular.data.model.Document;
import minderupt.spectacular.data.model.Report;
import minderupt.spectacular.executor.ArtifactExecutionResults;

import java.util.List;

/**
 *
 */
public interface ReportBuilder {

    public Report build(Document document, List<ArtifactExecutionResults> results);

}
