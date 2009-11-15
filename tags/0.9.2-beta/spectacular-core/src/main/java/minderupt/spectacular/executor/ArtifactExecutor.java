package minderupt.spectacular.executor;

import minderupt.spectacular.data.model.Artifact;
import minderupt.spectacular.data.model.GlobalOptions;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Oct 16, 2009
 * Time: 5:31:10 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ArtifactExecutor {

    public ArtifactExecutionResults executeArtifact(final GlobalOptions globalOptions, final Artifact artifact);


}
