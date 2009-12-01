package minderupt.spectacular.data.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Passed in to most Spectacular modules and who's data is set mainly by
 * command-line parameters.
 */
public class GlobalOptions {

    private List<String> eucBasePackages = new LinkedList<String>();
    private String reportLocation;

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
}
