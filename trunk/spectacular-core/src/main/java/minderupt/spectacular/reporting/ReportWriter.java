package minderupt.spectacular.reporting;

import minderupt.spectacular.data.model.Report;

import java.io.IOException;

/**
 *
 */
public interface ReportWriter {

    public void write(Report report) throws IOException;
    

}
