package minderupt.spectacular.decisioner.fit;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import minderupt.spectacular.data.model.Artifact;
import minderupt.spectacular.data.model.Decision;

import java.util.List;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Nov 7, 2009
 * Time: 3:43:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class FITHeaderClassIsFixtureDecisionerTest {


    @Test
    public void testClassIsFixture() throws Exception {

        FITHeaderClassIsFixtureDecisioner decisioner = new FITHeaderClassIsFixtureDecisioner();

        Artifact artifact = new Artifact();
        List<String> header = new LinkedList<String>();

        header.add("minderupt.spectacular.decisioner.fit.MinderuptFixture");
        artifact.setHeaders(header);
        
        Decision decision = decisioner.decision(artifact);

        assertNotNull(decision);
        assertEquals(Artifact.FIT, decision.getVote());


    }

}
