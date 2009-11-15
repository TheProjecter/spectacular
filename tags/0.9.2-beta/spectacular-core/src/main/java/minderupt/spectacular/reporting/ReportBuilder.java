package minderupt.spectacular.reporting;

import minderupt.spectacular.data.model.Document;
import minderupt.spectacular.data.model.Report;
import minderupt.spectacular.executor.ArtifactExecutionResults;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Oct 21, 2009
 * Time: 11:37:31 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ReportBuilder {

    public Report build(Document document, List<ArtifactExecutionResults> results);

}
