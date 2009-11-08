package minderupt.spectacular.decisioner.fit;

import minderupt.spectacular.decisioner.Decisioner;
import minderupt.spectacular.data.model.Decision;
import minderupt.spectacular.data.model.Artifact;
import fit.Fixture;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Nov 7, 2009
 * Time: 3:38:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class FITHeaderClassIsFixtureDecisioner implements Decisioner {

    public Decision decision(Artifact artifact) {

        Decision decision = new Decision();

        // check header (first row, first column) for class name
        List<String> header = artifact.getHeaders();
        if(header == null) return(new Decision(Artifact.ABSTAIN));

        String className = header.get(0);
        if(className == null) return(new Decision(Artifact.ABSTAIN));

        // instantiate the class by name
        Object fixture = null;

        try {
            fixture = Class.forName(className).newInstance();
        } catch (Exception e) {
            decision.setVote(Artifact.ABSTAIN);
            return(decision);
        }

        if(fixture instanceof Fixture) {
            decision.setVote(Artifact.FIT);
            decision.setWeight(5);
        }

        return(decision);


    }
}
