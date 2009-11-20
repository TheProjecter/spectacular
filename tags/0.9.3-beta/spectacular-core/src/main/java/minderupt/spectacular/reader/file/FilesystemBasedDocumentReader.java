package minderupt.spectacular.reader.file;

import minderupt.spectacular.reader.DocumentReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 *
 */
public class FilesystemBasedDocumentReader implements DocumentReader {

    private File documentFile = null;


    public FilesystemBasedDocumentReader() {

    }



    public String read(String location) throws FileNotFoundException {

        StringBuilder doc = new StringBuilder();

        this.documentFile = new File(location);
        if(!this.documentFile.exists()) {
            throw(new FileNotFoundException("Document given (" + location + ") does not exist."));
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.documentFile));

            String line = reader.readLine();
            while(line != null) {
                doc.append(line);
                doc.append(System.getProperty("line.separator"));
                line = reader.readLine();
            }

            reader.close();


        } catch (Exception e) {
            
        }

        return(doc.toString());
        
    }


}
