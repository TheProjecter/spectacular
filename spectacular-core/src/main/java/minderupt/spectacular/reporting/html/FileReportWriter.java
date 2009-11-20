package minderupt.spectacular.reporting.html;

import minderupt.spectacular.reporting.ReportWriter;
import minderupt.spectacular.data.model.Report;
import minderupt.spectacular.data.model.GlobalOptions;

import java.io.IOException;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 *
 */
public class FileReportWriter implements ReportWriter {



    public void write(GlobalOptions options, Report report) throws IOException {


        String path = options.getReportLocation();
        File file = new File(path);
        if(file.exists()) file.delete();
        if(!file.createNewFile() || !file.canWrite()) throw new IOException("Cannot write to file:  " + path);


        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(report.getReportText());
        writer.close();


    }


   
}
