package minderupt.spectacular.reporting.html.euc;

import minderupt.spectacular.executor.ArtifactExecutor;
import minderupt.spectacular.reporting.ArtifactReportBuilder;
import minderupt.spectacular.executor.ArtifactExecutionResults;

/**
 *
 */
public class EUCArtifactReportBuilderHTML implements ArtifactReportBuilder {



    private String passedTestColor;
    private String pendingTestColor;
    private String failedTestColor;
    private String notPerformedTestColor;
    


    public String build(ArtifactExecutionResults results) {

        StringBuilder report = new StringBuilder();
        report.append("<table border=\"1\"> \n");
        for(int i = 0 ; i < results.getRowCount() ; i++) {
            int c = 0;
            report.append("<tr>");
            String cell = results.get(i, c);
            while(cell != null) {

                String cellStyle = "";
                if(cell.indexOf(ArtifactExecutor.RESULT_SUCCESS) >= 0) cellStyle = "style=\"background-color: " + getPassedTestColor() + "\"";
                if(cell.indexOf(ArtifactExecutor.RESULT_PENDING) >= 0) cellStyle = "style=\"background-color: " + getPendingTestColor() + "\"";
                if(cell.indexOf(ArtifactExecutor.RESULT_FAIL) >= 0) cellStyle = "style=\"background-color: " + getFailedTestColor() + "\"";
                if(cell.indexOf(ArtifactExecutor.RESULT_NOT_PERFORMED) >= 0) cellStyle = "style=\"background-color: " + getNotPerformedTestColor() + "\"";

                if(cellStyle.length() == 0) {
                    report.append("<td>");
                } else {
                    report.append("<td " + cellStyle + ">");
                }

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

    public String getPassedTestColor() {
        return passedTestColor;
    }

    public void setPassedTestColor(String passedTestColor) {
        this.passedTestColor = passedTestColor;
    }

    public String getPendingTestColor() {
        return pendingTestColor;
    }

    public void setPendingTestColor(String pendingTestColor) {
        this.pendingTestColor = pendingTestColor;
    }

    public String getFailedTestColor() {
        return failedTestColor;
    }

    public void setFailedTestColor(String failedTestColor) {
        this.failedTestColor = failedTestColor;
    }

    public String getNotPerformedTestColor() {
        return notPerformedTestColor;
    }

    public void setNotPerformedTestColor(String notPerformedTestColor) {
        this.notPerformedTestColor = notPerformedTestColor;
    }
}
