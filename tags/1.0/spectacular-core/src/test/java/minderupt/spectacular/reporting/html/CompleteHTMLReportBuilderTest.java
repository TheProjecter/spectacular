package minderupt.spectacular.reporting.html;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import minderupt.spectacular.executor.ArtifactExecutionResults;
import minderupt.spectacular.data.model.Artifact;
import minderupt.spectacular.data.model.ArtifactType;
import minderupt.spectacular.data.model.Document;
import minderupt.spectacular.data.model.Report;
import minderupt.spectacular.reporting.ArtifactReportBuilder;

import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: dowlinmi
 * Date: Nov 13, 2009
 * Time: 8:39:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class CompleteHTMLReportBuilderTest {


    @Test
    public void testInsertsSegmentsInCorrectPositions() throws Exception {

        // create 3 artifacts
        Artifact artifact = mock(Artifact.class);
        when(artifact.getStartPosition()).thenReturn(12);
        when(artifact.getEndPosition()).thenReturn(16);
        when(artifact.getArtifactType()).thenReturn(ArtifactType.EUC);

        List<ArtifactExecutionResults> results = new LinkedList<ArtifactExecutionResults>();
        ArtifactExecutionResults result = mock(ArtifactExecutionResults.class);
        when(result.getOriginalArtifact()).thenReturn(artifact);
        results.add(result);

        ArtifactReportBuilder builder = mock(ArtifactReportBuilder.class);
        when(builder.build(result)).thenReturn("THAT");
        HashMap<ArtifactType, ArtifactReportBuilder> builderMap = new HashMap<ArtifactType, ArtifactReportBuilder>();
        builderMap.put(ArtifactType.EUC, builder);

        String base = prepareBaseDocument();
        Document document = mock(Document.class);
        when(document.getRawDocument()).thenReturn(base);

        CompleteHTMLReportBuilder reportBuilder = new CompleteHTMLReportBuilder();
        reportBuilder.setReportBuilders(builderMap);
        Report report = reportBuilder.build(document, results);

        assertNotNull(report);
        assertEquals("<html><body>THAT document is going to change.</body></html>", report.getReportText());






    }


    private String prepareBaseDocument() {

        String document = "<html><body>This document is going to change.</body></html>";
        return(document);

    }


}
