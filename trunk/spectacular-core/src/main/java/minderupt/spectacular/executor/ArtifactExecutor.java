package minderupt.spectacular.executor;

import minderupt.spectacular.data.model.Artifact;
import minderupt.spectacular.data.model.GlobalOptions;

/**
 *
 */
public interface ArtifactExecutor {

    public ArtifactExecutionResults executeArtifact(final GlobalOptions globalOptions, final Artifact artifact);


}
