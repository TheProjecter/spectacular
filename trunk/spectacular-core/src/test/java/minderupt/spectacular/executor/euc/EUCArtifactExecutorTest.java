package minderupt.spectacular.executor.euc;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import minderupt.spectacular.data.model.Artifact;
import minderupt.spectacular.data.model.GlobalOptions;
import minderupt.spectacular.data.model.ArtifactType;
import minderupt.spectacular.data.model.CommandLineGlobalOptions;
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

        ArtifactExecutionResults results = executor.executeArtifact(new CommandLineGlobalOptions(), a);
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
        a.put(1, 1, "User sees something that I don't have implemented");

        ArtifactExecutionResults results = executor.executeArtifact(new CommandLineGlobalOptions(), a);
        assertNotNull(results);
        assertEquals("User does something basic (SUCCESS)", results.get(1, 0));
        assertEquals("User sees something that I don't have implemented (PENDING)", results.get(1, 1));
        assertEquals("User executes some test button (NOT PERFORMED)", results.get(2, 0));
        assertEquals("User finds himself executed (NOT PERFORMED)", results.get(2, 1));


    }

    @Test
    public void testIntegratedRubyExecution() throws Exception {

        Artifact a = setupBasicEUCArtifact();
        GlobalOptions options = new CommandLineGlobalOptions();
        options.addFixture("ruby:src/test/ruby/IntegratedRubyExecution.rb");

        EUCArtifactExecutor executor = new EUCArtifactExecutor();
        ArtifactExecutionResults results = executor.executeArtifact(options, a);
        assertNotNull(results);
        assertEquals("User does something basic (SUCCESS)", results.get(1, 0));
        assertEquals("User sees a basic view (PENDING)", results.get(1, 1));
        assertEquals("User executes some test button (NOT PERFORMED)", results.get(2, 0));
        assertEquals("User finds himself executed (NOT PERFORMED)", results.get(2, 1));


    }

    @Test
    public void testIntegratedRubyExecutionWithArguments() throws Exception {

        Artifact a = setupBasicEUCArtifact();
        GlobalOptions options = new CommandLineGlobalOptions();
        options.addFixture("ruby:src/test/ruby/IntegratedRubyExecutionWithArguments.rb");

        EUCArtifactExecutor executor = new EUCArtifactExecutor();
        ArtifactExecutionResults results = executor.executeArtifact(options, a);
        assertNotNull(results);
        assertEquals("User does something basic (SUCCESS)", results.get(1, 0));
        assertEquals("User sees a basic view (SUCCESS)", results.get(1, 1));
        assertEquals("User executes some test button (PENDING)", results.get(2, 0));
        assertEquals("User finds himself executed (NOT PERFORMED)", results.get(2, 1));


    }


    @Test
    public void testIntegratedRubyExecutionWithArgumentsWithContext() throws Exception {

        Artifact a = setupBasicEUCArtifact();
        GlobalOptions options = new CommandLineGlobalOptions();
        options.addFixture("ruby:src/test/ruby/IntegratedRubyExecutionWithArgumentsWithContext.rb");

        EUCArtifactExecutor executor = new EUCArtifactExecutor();
        ArtifactExecutionResults results = executor.executeArtifact(options, a);
        assertNotNull(results);
        assertEquals("User does something basic (SUCCESS)", results.get(1, 0));
        assertEquals("User sees a basic view (SUCCESS)", results.get(1, 1));
        assertEquals("User executes some test button (PENDING)", results.get(2, 0));
        assertEquals("User finds himself executed (NOT PERFORMED)", results.get(2, 1));


    }

    @Test
    public void testIntegratedGroovyExecutionWithArgumentsWithContext() throws Exception {

        Artifact a = setupBasicEUCArtifact();
        GlobalOptions options = new CommandLineGlobalOptions();
        options.addFixture("groovy:src/test/groovy/IntegratedGroovyExecutionWithArgumentsWithContext.groovy");

        EUCArtifactExecutor executor = new EUCArtifactExecutor();
        ArtifactExecutionResults results = executor.executeArtifact(options, a);
        assertNotNull(results);
        assertEquals("User does something basic (SUCCESS)", results.get(1, 0));
        assertEquals("User sees a basic view (SUCCESS)", results.get(1, 1));
        assertEquals("User executes some test button (PENDING)", results.get(2, 0));
        assertEquals("User finds himself executed (NOT PERFORMED)", results.get(2, 1));


    }

    @Test
    public void testExecuteEUCWithDataDrivenTable() throws Exception {

        Artifact a = setupBasicEUCArtifact();
        Artifact data = setupBasicDataDrivenArtifact();

        a.put(1, 0, "User does $something basic");
        a.put(1, 1, "User sees $else view");
        a.put(2, 0, "User executes some $whatever button");
        a.put(2, 1, "User finds himself $whatever");

        a.setDataDrivenInstances(data);

        GlobalOptions options = new CommandLineGlobalOptions();
        options.addFixture("groovy:src/test/groovy/ExecuteEUCWithDataDrivenTable.groovy");

        EUCArtifactExecutor executor = new EUCArtifactExecutor();
        ArtifactExecutionResults results = executor.executeArtifact(options, a);
        assertNotNull(results);
        // assertEquals("User does $something basic (SEE DATA TABLE)", results.get(1, 0));
        // assertEquals("User sees $else view (SEE DATA TABLE)", results.get(1, 1));
        // assertEquals("User executes some $whatever button (SEE DATA TABLE)", results.get(2, 0));
        // assertEquals("User finds himself $whatever (SEE DATA TABLE)", results.get(2, 1));


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

        artifact.setArtifactType(ArtifactType.EUC);

        return (artifact);

    }

    private Artifact setupBasicDataDrivenArtifact() throws Exception {

        Artifact artifact = new Artifact();
        artifact.put(0, 0, "$something");
        artifact.put(0, 1, "$else");
        artifact.put(0, 2, "$whatever");
        artifact.put(1, 0, "onezero");
        artifact.put(1, 1, "oneone");
        artifact.put(1, 2, "onetwo");
        artifact.put(2, 0, "twozero");
        artifact.put(2, 1, "twoone");
        artifact.put(2, 2, "twotwo");
        artifact.put(3, 0, "threezero");
        artifact.put(3, 1, "threeone");
        artifact.put(3, 2, "threetwo");

        artifact.setArtifactType(ArtifactType.DATADRIVEN);

        return(artifact);


    }

}
