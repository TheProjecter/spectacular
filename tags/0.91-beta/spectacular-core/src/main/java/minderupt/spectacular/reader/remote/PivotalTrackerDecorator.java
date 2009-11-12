package minderupt.spectacular.reader.remote;

import minderupt.spectacular.reader.DocumentReader;

import java.io.FileNotFoundException;

/**
 * Decorates the WebBasedDocumentReader by altering the URL with PivotalTracker specific
 * values then delegating to the web reader.
 * 
 */
public class PivotalTrackerDecorator implements DocumentReader {

    private WebBasedDocumentReader webDocumentReader;



    public PivotalTrackerDecorator(WebBasedDocumentReader webDocReader) {
        this.webDocumentReader = webDocReader;
    }


    public String read(String location) throws FileNotFoundException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
