package minderupt.spectacular.decisioner.bdd;

import minderupt.spectacular.data.model.Artifact;
import minderupt.spectacular.data.model.Decision;
import minderupt.spectacular.data.model.ArtifactType;
import minderupt.spectacular.decisioner.Decisioner;

import java.util.List;

/**
 * Looks for common keywords in the data cell i.e. Given/When/Then
 *
 */
public class JBehaveBDDKeywordDecisioner implements Decisioner {

    public Decision decision(Artifact artifact) {

        Decision decision = new Decision();
        int weight = 0;

        // check for keywords in the data
        List<List<String>> data = artifact.getTableContent();
        if(data == null) return(new Decision(ArtifactType.ABSTAIN));
        String text = data.get(0).get(0);  // first row, first cell

        if(text == null) return(new Decision(ArtifactType.ABSTAIN));
        if(text.indexOf("Given") >= 0) weight++;
        if(text.indexOf("When") >= 0) weight++;
        if(text.indexOf("Then") >= 0) weight++;


        decision.setVote(ArtifactType.BDD);
        decision.setWeight(weight);


        return decision;  
    }

  
}
