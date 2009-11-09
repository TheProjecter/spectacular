package minderupt.spectacular.decisioner.fit;

import minderupt.spectacular.decisioner.Decisioner;
import minderupt.spectacular.data.model.Decision;
import minderupt.spectacular.data.model.Artifact;
import fit.Fixture;

import java.util.List;

/**
 * If the header value of this table is a classname and the classname is
 * of type fit.Fixture, this is definitely a FIT test.
 *
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
