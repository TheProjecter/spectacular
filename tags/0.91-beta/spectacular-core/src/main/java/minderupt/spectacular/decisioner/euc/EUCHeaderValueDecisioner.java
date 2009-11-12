package minderupt.spectacular.decisioner.euc;

import minderupt.spectacular.data.model.Artifact;
import minderupt.spectacular.data.model.Decision;
import minderupt.spectacular.decisioner.Decisioner;

import java.util.List;

/**
 * Looks for particular words in the table header (i.e. flow, user action) which would
 * indicate that this is an executable use case.
 * 
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
