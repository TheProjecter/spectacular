package minderupt.spectacular.preexecutor.datadriven;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import minderupt.spectacular.data.model.Artifact;
import minderupt.spectacular.data.model.ArtifactType;
import minderupt.spectacular.data.model.Document;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Dec 5, 2009
 * Time: 1:45:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class DataDrivenArrangementPreexecutorTest {

    @Test
    public void testApplyDataDrivenToPreviousArtifact() throws Exception {


        DataDrivenArrangementPreexecutor preexec = new DataDrivenArrangementPreexecutor();

        // create 3 artifacts - 2 normal 1 data driven in the middle
        Document doc = new Document();
        Artifact stdArtifactOne = new Artifact();
        stdArtifactOne.setArtifactType(ArtifactType.EUC);
        doc.addTestArtifact(stdArtifactOne);

        Artifact dd = new Artifact();
        dd.setArtifactType(ArtifactType.DATADRIVEN);
        doc.addTestArtifact(dd);

        Artifact stdArtifactTwo = new Artifact();
        stdArtifactTwo.setArtifactType(ArtifactType.BDD);
        doc.addTestArtifact(stdArtifactTwo);

        Document newDoc = preexec.preprocess(doc);
        assertNotNull(newDoc);
        assertEquals(2, newDoc.getArtifacts().size());
        assertNotNull(newDoc.getArtifacts().get(0).getDataDrivenInstances());


    }


}
