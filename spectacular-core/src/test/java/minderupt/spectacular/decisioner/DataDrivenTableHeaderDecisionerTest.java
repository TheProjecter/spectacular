package minderupt.spectacular.decisioner;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import minderupt.spectacular.data.model.Artifact;
import minderupt.spectacular.data.model.Decision;
import minderupt.spectacular.data.model.ArtifactType;
import minderupt.spectacular.decisioner.datadriven.DataDrivenTableHeaderDecisioner;

import java.util.List;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Dec 5, 2009
 * Time: 1:17:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class DataDrivenTableHeaderDecisionerTest {

    @Test
    public void testAbstainFromTableWithoutCorrectTitleInHeader() throws Exception {

        Artifact a = new Artifact();
        List<String> headers = new LinkedList<String>();
        headers.add("Some Title");
        a.setHeaders(headers);

        DataDrivenTableHeaderDecisioner decisioner = new DataDrivenTableHeaderDecisioner();
        Decision decision = decisioner.decision(a);

        assertNotNull(decision);
        assertEquals(ArtifactType.ABSTAIN, decision.getVote());

    }

    @Test
    public void testDataDrivenFromTableWithDataTitleInHeader() throws Exception {

        Artifact a = new Artifact();
        List<String> headers = new LinkedList<String>();
        headers.add("Data for something");
        a.setHeaders(headers);

        DataDrivenTableHeaderDecisioner decisioner = new DataDrivenTableHeaderDecisioner();
        Decision decision = decisioner.decision(a);

        assertNotNull(decision);
        assertEquals(ArtifactType.DATADRIVEN, decision.getVote());

    }

    @Test
    public void testDataDrivenFromTableWithDataDrivenTitleInHeader() throws Exception {

        Artifact a = new Artifact();
        List<String> headers = new LinkedList<String>();
        headers.add("Data Driven for something");
        a.setHeaders(headers);

        DataDrivenTableHeaderDecisioner decisioner = new DataDrivenTableHeaderDecisioner();
        Decision decision = decisioner.decision(a);

        assertNotNull(decision);
        assertEquals(ArtifactType.DATADRIVEN, decision.getVote());

    }

    @Test
    public void testDataDrivenFromTableWithDataHyphenDrivenTitleInHeader() throws Exception {

        Artifact a = new Artifact();
        List<String> headers = new LinkedList<String>();
        headers.add("Data-Driven for something");
        a.setHeaders(headers);

        DataDrivenTableHeaderDecisioner decisioner = new DataDrivenTableHeaderDecisioner();
        Decision decision = decisioner.decision(a);

        assertNotNull(decision);
        assertEquals(ArtifactType.DATADRIVEN, decision.getVote());

    }


    @Test
    public void testAbstainFromTableWithNullHeader() throws Exception {

        Artifact a = new Artifact();
        List<String> headers = new LinkedList<String>();
        a.setHeaders(headers);

        DataDrivenTableHeaderDecisioner decisioner = new DataDrivenTableHeaderDecisioner();
        Decision decision = decisioner.decision(a);

        assertNotNull(decision);
        assertEquals(ArtifactType.ABSTAIN, decision.getVote());

    }





}
