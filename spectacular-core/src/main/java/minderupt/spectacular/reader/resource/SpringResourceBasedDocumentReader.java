package minderupt.spectacular.reader.resource;

import minderupt.spectacular.reader.DocumentReader;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.MalformedURLException;

/**
 * Created by IntelliJ IDEA.
 * User: dowlinmi
 * Date: Oct 30, 2009
 * Time: 11:40:15 AM
 * To change this template use File | Settings | File Templates.
 */
public class SpringResourceBasedDocumentReader implements DocumentReader {

    private static Logger LOGGER = Logger.getLogger(SpringResourceBasedDocumentReader.class);


    public String read(String location) throws FileNotFoundException {

        Resource fileResource = null;

        // what kind of resource?
        if (location.indexOf("classpath:") == 0) {
            // do we need to take out classpath?
            String cleanLocation = removeTag(location);
            fileResource = new ClassPathResource(cleanLocation);
        } else if(location.indexOf("http:") == 0 || location.indexOf("https:") == 0) {
            // url resource!
            try {
                fileResource = new UrlResource(location);
            } catch (MalformedURLException e) {
                throw(new FileNotFoundException("Unable create UrlResource to get file:  " + e));
            }
        }

        String fileContents = null;
        try {
            fileContents = loadFileContents(fileResource.getFile());
        } catch (Exception e) {
            LOGGER.error("Unable to load file contents from resource:  " + fileResource.getFilename(), e);
        }


        return (fileContents);
    }

    private String removeTag(final String location) {
        // classpath
        return(location.substring("classpath:".length()));
    }


    private String loadFileContents(File file) throws Exception {

        StringBuilder doc = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line = reader.readLine();
        while (line != null) {
            doc.append(line);
            doc.append(System.getProperty("line.separator"));
            line = reader.readLine();
        }

        reader.close();


        return (doc.toString());


    }


}
