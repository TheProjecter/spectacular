package minderupt.spectacular.reader.remote;

import minderupt.spectacular.reader.DocumentReader;

import java.io.*;
import java.net.MalformedURLException;

import org.springframework.core.io.UrlResource;
import org.apache.log4j.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Nov 8, 2009
 * Time: 10:11:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class WebBasedDocumentReader implements DocumentReader {

    private static Logger LOGGER = Logger.getLogger(WebBasedDocumentReader.class);

    public String read(String location) throws FileNotFoundException {

        UrlResource urlResource = null;
        try {
            urlResource = new UrlResource(location);
        } catch(MalformedURLException mue) {
            LOGGER.error("Could not parse URL provided (" + location + ")", mue);
            return(null);
        }


        StringBuilder fileContents = new StringBuilder();
        BufferedReader reader = null;


        try {
            reader = new BufferedReader(new InputStreamReader(urlResource.getInputStream()));
            String line = reader.readLine();
            while(line != null) {
                fileContents.append(line);
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            LOGGER.error("Unable to get contents of file at (" + location + ")", e);
            return(null);
        }


        return(fileContents.toString());

    }
}
