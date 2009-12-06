package minderupt.spectacular.data.model;

import java.util.LinkedList;
import java.util.List;

/**
 * A document is a collection of Artifacts and all the other stuff used to support
 * the artifacts.  I.E. Your Requirements
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

    public void setArtifactList(List<Artifact> artifactList) {
        this.testArtifactList = artifactList;
    }

    


}
