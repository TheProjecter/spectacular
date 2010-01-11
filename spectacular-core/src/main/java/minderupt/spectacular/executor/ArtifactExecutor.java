package minderupt.spectacular.executor;

import minderupt.spectacular.data.model.Artifact;
import minderupt.spectacular.data.model.GlobalOptions;

/**
 *
 */
public interface ArtifactExecutor {

    public static final String RESULT_SUCCESS = "(SUCCESS)";
    public static final String RESULT_PENDING = "(PENDING)";
    public static final String RESULT_FAIL = "(FAIL)";
    public static final String RESULT_NOT_PERFORMED = "(NOT PERFORMED)";


    public ArtifactExecutionResults executeArtifact(final GlobalOptions globalOptions, final Artifact artifact);


}
