package minderupt.spectacular.executor.fit;

import fit.Fixture;
import fit.Parse;
import minderupt.spectacular.data.model.Artifact;
import minderupt.spectacular.data.model.GlobalOptions;
import minderupt.spectacular.executor.ArtifactExecutionResults;
import minderupt.spectacular.executor.ArtifactExecutor;
import org.apache.log4j.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;

/**
 * Created by IntelliJ IDEA.
 * User: dowlinmi
 * Date: Oct 20, 2009
 * Time: 9:03:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class FITArtifactExecutor implements ArtifactExecutor {

    private static Logger LOGGER = Logger.getLogger(FITArtifactExecutor.class);

    public ArtifactExecutionResults executeArtifact(final GlobalOptions globalOptions, final Artifact artifact) {

        ArtifactExecutionResults results = new ArtifactExecutionResults();

        // parsing tables
        if(LOGGER.isInfoEnabled()) LOGGER.info("Parsing FIT Tables.");
        Parse tables = null;
        try {
            tables = new Parse(artifact.getRawArtifact(), new String[] {"table", "tr", "td"});
        } catch(ParseException pe) {
            LOGGER.error("Unable to parse FIT table in artifact.", pe);
            results.setRawResults(artifact.getRawArtifact());
            return(results);
        }

        // execute fixture
        if(LOGGER.isInfoEnabled()) LOGGER.info("Executing FIT fixture.");
        StringWriter stringWriter = new StringWriter();
        try {
            Fixture fitFixture = new Fixture();
            fitFixture.doTables(tables);

            // get results of test
            PrintWriter writer = new PrintWriter(stringWriter);
            tables.print(writer);
        } catch (Exception e) {
            LOGGER.error("Problem occured while running FIT tests.", e);
            return(results);
        }

        // set results of test
        results.setRawResults(stringWriter.toString());
        return results;  

    }
}
