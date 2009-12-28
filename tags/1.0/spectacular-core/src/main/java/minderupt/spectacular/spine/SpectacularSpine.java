package minderupt.spectacular.spine;

import minderupt.spectacular.data.model.Artifact;
import minderupt.spectacular.data.model.Document;
import minderupt.spectacular.data.model.GlobalOptions;
import minderupt.spectacular.data.model.Report;
import minderupt.spectacular.decisioner.DecisionerAgent;
import minderupt.spectacular.executor.ArtifactExecutionResults;
import minderupt.spectacular.executor.ArtifactExecutorAgent;
import minderupt.spectacular.parser.ArtifactExtractor;
import minderupt.spectacular.parser.DocumentParseException;
import minderupt.spectacular.reader.DocumentReader;
import minderupt.spectacular.reporting.ReportBuilder;
import minderupt.spectacular.reporting.ReportWriter;
import minderupt.spectacular.preexecutor.agent.PreexecutorAgent;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


/**
 *
 */
public class SpectacularSpine {

    private static Logger LOGGER = Logger.getLogger(SpectacularSpine.class);

    // location and name of spec
    private String specificationLocation;

    // global options
    private GlobalOptions globalOptions;


    private DocumentReader documentReader;
    private ArtifactExtractor artifactExtractor;
    private DecisionerAgent decisionerAgent;
    private PreexecutorAgent preexecutorAgent;
    private ArtifactExecutorAgent artifactExecutorAgent;
    private ReportBuilder reportBuilder;
    private ReportWriter reportWriter;


    public void run() {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("Starting Spectacular spine!");

        // read specifications
        if (LOGGER.isDebugEnabled()) LOGGER.debug("Loading specification from " + getSpecificationLocation());
        String specification = null;
        try {
            specification = getDocumentReader().read(getSpecificationLocation());
        } catch (FileNotFoundException fnfe) {
            LOGGER.fatal("Unable to find specification (" + getSpecificationLocation() + ")", fnfe);
            return;
        }

        // parse into Document + Artifacts
        if (LOGGER.isDebugEnabled()) LOGGER.debug("Parsing specification and extracting artifacts.");
        Document specDoc = null;
        try {
            specDoc = getArtifactExtractor().parse(specification);
        } catch (DocumentParseException dpe) {
            LOGGER.fatal("Unable to parse document into artifacts.", dpe);
            return;
        }

        // decision each artifact (BDD, FIT, EUC)
        if (LOGGER.isDebugEnabled()) LOGGER.debug("Figuring out what types of artifacts we have.");
        specDoc = getDecisionerAgent().decide(specDoc);
        // logArtifactTypes(specDoc);

        // preprocess document
        if(LOGGER.isDebugEnabled()) LOGGER.debug("Preprocessing Document.");
        try {
            specDoc = getPreexecutorAgent().preprocessDocument(specDoc);
        } catch(Exception e) {
            LOGGER.error("Unable to preprocess document but moving on", e);
        }


        // execute each artifact
        if (LOGGER.isDebugEnabled()) LOGGER.debug("Executing each artifact.");
        List<ArtifactExecutionResults> resultsList = getArtifactExecutorAgent().executeArtifacts(getGlobalOptions(), specDoc.getArtifacts());

        // format reports, write report spec
        if(LOGGER.isDebugEnabled()) LOGGER.debug("Writing report spec");
        Report report = getReportBuilder().build(specDoc, resultsList);
        
        try {
            getReportWriter().write(globalOptions, report);
        } catch(IOException ioe) {
            LOGGER.fatal("Unable to write report", ioe);
        }


    }

    private void logArtifactTypes(Document specDoc) {

        List<Artifact> artifactList = specDoc.getArtifacts();
        LOGGER.info("Number of artifacts identified:  " + artifactList.size());

        for (Artifact a : artifactList) {

            LOGGER.info("Artifact:  " + a.getRawArtifact());
            LOGGER.info("Artifact Type:  " + a.getArtifactType());

        }


    }

    public String getSpecificationLocation() {
        return specificationLocation;
    }

    public void setSpecificationLocation(String specificationLocation) {
        this.specificationLocation = specificationLocation;
    }

    public ReportBuilder getReportBuilder() {
        return reportBuilder;
    }

    public void setReportBuilder(ReportBuilder reportBuilder) {
        this.reportBuilder = reportBuilder;
    }

    public ReportWriter getReportWriter() {
        return reportWriter;
    }

    public void setReportWriter(ReportWriter reportWriter) {
        this.reportWriter = reportWriter;
    }

    public DocumentReader getDocumentReader() {
        return documentReader;
    }

    public void setDocumentReader(DocumentReader documentReader) {
        this.documentReader = documentReader;
    }

    public ArtifactExtractor getArtifactExtractor() {
        return artifactExtractor;
    }

    public void setArtifactExtractor(ArtifactExtractor artifactExtractor) {
        this.artifactExtractor = artifactExtractor;
    }

    public DecisionerAgent getDecisionerAgent() {
        return decisionerAgent;
    }

    public void setDecisionerAgent(DecisionerAgent decisionerAgent) {
        this.decisionerAgent = decisionerAgent;
    }

    public ArtifactExecutorAgent getArtifactExecutorAgent() {
        return artifactExecutorAgent;
    }

    public PreexecutorAgent getPreexecutorAgent() {
        return preexecutorAgent;
    }

    public void setPreexecutorAgent(PreexecutorAgent preexecutorAgent) {
        this.preexecutorAgent = preexecutorAgent;
    }

    public void setArtifactExecutorAgent(ArtifactExecutorAgent artifactExecutorAgent) {
        this.artifactExecutorAgent = artifactExecutorAgent;
    }

    public GlobalOptions getGlobalOptions() {
        return globalOptions;
    }

    public void setGlobalOptions(GlobalOptions globalOptions) {
        this.globalOptions = globalOptions;
    }

}
