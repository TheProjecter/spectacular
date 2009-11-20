package minderupt.spectacular.executor;

import minderupt.spectacular.data.model.Artifact;
import minderupt.spectacular.data.model.GlobalOptions;

import java.util.List;

/**
 *
 */
public interface ArtifactExecutorAgent {

    public List<ArtifactExecutionResults> executeArtifacts(final GlobalOptions options, List<Artifact> artifactList);


}
