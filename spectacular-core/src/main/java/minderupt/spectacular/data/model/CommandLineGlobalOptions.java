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

    private Options options;

    private List<String> eucBasePackages = new LinkedList<String>();
    private String reportLocation;
    private String specLocation;
    private String config;
    private boolean help;


    private String documentReaderBeanName;
    private String artifactExtractorBeanName;
    private String decisionerAgentBeanName;
    private String preexecutorAgentBeanName;
    private String artifactExecutorAgentBeanName;
    private String reportBuilderBeanName;
    private String reportWriterBeanName;

   
    private String seleniumRCHost;
    private int seleniumRCPort;
    private String seleniumRCStartupCommand;
    private String seleniumRCInitialUrl;

    private String projectXml;





    public CommandLineGlobalOptions() {
        this(new String[] {});
    }


    public CommandLineGlobalOptions(String[] args) {

        // Options
        options = new Options();
        options.addOption(new Option(ARGS_EUC_FIXTURES, true, "Base Package for Executable Use Case fixtures."));
        options.addOption(new Option(ARGS_SPEC_LOCATION, true, "Location of the specification to test."));
        options.addOption(new Option(ARGS_REPORT_LOCATION, true, "Location of the report to write."));
        options.addOption(new Option(ARGS_CONFIG, true, "Beans file that configures Spectacular and wires the spine together"));
        options.addOption(new Option(ARGS_HELP, false, "Help Menu"));

        // bean options
        options.addOption(new Option(ARGS_DOCUMENT_READER, true, "Bean name for document reader."));
        options.addOption(new Option(ARGS_ARTIFACT_EXTRACTOR, true, "Bean name for artifact extractor"));
        options.addOption(new Option(ARGS_DECISIONER_AGENT, true, "Bean name for Decisioner Agent"));
        options.addOption(new Option(ARGS_PREEXECUTOR_AGENT, true, "Bean name for Pre-Executor Agent"));
        options.addOption(new Option(ARGS_ARTIFACT_EXECUTOR_AGENT, true, "Bean name for Artifact Executor Agent"));
        options.addOption(new Option(ARGS_REPORT_BUILDER, true, "Bean name for Report Builder"));
        options.addOption(new Option(ARGS_REPORT_WRITER, true, "Bean name for Report Writer"));

        // Selenium options
        options.addOption(new Option(ARGS_SELENIUM_RC_HOST, true, "Hostname of Remote Control server"));
        options.addOption(new Option(ARGS_SELENIUM_RC_PORT, true, "Port of Remote Control server"));
        options.addOption(new Option(ARGS_SELENIUM_RC_STARTUP_COMMAND, true, "Startup command for Selenium (typically the browser)"));
        options.addOption(new Option(ARGS_SELENIUM_RC_INITIAL_URL, true, "Initial URL for Selenium"));

        // project xml file
        options.addOption(new Option(ARGS_PROJECT_FILE, true, "Location of XML file for project command line arguments."));


        CommandLineParser cmdLineParse = new GnuParser();
        CommandLine cmdLine = null;
        try {
            cmdLine = cmdLineParse.parse(options, args, false);
        } catch(ParseException pe) {
            LOGGER.fatal("Unable to parse command line options", pe);
            return;
        }

        if(cmdLine.hasOption(ARGS_EUC_FIXTURES)) {
           String fixtures = cmdLine.getOptionValue(ARGS_EUC_FIXTURES);
            String[] fixtureArray = fixtures.split(",");
            for(String fixture : fixtureArray) addFixture(fixture);
        }

        if(!cmdLine.hasOption(ARGS_REPORT_LOCATION)) {
            setReportLocation("TestResults.html");
        } else {
            setReportLocation(cmdLine.getOptionValue(ARGS_REPORT_LOCATION));
        }

        if(cmdLine.hasOption(ARGS_SPEC_LOCATION)) setSpecLocation(cmdLine.getOptionValue(ARGS_SPEC_LOCATION));
        if(cmdLine.hasOption(ARGS_CONFIG)) setConfig(cmdLine.getOptionValue(ARGS_CONFIG));
        if(cmdLine.hasOption(ARGS_HELP)) setHelp(true);

        // bean
        if(cmdLine.hasOption(ARGS_DOCUMENT_READER)) setDocumentReaderBeanName(cmdLine.getOptionValue(ARGS_DOCUMENT_READER));
        if(cmdLine.hasOption(ARGS_DECISIONER_AGENT)) setDecisionerAgentBeanName(cmdLine.getOptionValue(ARGS_DECISIONER_AGENT));
        if(cmdLine.hasOption(ARGS_PREEXECUTOR_AGENT)) setPreexecutorAgentBeanName(cmdLine.getOptionValue(ARGS_PREEXECUTOR_AGENT));
        if(cmdLine.hasOption(ARGS_ARTIFACT_EXTRACTOR)) setArtifactExtractorBeanName(cmdLine.getOptionValue(ARGS_ARTIFACT_EXTRACTOR));
        if(cmdLine.hasOption(ARGS_ARTIFACT_EXECUTOR_AGENT)) setArtifactExecutorAgentBeanName(cmdLine.getOptionValue(ARGS_ARTIFACT_EXECUTOR_AGENT));
        if(cmdLine.hasOption(ARGS_REPORT_BUILDER)) setReportBuilderBeanName(cmdLine.getOptionValue(ARGS_REPORT_BUILDER));
        if(cmdLine.hasOption(ARGS_REPORT_WRITER)) setReportWriterBeanName(cmdLine.getOptionValue(ARGS_REPORT_WRITER));

        // selenium
        if(cmdLine.hasOption(ARGS_SELENIUM_RC_HOST)) setSeleniumRCHost(cmdLine.getOptionValue(ARGS_SELENIUM_RC_HOST));
        if(cmdLine.hasOption(ARGS_SELENIUM_RC_PORT)) setSeleniumRCPort(Integer.parseInt(cmdLine.getOptionValue(ARGS_SELENIUM_RC_PORT)));
        if(cmdLine.hasOption(ARGS_SELENIUM_RC_STARTUP_COMMAND)) setSeleniumRCStartupCommand(cmdLine.getOptionValue(ARGS_SELENIUM_RC_STARTUP_COMMAND));
        if(cmdLine.hasOption(ARGS_SELENIUM_RC_INITIAL_URL)) setSeleniumRCInitialUrl(cmdLine.getOptionValue(ARGS_SELENIUM_RC_INITIAL_URL));

        // project xml file
        if(cmdLine.hasOption(ARGS_PROJECT_FILE)) setProjectXml(cmdLine.getOptionValue(ARGS_PROJECT_FILE));


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

    @Override
    public String getSpecLocation() {
        return this.specLocation;
    }

    @Override
    public void setSpecLocation(String l) {
        this.specLocation = l;
    }

    @Override
    public String getConfig() {
        return(this.config);
    }

    @Override
    public void setConfig(String c) {
        this.config = c;
    }

    @Override
    public String getDocumentReaderBeanName() {
        return documentReaderBeanName;
    }

    @Override
    public void setDocumentReaderBeanName(String documentReaderBeanName) {
        this.documentReaderBeanName = documentReaderBeanName;
    }

    @Override
    public String getArtifactExtractorBeanName() {
        return artifactExtractorBeanName;
    }

    @Override
    public void setArtifactExtractorBeanName(String artifactExtractorBeanName) {
        this.artifactExtractorBeanName = artifactExtractorBeanName;
    }

    @Override
    public String getDecisionerAgentBeanName() {
        return decisionerAgentBeanName;
    }

    @Override
    public void setDecisionerAgentBeanName(String decisionerAgentBeanName) {
        this.decisionerAgentBeanName = decisionerAgentBeanName;
    }

    @Override
    public String getPreexecutorAgentBeanName() {
        return preexecutorAgentBeanName;
    }

    @Override
    public void setPreexecutorAgentBeanName(String preexecutorAgentBeanName) {
        this.preexecutorAgentBeanName = preexecutorAgentBeanName;
    }

    @Override
    public String getArtifactExecutorAgentBeanName() {
        return artifactExecutorAgentBeanName;
    }

    @Override
    public void setArtifactExecutorAgentBeanName(String artifactExecutorAgentBeanName) {
        this.artifactExecutorAgentBeanName = artifactExecutorAgentBeanName;
    }

    @Override
    public String getReportBuilderBeanName() {
        return reportBuilderBeanName;
    }

    @Override
    public void setReportBuilderBeanName(String reportBuilderBeanName) {
        this.reportBuilderBeanName = reportBuilderBeanName;
    }

    @Override
    public String getReportWriterBeanName() {
        return reportWriterBeanName;
    }

    @Override
    public void setReportWriterBeanName(String reportWriterBeanName) {
        this.reportWriterBeanName = reportWriterBeanName;
    }


    public String getSeleniumRCHost() {
        return seleniumRCHost;
    }

    public void setSeleniumRCHost(String seleniumRCHost) {
        this.seleniumRCHost = seleniumRCHost;
    }

    public int getSeleniumRCPort() {
        return seleniumRCPort;
    }

    public void setSeleniumRCPort(int seleniumRCPort) {
        this.seleniumRCPort = seleniumRCPort;
    }

    public String getSeleniumRCStartupCommand() {
        return seleniumRCStartupCommand;
    }

    public void setSeleniumRCStartupCommand(String seleniumRCStartupCommand) {
        this.seleniumRCStartupCommand = seleniumRCStartupCommand;
    }

    public String getSeleniumRCInitialUrl() {
        return seleniumRCInitialUrl;
    }

    public void setSeleniumRCInitialUrl(String seleniumRCInitialUrl) {
        this.seleniumRCInitialUrl = seleniumRCInitialUrl;
    }

    @Override
    public String getProjectXml() {
        return this.projectXml;
    }

    @Override
    public void setProjectXml(String xmlFile) {
        this.projectXml = xmlFile;
    }

    @Override
    public boolean isHelp() {
        return help;
    }

    @Override
    public void setHelp(boolean help) {
        this.help = help;
    }

    @Override
    public void printUsage() {

        HelpFormatter f = new HelpFormatter();
        f.printHelp("Spectacular", options);
        
    }

}
