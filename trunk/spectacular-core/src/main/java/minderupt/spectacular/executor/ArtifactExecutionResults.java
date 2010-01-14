package minderupt.spectacular.executor;

import minderupt.spectacular.data.model.Artifact;
import minderupt.spectacular.data.model.TabularContentAccessor;
import minderupt.spectacular.util.TableContentUtil;

import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class ArtifactExecutionResults implements TabularContentAccessor<String> {

    public static final String LABEL_PASSED = "(PASSED)";
    public static final String LABEL_PENDING = "(PENDING)";
    public static final String LABEL_FAILED = "(FAILED)";


    private boolean pass;
    private String rawResults;
    private List<List<String>> tabularResults;
    TableContentUtil<String> tableUtil = new TableContentUtil<String>(); 
    private Artifact originalArtifact;


    public ArtifactExecutionResults() {
        this.tabularResults = new LinkedList<List<String>>();
        this.rawResults = "";
    }

    public String getRawResults() {
        return rawResults;
    }

    public void setRawResults(String rawResults) {
        this.rawResults = rawResults;
    }

    public List<List<String>> getTabularResults() {
        return tabularResults;
    }

    public void setTabularResults(List<List<String>> tabularResults) {
        this.tabularResults = tabularResults;
    }

    public Artifact getOriginalArtifact() {
        return originalArtifact;
    }

    public void setOriginalArtifact(Artifact originalArtifact) {
        this.originalArtifact = originalArtifact;
    }

    public String get(int row, int column) {

        String content = this.tableUtil.getContent(row, column, this.tabularResults);
        return(content);
        

    }

    public void put(int row, int column, String content) {
        this.tableUtil.putContent(row, column, content, this.tabularResults);
    }

    public int getRowCount() {
        return(this.tabularResults.size());
    }


    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    public void printTableContents() {
        this.tableUtil.printTableContents(this.tabularResults);
    }


}
