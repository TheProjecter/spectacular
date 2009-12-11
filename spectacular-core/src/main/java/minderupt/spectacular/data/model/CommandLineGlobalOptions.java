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


    public CommandLineGlobalOptions(String[] args) {

        // Options
        Options options = new Options();
        options.addOption("fixtures", true, "Base Package for Executable Use Case fixtures.");
        options.addOption("specLocation", true, "Location of the specification to test.");
        options.addOption("config", true, "Beans file that configures Spectacular and wires the spine together");
        options.addOption("help", false, "Help Menu");

        // bean options
        options.addOption(ARGS_DOCUMENT_READER, true, "Help Menu");
        options.addOption(ARGS_ARTIFACT_EXTRACTOR, true, "Help Menu");
        options.addOption(ARGS_DECISIONER_AGENT, true, "Help Menu");
        options.addOption(ARGS_PREEXECUTOR_AGENT, true, "Help Menu");
        options.addOption(ARGS_ARTIFACT_EXECUTOR_AGENT, true, "Help Menu");
        options.addOption(ARGS_REPORT_BUILDER, true, "Help Menu");
        options.addOption(ARGS_REPORT_WRITER, true, "Help Menu");


        CommandLineParser cmdLineParse = new PosixParser();
        CommandLine cmdLine = null;
        try {
            cmdLine = cmdLineParse.parse(options, args);
        } catch(ParseException pe) {
            LOGGER.fatal("Unable to parse command line options");
            return;
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
