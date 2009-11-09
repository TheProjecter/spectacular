package minderupt.spectacular.reader.file;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Oct 9, 2009
 * Time: 12:56:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class FilesystemBasedDocumentReaderTest {

    private String baseResourceDir = "src/test/resources";


    @Before
    public void setUp() throws Exception {

        // where are we being run?
        File currentDir = new File(".");
        File[] filenameList = currentDir.listFiles();

        // if we're in the top-level dir...
        if(inTopLevel(filenameList)) {
            this.baseResourceDir = "spectacular-core/src/test/resource";
        } 


    }



    @Test
    public void testFileNotFound() throws Exception {

        try {
            FilesystemBasedDocumentReader reader = new FilesystemBasedDocumentReader();
            reader.read(this.baseResourceDir + "/filenotfound.html");
            fail("Did not throw file not found exception as expected.");
        } catch(FileNotFoundException fnfe) {
            assertNotNull(fnfe);
        }


    }

    @Test
    public void testFileFoundContentRead() throws Exception {

        FilesystemBasedDocumentReader reader = new FilesystemBasedDocumentReader();
        String doc = reader.read(this.baseResourceDir + "/testdocs/RealDoc1.html");

        assertNotNull(doc);
        assertEquals("<html>hello</html>" + System.getProperty("line.separator"), doc);


    }

    private boolean inTopLevel(File[] filenameList) {

        for(File filename : filenameList) {
            System.out.println("FILENAME:  " + filename.getName());
            if(filename.getName().indexOf("spectacular-core") >= 0) {
                return(true);
            }
        }

        return false;  
    }

    


}
