package minderupt.spectacular.executor;

import minderupt.spectacular.data.model.Artifact;
import minderupt.spectacular.data.model.GlobalOptions;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Oct 21, 2009
 * Time: 9:17:29 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ArtifactExecutorAgent {

    public List<ArtifactExecutionResults> executeArtifacts(final GlobalOptions options, List<Artifact> artifactList);


}
