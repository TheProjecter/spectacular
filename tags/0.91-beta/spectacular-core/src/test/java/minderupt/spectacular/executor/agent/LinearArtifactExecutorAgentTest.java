package minderupt.spectacular.executor.agent;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;

import static org.mockito.Mockito.*;

import minderupt.spectacular.executor.ArtifactExecutor;
import minderupt.spectacular.executor.ArtifactExecutionResults;
import minderupt.spectacular.data.model.Artifact;
import minderupt.spectacular.data.model.GlobalOptions;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Nov 4, 2009
 * Time: 7:34:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class LinearArtifactExecutorAgentTest {


    @Test
    public void testExecuteMockEUC() throws Exception {

        LinearArtifactExecutorAgent agent = new LinearArtifactExecutorAgent();

        ArtifactExecutionResults results = new ArtifactExecutionResults();
        results.setRawResults("TESTRESULTS");

        ArtifactExecutor mockExecutor = mock(ArtifactExecutor.class);
        when(mockExecutor.executeArtifact((GlobalOptions)anyObject(), (Artifact)anyObject())).thenReturn(results);


        // set up executors
        Map<Integer, ArtifactExecutor> executors = new HashMap<Integer, ArtifactExecutor>();
        executors.put(Artifact.EUC, mockExecutor);
        agent.setExecutors(executors);

        Artifact a = new Artifact();
        a.setArtifactType(Artifact.EUC);
        List<Artifact> artifactList = new LinkedList<Artifact>();
        artifactList.add(a);

        List<ArtifactExecutionResults> checkedResults = agent.executeArtifacts(new GlobalOptions(), artifactList);

        assertNotNull(checkedResults);
        assertEquals("TESTRESULTS", checkedResults.get(0).getRawResults());


    }

    @Test
    public void testExecuteMockEUCThrowException() throws Exception {


        LinearArtifactExecutorAgent agent = new LinearArtifactExecutorAgent();

        ArtifactExecutor mockExecutor = mock(ArtifactExecutor.class);
        when(mockExecutor.executeArtifact((GlobalOptions) anyObject(), (Artifact)anyObject())).thenThrow(new RuntimeException());


        // set up executors
        Map<Integer, ArtifactExecutor> executors = new HashMap<Integer, ArtifactExecutor>();
        executors.put(Artifact.EUC, mockExecutor);
        agent.setExecutors(executors);

        Artifact a = new Artifact();
        a.setArtifactType(Artifact.EUC);
        List<Artifact> artifactList = new LinkedList<Artifact>();
        artifactList.add(a);

        List<ArtifactExecutionResults> checkedResults = agent.executeArtifacts(new GlobalOptions(), artifactList);

        assertNotNull(checkedResults);
        assertFalse(checkedResults.get(0).isPass());
           



    }




}
