package minderupt.spectacular.decisioner.euc;

import static org.junit.Assert.*;
import org.junit.Test;
import minderupt.spectacular.data.model.Artifact;
import minderupt.spectacular.data.model.Decision;

import java.util.List;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Oct 16, 2009
 * Time: 4:39:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class EUCHeaderValueDecisionerTest {


    @Test
    public void testHeaderValueVote() throws Exception {

        EUCHeaderValueDecisioner decisioner = new EUCHeaderValueDecisioner();
        Artifact artifact = new Artifact();

        List<String> headers = new LinkedList<String>();
        headers.add("Primary Flow");
        artifact.setHeaders(headers);

        Decision vote = decisioner.decision(artifact);
        assertEquals(Artifact.EUC, vote.getVote());


    }
}
