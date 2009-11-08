package minderupt.spectacular.data.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Oct 11, 2009
 * Time: 12:51:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class Document {

    private String rawDocument;
    private List<Artifact> testArtifactList = new LinkedList<Artifact>();

    public Document() {

    }

    public String getRawDocument() {
        return rawDocument;
    }

    public void setRawDocument(String rawDocument) {
        this.rawDocument = rawDocument;
    }

    public void addTestArtifact(Artifact testArtifact) {
        this.testArtifactList.add(testArtifact);
    }

    public List<Artifact> getArtifacts() {
        return(this.testArtifactList);
    }

    


}
