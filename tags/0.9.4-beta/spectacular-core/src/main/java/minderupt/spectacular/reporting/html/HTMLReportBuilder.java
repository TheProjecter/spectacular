package minderupt.spectacular.reporting.html;

import minderupt.spectacular.data.model.Document;
import minderupt.spectacular.data.model.Report;
import minderupt.spectacular.data.model.ArtifactType;
import minderupt.spectacular.executor.ArtifactExecutionResults;
import minderupt.spectacular.reporting.ReportBuilder;
import minderupt.spectacular.reporting.ArtifactReportBuilder;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.apache.log4j.Logger;

/**
 *
 */
public class HTMLReportBuilder implements ReportBuilder {

    private static Logger LOGGER = Logger.getLogger(HTMLReportBuilder.class);
    private Map<ArtifactType, ArtifactReportBuilder> reportBuilders;


    public HTMLReportBuilder() {
        this.reportBuilders = new HashMap<ArtifactType, ArtifactReportBuilder>();
    }


    public Report build(Document document, List<ArtifactExecutionResults> results) {

        if(LOGGER.isInfoEnabled()) LOGGER.info("Building HTML Report.");
        StringBuilder report = new StringBuilder();
        report.append("<html><body><h1>Test Results</h1>");

        for(ArtifactExecutionResults result : results) {

            // get the report builder for this artifact type
            ArtifactType artifactType = result.getOriginalArtifact().getArtifactType();
            if(LOGGER.isInfoEnabled()) LOGGER.info("Getting report builder for artifact type = " + artifactType);
            ArtifactReportBuilder builder = this.reportBuilders.get(artifactType);
            if(builder == null) {
                LOGGER.error("Artifact executed but there is no report builder defined for artifact type = " + artifactType);
                continue;
            }

            // get report from builder
            if(LOGGER.isInfoEnabled()) LOGGER.info("Building report for artifact type = " + artifactType);
            String builderReport = builder.build(result);

            report.append("<hr /><br />");
            report.append(builderReport);
            report.append("<br /><hr /><br />");

        }


        report.append("</body></html>");

        Report r = new Report();
        r.setReportText(report.toString());

        return(r);
        
    }

    public Map<ArtifactType, ArtifactReportBuilder> getReportBuilders() {
        return reportBuilders;
    }

    public void setReportBuilders(Map<ArtifactType, ArtifactReportBuilder> reportBuilders) {
        this.reportBuilders = reportBuilders;
    }
}
