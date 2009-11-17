package minderupt.spectacular.reporting.html.bdd;

import minderupt.spectacular.reporting.ArtifactReportBuilder;
import minderupt.spectacular.executor.ArtifactExecutionResults;

/**
 *
 */
public class JBehaveArtifactReportBuilderHTML implements ArtifactReportBuilder {

    public String build(ArtifactExecutionResults results) {

        // get results
        String bddResults = results.get(1,0);
        bddResults = bddResults.replaceAll("\n", "<br />");

        StringBuilder bddTable = new StringBuilder();
        bddTable.append("<table border=\"1\">\n");

        // header column with class name
        bddTable.append("<tr>");
        bddTable.append("<td>");
        bddTable.append(results.get(0, 0));
        bddTable.append("</td>");
        bddTable.append("</tr>\n");

        // now, the results
        bddTable.append("<tr>");
        bddTable.append("<td>");
        bddTable.append(bddResults);
        bddTable.append("</td>");
        bddTable.append("</tr>\n");

        bddTable.append("</table>\n");

        return(bddTable.toString());

    }
}
