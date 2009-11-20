package minderupt.spectacular.reporting.html.euc;

import minderupt.spectacular.reporting.ArtifactReportBuilder;
import minderupt.spectacular.executor.ArtifactExecutionResults;

/**
 *
 */
public class EUCArtifactReportBuilderHTML implements ArtifactReportBuilder {



    private String passedTestStyle;
    private String pendingTestStyle;
    private String failedTestStyle;
    


    public String build(ArtifactExecutionResults results) {

        StringBuilder report = new StringBuilder();
        report.append("<table border=\"1\"> \n");
        for(int i = 0 ; i < results.getRowCount() ; i++) {
            int c = 0;
            report.append("<tr>");
            String cell = results.get(i, c);
            while(cell != null) {

                report.append("<td>");
                report.append(cell);
                report.append("</td>");
                c++;

                cell = results.get(i, c);

            }
            report.append("</tr> \n");

        }
        report.append("</table>");


        return report.toString();

    }
}
