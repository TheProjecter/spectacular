package minderupt.spectacular.preexecutor.agent;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import minderupt.spectacular.preexecutor.Preexecutor;
import minderupt.spectacular.data.model.Document;

import java.util.List;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Dec 7, 2009
 * Time: 12:42:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class LinearPreexecutorAgentTest {


    @Test
    public void testPreprocessingInOrder() throws Exception {

        Document documentOne = new Document();
        documentOne.setRawDocument("");

        Document documentTwo = new Document();
        documentTwo.setRawDocument("One");

        Document documentThree = new Document();
        documentThree.setRawDocument("OneTwo");

        Preexecutor one = mock(Preexecutor.class);
        when(one.preprocess(documentOne)).thenReturn(documentTwo);

        Preexecutor two = mock(Preexecutor.class);
        when(two.preprocess(documentTwo)).thenReturn(documentThree);

        List<Preexecutor> peList = new LinkedList<Preexecutor>();
        peList.add(one);
        peList.add(two);

        LinearPreexecutorAgent agent = new LinearPreexecutorAgent();
        agent.setPreexecutors(peList);

        Document tstDoc = agent.preprocessDocument(documentOne);
        assertNotNull(tstDoc);
        assertEquals("OneTwo", tstDoc.getRawDocument());



    }


}
