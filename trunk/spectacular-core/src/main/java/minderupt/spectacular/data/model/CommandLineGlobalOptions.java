package minderupt.spectacular.data.model;

import org.apache.commons.cli.*;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

/**
 * Passed in to most Spectacular modules and who's data is set mainly by
 * command-line parameters.
 */
public class CommandLineGlobalOptions implements GlobalOptions {

    private static Logger LOGGER = Logger.getLogger(CommandLineGlobalOptions.class);
    private List<String> eucBasePackages = new LinkedList<String>();
    private String reportLocation;

    private final static String ARGS_DOCUMENT_READER = "documentReader";
    private final static String ARGS_ARTIFACT_EXTRACTOR = "artifactExtractor";
    private final static String ARGS_DECISIONER_AGENT = "decisionerAgent";
    private final static String ARGS_PREEXECUTOR_AGENT = "preexecutorAgent";
    private final static String ARGS_ARTIFACT_EXECUTOR_AGENT = "artifactExecutorAgent";
    private final static String ARGS_REPORT_BUILDER = "reportBuilder";
    private final static String ARGS_REPORT_WRITER = "reportWriter";

    private final static String ARGS_EUC_FIXTURES = "fixtures";
    private final static String ARGS_SPEC_LOCATION = "specLocation";
    private final static String ARGS_CONFIG = "config";
    private final static String ARGS_HELP = "help";



    public CommandLineGlobalOptions() {
        this(new String[] {});
    }


    public CommandLineGlobalOptions(String[] args) {

        // Options
        Options options = new Options();
        options.addOption(ARGS_EUC_FIXTURES, true, "Base Package for Executable Use Case fixtures.");
        options.addOption(ARGS_SPEC_LOCATION, true, "Location of the specification to test.");
        options.addOption(ARGS_CONFIG, true, "Beans file that configures Spectacular and wires the spine together");
        options.addOption(ARGS_HELP, false, "Help Menu");

        // bean options
        options.addOption(ARGS_DOCUMENT_READER, true, "Bean name for document reader.");
        options.addOption(ARGS_ARTIFACT_EXTRACTOR, true, "Bean name for artifact extractor");
        options.addOption(ARGS_DECISIONER_AGENT, true, "Bean name for Decisioner Agent");
        options.addOption(ARGS_PREEXECUTOR_AGENT, true, "Bean name for Pre-Executor Agent");
        options.addOption(ARGS_ARTIFACT_EXECUTOR_AGENT, true, "Bean name for Artifact Executor Agent");
        options.addOption(ARGS_REPORT_BUILDER, true, "Bean name for Report Builder");
        options.addOption(ARGS_REPORT_WRITER, true, "Bean name for Report Writer");


        CommandLineParser cmdLineParse = new PosixParser();
        CommandLine cmdLine = null;
        try {
            cmdLine = cmdLineParse.parse(options, args);
        } catch(ParseException pe) {
            LOGGER.fatal("Unable to parse command line options");
            return;
        }

        if(cmdLine.hasOption(ARGS_EUC_FIXTURES)) {

            String fixtures = cmdLine.getOptionValue(ARGS_EUC_FIXTURES);
            String[] fixtureArray = fixtures.split(",");
            for(String fixture : fixtureArray) addFixture(fixture);

        }


    }

    public List<String> getFixtures() {
        return(this.eucBasePackages);
    }


    public void addFixture(String basePackages) {
        eucBasePackages.add(basePackages);    
    }

    public String getReportLocation() {
        return reportLocation;
    }

    public void setReportLocation(String reportLocation) {
        this.reportLocation = reportLocation;
    }

    public String getSpecLocation() {

        return null;
    }

    public String getConfig() {

        return(null);
    }
}
