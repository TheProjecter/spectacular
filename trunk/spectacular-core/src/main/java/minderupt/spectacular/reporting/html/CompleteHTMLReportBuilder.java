package minderupt.spectacular.reporting.html;

import minderupt.spectacular.reporting.ReportBuilder;
import minderupt.spectacular.reporting.ArtifactReportBuilder;
import minderupt.spectacular.data.model.Report;
import minderupt.spectacular.data.model.Document;
import minderupt.spectacular.data.model.ArtifactType;
import minderupt.spectacular.executor.ArtifactExecutionResults;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: dowlinmi
 * Date: Nov 13, 2009
 * Time: 8:36:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class CompleteHTMLReportBuilder implements ReportBuilder {

    private Map<ArtifactType, ArtifactReportBuilder> reportBuilders;

    public Report build(Document document, List<ArtifactExecutionResults> results) {

        String originalDocument = document.getRawDocument();

        List<String> reportSegments = new LinkedList<String>();
        int previousStartPosition = 0;
        int previousEndPosition = 0;
        for (ArtifactExecutionResults result : results) {

            // should be in order
            // get the start position
            int startPosition = result.getOriginalArtifact().getStartPosition();

            // get everything from the previous end position to the current start position
            String originalSegment = originalDocument.substring(previousEndPosition, startPosition);
            reportSegments.add(originalSegment);

            // execute report builder on artifact
            ArtifactReportBuilder builder = getReportBuilders().get(result.getOriginalArtifact().getArtifactType());
            if (builder == null) {
                reportSegments.add("<h1>NO REPORT BUILDER DEFINED FOR ARTIFACT TYPE (" + result.getOriginalArtifact().getArtifactType() + ")</h1>");
                previousStartPosition = startPosition;
                previousEndPosition = result.getOriginalArtifact().getEndPosition();
                continue;
            }

            String report = builder.build(result);
            reportSegments.add(report);

            previousStartPosition = startPosition;
            previousEndPosition = result.getOriginalArtifact().getEndPosition();


        }

        // take everything from the last end position and append
        String lastSegment = originalDocument.substring(previousEndPosition);
        reportSegments.add(lastSegment);

        String finalReportText = combineSegments(reportSegments);
        Report finalReport = new Report();
        finalReport.setReportText(finalReportText);
        return(finalReport);
        
    }

    private String combineSegments(List<String> reportSegments) {

        StringBuilder builder = new StringBuilder();
        for(String segment : reportSegments) {
            builder.append(segment);
        }

        return(builder.toString());

    }


    public Map<ArtifactType, ArtifactReportBuilder> getReportBuilders() {
        return reportBuilders;
    }

    public void setReportBuilders(Map<ArtifactType, ArtifactReportBuilder> reportBuilders) {
        this.reportBuilders = reportBuilders;
    }


}
