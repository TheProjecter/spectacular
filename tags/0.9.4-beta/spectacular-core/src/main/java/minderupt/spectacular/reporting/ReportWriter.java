package minderupt.spectacular.reporting;

import minderupt.spectacular.data.model.Report;
import minderupt.spectacular.data.model.GlobalOptions;

import java.io.IOException;

/**
 *
 */
public interface ReportWriter {

    public void write(GlobalOptions options, Report report) throws IOException;
    

}
