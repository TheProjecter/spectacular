package minderupt.spectacular.data.model;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: dowlinmi
 * Date: Dec 11, 2009
 * Time: 10:53:44 AM
 * To change this template use File | Settings | File Templates.
 */
public interface GlobalOptions {
    List<String> getFixtures();

    void addFixture(String basePackages);

    String getReportLocation();

    void setReportLocation(String reportLocation);
}
