package minderupt.spectacular.data.model;

import minderupt.spectacular.util.TableContentUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * An Artifact is a container for tests, stored internally as a
 * table structure with an optional header value
 * 
 */
public class Artifact implements TabularContentAccessor<String> {

    private ArtifactType artifactType = ArtifactType.UNKNOWN;

    private String rawArtifact;
    private List<String> headers;
    private List<List<String>> tableContent;

    private int startPosition = 0;
    private int endPosition = 0;

    private Artifact dataDrivenInstances;

    private TableContentUtil<String> tableUtil = new TableContentUtil<String>();


    public int getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    public int getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(int endPosition) {
        this.endPosition = endPosition;
    }

    public String getRawArtifact() {
        return rawArtifact;
    }

    public void setRawArtifact(String rawArtifact) {
        this.rawArtifact = rawArtifact;
    }



    public void setHeaders(List<String> headerColumns) {
        this.headers = headerColumns;
    }

    public List<String> getHeaders() {
        return(this.headers);
    }

    public void setDataDrivenInstances(Artifact instance) {
        this.dataDrivenInstances = instance;
    }

    public Artifact getDataDrivenInstances() {
        return(this.dataDrivenInstances);
    }

    public void setTableContent(List<List<String>> tableData) {
        this.tableContent = tableData;
    }

    public List<List<String>> getTableContent() {
        return(this.tableContent);
    }

    public void setArtifactType(ArtifactType t) {
        this.artifactType = t;
    }

    public ArtifactType getArtifactType() {
        return(this.artifactType);
    }


    // need a much better way to deal with table content than
    // returning lists of lists.  Ugh!

    public String get(int row, int column) {

        String cellData = tableUtil.getContent(row, column, this.getTableContent());
        return cellData;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void put(int row, int column, String content) {

        if(this.tableContent == null) this.tableContent = new LinkedList<List<String>>();
        tableUtil.putContent(row, column, content, getTableContent());

    }

    public int getRowCount() {
        return(getTableContent().size());
    }
}
