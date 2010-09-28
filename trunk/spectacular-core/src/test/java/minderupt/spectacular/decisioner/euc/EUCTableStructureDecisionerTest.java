package minderupt.spectacular.decisioner.euc;

import org.junit.Test;
import static org.junit.Assert.*;
import minderupt.spectacular.data.model.Artifact;
import minderupt.spectacular.data.model.Decision;
import minderupt.spectacular.data.model.ArtifactType;
import minderupt.spectacular.decisioner.AbstractDecisionerTest;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Oct 16, 2009
 * Time: 4:15:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class EUCTableStructureDecisionerTest extends AbstractDecisionerTest {

    @Test
    public void testDecisionsStandardTwoColumnWithHeadersTable() throws Exception {

        EUCTableStructureDecisioner decisioner = new EUCTableStructureDecisioner();
        Artifact artifact = createTwoColumnMultilineTable();
        Decision vote = decisioner.decision(artifact);

        assertEquals(ArtifactType.EUC, vote.getVote());


    }




}
