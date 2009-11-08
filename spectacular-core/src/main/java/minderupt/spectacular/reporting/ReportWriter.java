package minderupt.spectacular.reporting;

import minderupt.spectacular.data.model.Report;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Oct 21, 2009
 * Time: 11:37:46 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ReportWriter {

    public void write(Report report) throws IOException;
    

}
