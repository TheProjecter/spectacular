package minderupt.spectacular.decisioner.datadriven;

import minderupt.spectacular.decisioner.Decisioner;
import minderupt.spectacular.data.model.Decision;
import minderupt.spectacular.data.model.Artifact;
import minderupt.spectacular.data.model.ArtifactType;

import java.util.List;

public class DataDrivenTableHeaderDecisioner implements Decisioner {

    public Decision decision(Artifact artifact) {


        // look at header for keyword "Data", "Data Driven", "Data-Driven"
        Decision decision = new Decision(ArtifactType.ABSTAIN);
        List<String> headerRow = artifact.getHeaders();
        if(headerRow == null || headerRow.size() == 0 || headerRow.get(0) == null) return(decision);

        String title = headerRow.get(0);
        if(title.indexOf("Data") == 0 ||
                title.indexOf("Data Driven") == 0 ||
                title.indexOf("Data-Driven") == 0) {

            decision.setVote(ArtifactType.DATADRIVEN);
            decision.setWeight(10);

        }
        

        return(decision);


    }
}
