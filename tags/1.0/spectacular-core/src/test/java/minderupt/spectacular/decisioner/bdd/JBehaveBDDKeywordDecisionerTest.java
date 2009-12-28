package minderupt.spectacular.decisioner.bdd;

import static org.junit.Assert.*;
import org.junit.Test;
import minderupt.spectacular.data.model.Artifact;
import minderupt.spectacular.data.model.Decision;
import minderupt.spectacular.data.model.ArtifactType;

import java.util.List;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: dowlinmi
 * Date: Oct 20, 2009
 * Time: 10:37:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class JBehaveBDDKeywordDecisionerTest {

    private static final String LINESEP = System.getProperty("line.separator");

    @Test
    public void testBDDDecisionSimpleTableSimpleKeywords() throws Exception {

        JBehaveBDDKeywordDecisioner decisioner = new JBehaveBDDKeywordDecisioner();

        Artifact artifact = new Artifact();
        List<List<String>> table = new LinkedList<List<String>>();
        List<String> dataCells = new LinkedList<String>();
        List<String> header = new LinkedList<String>();

        header.add("minderupt.spectacular.decisioner.bdd.SandboxOneSteps");
        artifact.setHeaders(header);

        String scenarioText = "Scenario: Trying to do something useful" + LINESEP +
                              "  Given a system in a certain state" + LINESEP +
                              "  When a user does something on the system" + LINESEP +
                              "  Then verify that the system does as expected" + LINESEP;

        dataCells.add(scenarioText);
        table.add(dataCells);
        artifact.setTableContent(table);

        Decision decision = decisioner.decision(artifact);
        assertNotNull(decision);
        assertEquals(ArtifactType.BDD,  decision.getVote());
        assertEquals(3, decision.getWeight());


    }


}
