package minderupt.spectacular.executor.agent;

import minderupt.spectacular.data.model.Artifact;
import minderupt.spectacular.data.model.GlobalOptions;
import minderupt.spectacular.data.model.ArtifactType;
import minderupt.spectacular.executor.ArtifactExecutionResults;
import minderupt.spectacular.executor.ArtifactExecutor;
import minderupt.spectacular.executor.ArtifactExecutorAgent;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class LinearArtifactExecutorAgent implements ArtifactExecutorAgent {

    private static Logger LOGGER = Logger.getLogger(LinearArtifactExecutorAgent.class);
    private Map<ArtifactType, ArtifactExecutor> artifactExecutors;

    public LinearArtifactExecutorAgent() {
        this.artifactExecutors = new HashMap<ArtifactType, ArtifactExecutor>();
    }


    public List<ArtifactExecutionResults> executeArtifacts(final GlobalOptions options, List<Artifact> artifactList) {

        if(LOGGER.isInfoEnabled()) LOGGER.info("Executing artifacts.");
        List<ArtifactExecutionResults> resultsList = new LinkedList<ArtifactExecutionResults>();
        for (Artifact artifact : artifactList) {

            // get executor for artifact
            ArtifactExecutor executor = this.artifactExecutors.get(artifact.getArtifactType());
            if (executor == null) {
                if(LOGGER.isInfoEnabled()) LOGGER.info("No executor defined for artifact type (" + artifact.getArtifactType() + ")");
                continue;
            }

            ArtifactExecutionResults results = null;
            try {
                if(LOGGER.isInfoEnabled()) LOGGER.info("Executing artifact type:  " + artifact.getArtifactType());
                results = executor.executeArtifact(options, artifact);
            } catch (Exception e) {
                results = new ArtifactExecutionResults();
                results.setRawResults("UNABLE TO EXECUTE ARTIFACT: " + e);
                results.setPass(false);
            }

            results.setOriginalArtifact(artifact);
            resultsList.add(results);

            

        }


        return resultsList;


    }

    public void setExecutors(Map<ArtifactType, ArtifactExecutor> executors) {

        this.artifactExecutors.putAll(executors);


    }
}
