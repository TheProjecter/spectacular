package minderupt.spectacular.decisioner;

import minderupt.spectacular.data.model.Artifact;

import java.util.List;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Oct 16, 2009
 * Time: 4:43:46 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractDecisionerTest {
    
    protected Artifact createTwoColumnMultilineTable() {
        // set up 2 column multi line table
        Artifact artifact = new Artifact();
        List<String> headers = new LinkedList<String>();
        headers.add("HEADER 1");
        headers.add("HEADER 2");
        artifact.setHeaders(headers);

        // 2 columns 3 rows
        List<List<String>> data = new LinkedList<List<String>>();

        List<String> row = new LinkedList<String>();
        row.add("R1C1");
        row.add("R1C2");
        data.add(row);

        List<String> row2 = new LinkedList<String>();
        row2.add("R1C1");
        row2.add("R1C2");
        data.add(row2);

        List<String> row3 = new LinkedList<String>();
        row3.add("R1C1");
        row3.add("R1C2");
        data.add(row3);

        artifact.setTableContent(data);
        return artifact;
        
    }
}
