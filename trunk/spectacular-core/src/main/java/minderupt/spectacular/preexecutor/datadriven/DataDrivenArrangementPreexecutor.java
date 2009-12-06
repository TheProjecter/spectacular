package minderupt.spectacular.preexecutor.datadriven;

import minderupt.spectacular.preexecutor.Preexecutor;
import minderupt.spectacular.data.model.Document;
import minderupt.spectacular.data.model.Artifact;
import minderupt.spectacular.data.model.ArtifactType;

import java.util.List;

public class DataDrivenArrangementPreexecutor implements Preexecutor {

    public Document preprocess(Document document) throws Exception {

        // go through document
        // if encountering a DATADRIVEN artifact, delete from list and assign to
        // immediately preceeding test artifact of type EUC or BDD
        List<Artifact> artifactList = document.getArtifacts();
        int i = 0;
        for(Artifact artifact : artifactList) {

            if(!artifact.getArtifactType().equals(ArtifactType.DATADRIVEN)) {
                i++;
                continue;
            }

            // if we're here, we found a data driven artifact
            // find the previous artifact and assign it to them
            if(i == 0) continue;
            artifactList.get((i- 1)).setDataDrivenInstances(artifact);

            // delete data driven artifact from list
            artifactList.remove(i);

        }

        document.setArtifactList(artifactList);
        return(document);
        
    }
}
