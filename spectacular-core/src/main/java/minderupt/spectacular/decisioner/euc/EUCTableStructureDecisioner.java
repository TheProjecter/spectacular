package minderupt.spectacular.decisioner.euc;

import minderupt.spectacular.data.model.Artifact;
import minderupt.spectacular.data.model.Decision;
import minderupt.spectacular.decisioner.Decisioner;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Oct 16, 2009
 * Time: 4:15:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class EUCTableStructureDecisioner implements Decisioner {

    public Decision decision(Artifact artifact) {

        // test for >1 columns, >1 rows
        List<List<String>> data = artifact.getTableContent();

        int columns = 0;
        int rows = data.size();

        if(rows > 0) {
            columns = data.get(0).size();
        } else {

            // no data rows?  Not sure what this is
            return(new Decision(Artifact.ABSTAIN));

        }

        // 2 or more columns and 1 or more rows?  I vote EUC
        if(columns >= 2 && rows >= 1) {
            return(new Decision(Artifact.EUC, 1));
        }


        // other structure?  Not sure
        return(new Decision(Artifact.ABSTAIN));


    }





}
