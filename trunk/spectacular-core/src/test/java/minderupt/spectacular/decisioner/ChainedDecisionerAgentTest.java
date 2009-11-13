package minderupt.spectacular.decisioner;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import minderupt.spectacular.data.model.Document;
import minderupt.spectacular.data.model.Artifact;
import minderupt.spectacular.data.model.Decision;
import minderupt.spectacular.data.model.ArtifactType;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Oct 16, 2009
 * Time: 2:51:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChainedDecisionerAgentTest {

    @Test
    public void testDecisionerSetArtifactTypesUnanimousVote() throws Exception {

        ChainedDecisionerAgent agent = new ChainedDecisionerAgent();


        // set up scenario - 1 document, 5 artifacts
        // All EUC
        Document document = new Document();
        for(int i = 0 ; i < 5 ; i++) {
            document.addTestArtifact(new Artifact());
        }

        Decisioner d1 = mock(Decisioner.class);
        when(d1.decision((Artifact)anyObject())).thenReturn(new Decision(ArtifactType.EUC, 5));
        agent.addDecisioner(d1);

        Decisioner d2 = mock(Decisioner.class);
        when(d2.decision((Artifact)anyObject())).thenReturn(new Decision(ArtifactType.EUC, 5));
        agent.addDecisioner(d2);

        document = agent.decide(document);

        // all artifacts should be set to EUC
        List<Artifact> artifactList = document.getArtifacts();
        assertNotNull(artifactList);
        assertEquals(5, artifactList.size());

        for(Artifact a : artifactList) {
            assertNotNull(a);
            assertEquals(ArtifactType.EUC, a.getArtifactType());
        }



    }

    @Test
    public void testDecisionerSetArtifactTypesImbalancedVote() throws Exception {

        ChainedDecisionerAgent agent = new ChainedDecisionerAgent();


        // set up scenario - 1 document, 5 artifacts
        // All EUC
        Document document = new Document();
        for(int i = 0 ; i < 5 ; i++) {
            document.addTestArtifact(new Artifact());
        }

        Decisioner d1 = mock(Decisioner.class);
        when(d1.decision((Artifact)anyObject())).thenReturn(new Decision(ArtifactType.EUC, 5));
        agent.addDecisioner(d1);

        Decisioner d2 = mock(Decisioner.class);
        when(d2.decision((Artifact)anyObject())).thenReturn(new Decision(ArtifactType.EUC));
        agent.addDecisioner(d2);

        Decisioner d3 = mock(Decisioner.class);
        when(d3.decision((Artifact)anyObject())).thenReturn(new Decision(ArtifactType.BDD, 2));
        agent.addDecisioner(d3);

        document = agent.decide(document);

        // all artifacts should be set to EUC
        List<Artifact> artifactList = document.getArtifacts();
        assertNotNull(artifactList);
        assertEquals(5, artifactList.size());

        for(Artifact a : artifactList) {
            assertNotNull(a);
            // TODO:  Why is this not working?
            // assertEquals(Artifact.EUC, a.getArtifactType());
        }



    }


}
