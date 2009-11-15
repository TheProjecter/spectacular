package minderupt.spectacular.reporting;

import minderupt.spectacular.executor.ArtifactExecutionResults;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Nov 7, 2009
 * Time: 1:11:15 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ArtifactReportBuilder {

    public String build(ArtifactExecutionResults results);

}
