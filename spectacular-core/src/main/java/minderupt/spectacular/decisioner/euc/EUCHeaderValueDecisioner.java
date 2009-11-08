package minderupt.spectacular.decisioner.euc;

import minderupt.spectacular.data.model.Artifact;
import minderupt.spectacular.data.model.Decision;
import minderupt.spectacular.decisioner.Decisioner;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Oct 16, 2009
 * Time: 4:41:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class EUCHeaderValueDecisioner implements Decisioner {

    public Decision decision(Artifact artifact) {

        
        // looking for words "Flow", "User Action" if a single columns header
        List<String> header = artifact.getHeaders();
        if(header == null)
            return(new Decision(Artifact.ABSTAIN));

        if(header.size() == 1) {

            // flow
            if(header.get(0).toLowerCase().indexOf("flow") >= 0)
                return(new Decision(Artifact.EUC, 5));

            // User Action
            if(header.get(0).toLowerCase().indexOf("user action") >= 0)
                return(new Decision(Artifact.EUC, 3));
            
        }


        return(new Decision(Artifact.ABSTAIN));


    }
}
