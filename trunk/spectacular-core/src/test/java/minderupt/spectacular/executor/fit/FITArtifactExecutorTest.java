package minderupt.spectacular.executor.fit;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import minderupt.spectacular.data.model.Artifact;
import minderupt.spectacular.data.model.GlobalOptions;
import minderupt.spectacular.executor.ArtifactExecutionResults;

/**
 * Created by IntelliJ IDEA.
 * User: dowlinmi
 * Date: Oct 20, 2009
 * Time: 9:04:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class FITArtifactExecutorTest {

    private static final String LINESEP = System.getProperty("line.separator");
    private Artifact standardFITArtifact;
    private FITArtifactExecutor fitExecutor;

    @Before
    public void setUp() throws Exception {



    }




    @Test
    public void testFITExecutor() throws Exception {

        this.fitExecutor = new FITArtifactExecutor();


        this.standardFITArtifact = new Artifact();
        String rawArtifact =       "<html>" + LINESEP +
                                   "<body>" + LINESEP +
                                   "<table>" + LINESEP +
                                   "<tr><td>minderupt.spectacular.executor.fit.SandboxOneFixture</td></tr>" + LINESEP +
                                   "<tr><td>First Name</td><td>Last Name</td><td>Age</td><td>Email</td><td>create()</td></tr>" + LINESEP +
                                   "<tr><td>Michael</td><td>Dowling</td><td>32</td><td>michael@minderupt.com</td><td>true</td></tr>" + LINESEP +
                                   "<tr><td>Michael</td><td>null</td><td>32</td><td>michael@minderupt.com</td><td>false</td></tr>" + LINESEP +
                                   "<tr><td>Michael</td><td>Dowling</td><td>16</td><td>michael@minderupt.com</td><td>false</td></tr>" + LINESEP +
                                   "</table></body></html>";
        this.standardFITArtifact.setRawArtifact(rawArtifact);

        

        ArtifactExecutionResults results = this.fitExecutor.executeArtifact(new GlobalOptions(), this.standardFITArtifact);
        assertNotNull(results);

        
    }


}
