package minderupt.spectacular.data.model;

import org.apache.commons.cli.Options;

import java.util.List;


public interface GlobalOptions {

    public static final String ARGS_DOCUMENT_READER = "documentReader";
    public static final String ARGS_ARTIFACT_EXTRACTOR = "artifactExtractor";
    public static final String ARGS_DECISIONER_AGENT = "decisionerAgent";
    public static final String ARGS_PREEXECUTOR_AGENT = "preexecutorAgent";
    public static final String ARGS_ARTIFACT_EXECUTOR_AGENT = "artifactExecutorAgent";
    public static final String ARGS_REPORT_BUILDER = "reportBuilder";
    public static final String ARGS_REPORT_WRITER = "reportWriter";
    public static final String ARGS_EUC_FIXTURES = "fixtures";
    public static final String ARGS_SPEC_LOCATION = "specLocation";
    public static final String ARGS_CONFIG = "config";
    public static final String ARGS_HELP = "help";

    List<String> getFixtures();

    void addFixture(String basePackages);

    String getReportLocation();

    void setReportLocation(String reportLocation);

    String getSpecLocation();

    void setSpecLocation(String l);

    String getConfig();

    void setConfig(String c);

    String getDocumentReaderBeanName();

    void setDocumentReaderBeanName(String documentReaderBeanName);

    String getArtifactExtractorBeanName();

    void setArtifactExtractorBeanName(String artifactExtractorBeanName);

    String getDecisionerAgentBeanName();

    void setDecisionerAgentBeanName(String decisionerAgentBeanName);

    String getPreexecutorAgentBeanName();

    void setPreexecutorAgentBeanName(String preexecutorAgentBeanName);

    String getArtifactExecutorAgentBeanName();

    void setArtifactExecutorAgentBeanName(String artifactExecutorAgentBeanName);

    String getReportBuilderBeanName();

    void setReportBuilderBeanName(String reportBuilderBeanName);

    String getReportWriterBeanName();

    void setReportWriterBeanName(String reportWriterBeanName);

    boolean isHelp();

    void setHelp(boolean help);

    void printUsage(Options options);
}
