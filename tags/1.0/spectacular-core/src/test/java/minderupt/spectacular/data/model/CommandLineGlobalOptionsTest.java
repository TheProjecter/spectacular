package minderupt.spectacular.data.model;

import org.apache.commons.cli.*;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class CommandLineGlobalOptionsTest {


    @Test
    public void testParseCommonOptions() throws Exception {

        String[] args = new String[] {"-specLocation", "/path/to/spec",
                                      "-config", "/path/to/config",
                                      "-fixtures", "some.fixture.package"};


        CommandLineGlobalOptions options = new CommandLineGlobalOptions(args);
        assertNotNull(options.getFixtures());
        assertEquals("some.fixture.package", options.getFixtures().get(0));

        assertNotNull(options.getSpecLocation());
        assertEquals("/path/to/spec", options.getSpecLocation());

        assertNotNull(options.getConfig());
        assertEquals("/path/to/config", options.getConfig());


    }

    @Test
    public void testParseBeanNameOptions() throws Exception {


        String[] args = new String[] {"-documentReader", "reader",
                                    "-artifactExtractor", "extractor",
                                    "-decisionerAgent", "decisioner",
                                    "-preexecutorAgent", "preexecutor",
                                    "-artifactExecutorAgent", "artifactexecutor",
                                    "-reportBuilder", "builder",
                                    "-reportWriter", "writer"};

        CommandLineGlobalOptions options = new CommandLineGlobalOptions(args);
        assertEquals("reader", options.getDocumentReaderBeanName());
        assertEquals("extractor", options.getArtifactExtractorBeanName());
        assertEquals("decisioner", options.getDecisionerAgentBeanName());
        assertEquals("preexecutor", options.getPreexecutorAgentBeanName());
        assertEquals("artifactexecutor", options.getArtifactExecutorAgentBeanName());
        assertEquals("builder", options.getReportBuilderBeanName());
        assertEquals("writer", options.getReportWriterBeanName());




    }


    @Test
    public void testSpikeOnPosixParser() throws Exception {

        Options options = new Options();

        options.addOption(new Option("multichar", true, "Some Multicare Thing"));

        CommandLineParser cmdLineParser = new GnuParser();
        CommandLine cmdLine = cmdLineParser.parse(options, new String[] {"-multichar", "somevalue"});

        assertNotNull(cmdLine);
        assertTrue(cmdLine.hasOption("multichar"));
        assertEquals("somevalue", cmdLine.getOptionValue("multichar"));


    }

    


}
