package minderupt.spectacular.data.model;

import minderupt.spectacular.util.TableContentUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Oct 11, 2009
 * Time: 5:16:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class Artifact implements TabularContentAccessor<String> {

    //! This really needs to be refactored into an enum...
    public static final int ABSTAIN = -1;
    public static final int UNKNOWN = 0;
    public static final int BDD = 1;
    public static final int FIT = 2;
    public static final int EUC = 3;

    // i really should be using java5 enums here.  why did I not do that?
    private int artifactType = Artifact.UNKNOWN;

    private String rawArtifact;
    private List<String> headers;
    private List<List<String>> tableContent;

    private int startPosition = 0;
    private int endPosition = 0;

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

    public void setTableContent(List<List<String>> tableData) {
        this.tableContent = tableData;
    }

    public List<List<String>> getTableContent() {
        return(this.tableContent);
    }

    public void setArtifactType(int t) {
        this.artifactType = t;
    }

    public int getArtifactType() {
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
