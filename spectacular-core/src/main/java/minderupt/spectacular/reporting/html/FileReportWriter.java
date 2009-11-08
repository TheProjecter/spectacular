package minderupt.spectacular.reporting.html;

import minderupt.spectacular.reporting.ReportWriter;
import minderupt.spectacular.data.model.Report;

import java.io.IOException;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Nov 8, 2009
 * Time: 11:16:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class FileReportWriter implements ReportWriter {

    private String basePath;


    public void write(Report report) throws IOException {


        String path = getBasePath() + "/TestResults.html";
        File file = new File(path);
        if(file.exists()) file.delete();
        if(!file.createNewFile() || !file.canWrite()) throw new IOException("Cannot write to file:  " + path);


        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(report.getReportText());
        writer.close();


    }


    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }
}
