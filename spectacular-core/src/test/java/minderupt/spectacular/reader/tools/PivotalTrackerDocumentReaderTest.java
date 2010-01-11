package minderupt.spectacular.reader.tools;

import minderupt.spectacular.util.HttpGateway;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by IntelliJ IDEA.
 * User: dowlinmi
 * Date: Jan 8, 2010
 * Time: 9:48:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class PivotalTrackerDocumentReaderTest {

    @Test
    public void testParseGoodLocation() throws Exception {

        PivotalTrackerDocumentReader reader = new PivotalTrackerDocumentReader();
        PivotalLocation location = reader.parseLocation("1111:222");

        assertNotNull(location);
        assertEquals("1111", location.getProjectId());
        assertEquals("222", location.getStoryId());


    }

    @Test
    public void testParseBadLocationNoStoryId() throws Exception {

        PivotalTrackerDocumentReader reader = new PivotalTrackerDocumentReader();
        PivotalLocation location = reader.parseLocation("1111");

        assertNotNull(location);
        assertEquals("1111", location.getProjectId());
        assertEquals("*", location.getStoryId());


    }

    @Test
    public void testParseBadLocationAllEmpty() throws Exception {

        PivotalTrackerDocumentReader reader = new PivotalTrackerDocumentReader();
        PivotalLocation location = reader.parseLocation(null);

        assertNotNull(location);
        assertEquals("", location.getProjectId());
        assertEquals("*", location.getStoryId());


    }

    @Test
    public void testGetSingleProjectSingleStoryAPIRequest() throws Exception {

        HttpGateway gateway = mock(HttpGateway.class);

        PivotalTrackerDocumentReader reader = new PivotalTrackerDocumentReader();
        reader.setHttpGateway(gateway);

        reader.read("12345:9876");

        verify(gateway).doGet("http://www.pivotaltracker.com/services/v2/projects/12345/stories/9876");

    }


    @Test
    public void testGetSingleProjectAllStoriesAPIRequest() throws Exception {

        HttpGateway gateway = mock(HttpGateway.class);

        PivotalTrackerDocumentReader reader = new PivotalTrackerDocumentReader();
        reader.setHttpGateway(gateway);

        reader.read("12345:*");

        verify(gateway).doGet("http://www.pivotaltracker.com/services/v2/projects/12345/stories");

    }




}

