package minderupt.spectacular.executor.euc;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import minderupt.spectacular.data.model.Artifact;
import minderupt.spectacular.data.model.GlobalOptions;
import minderupt.spectacular.executor.ArtifactExecutionResults;

import java.util.List;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: dowlinmi
 * Date: Nov 5, 2009
 * Time: 7:31:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class EUCArtifactExecutorTest {

    @Before
    public void setUp() throws Exception {

    }


    @Test
    public void testExecuteBasicEUCArtifactAndBasicResults() throws Exception {

        List<String> basePackage = new LinkedList<String>();
        basePackage.add("minderupt.spectacular.executor.euc.testthree");

        EUCArtifactExecutor executor = new EUCArtifactExecutor();
        executor.setBasePackages(basePackage);

        Artifact a = setupBasicEUCArtifact();

        ArtifactExecutionResults results = executor.executeArtifact(new GlobalOptions(), a);
        assertNotNull(results);
        assertEquals("User does something basic (SUCCESS)", results.get(1, 0));
        assertEquals("User sees a basic view (SUCCESS)", results.get(1, 1));
        assertEquals("User executes some test button (SUCCESS)", results.get(2, 0));
        assertEquals("User finds himself executed (PENDING)", results.get(2, 1));


    }



    @Test
    public void testExecuteBasicEUCArtifactAndNotPerformedResults() throws Exception {

        List<String> basePackage = new LinkedList<String>();
        basePackage.add("minderupt.spectacular.executor.euc.testthree");

        EUCArtifactExecutor executor = new EUCArtifactExecutor();
        executor.setBasePackages(basePackage);

        Artifact a = setupBasicEUCArtifact();
        a.put(1,1, "User sees something that I don't have implemented");

        ArtifactExecutionResults results = executor.executeArtifact(new GlobalOptions(), a);
        assertNotNull(results);
        assertEquals("User does something basic (SUCCESS)", results.get(1, 0));
        assertEquals("User sees something that I don't have implemented (PENDING)", results.get(1, 1));
        assertEquals("User executes some test button (NOT PERFORMED)", results.get(2, 0));
        assertEquals("User finds himself executed (NOT PERFORMED)", results.get(2, 1));


    }

    private Artifact setupBasicEUCArtifact() throws Exception {

        Artifact artifact = new Artifact();

        // typical header
        artifact.put(0, 0, "User Flow");
        artifact.put(0, 1, "Expectation");
        artifact.put(0, 2, "Comments");

        artifact.put(1, 0, "User does something basic");
        artifact.put(1, 1, "User sees a basic view");
        artifact.put(2, 0, "User executes some test button");
        artifact.put(2, 1, "User finds himself executed");

        artifact.setArtifactType(Artifact.EUC);

        return(artifact);


    }



}
