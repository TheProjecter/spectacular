package minderupt.spectacular.decisioner;

import minderupt.spectacular.data.model.Artifact;
import minderupt.spectacular.data.model.Decision;
import minderupt.spectacular.data.model.Document;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * A decisioner agent that chains together multiple Decisioners and then makes a decision based on
 * who gets the most votes.
 */
public class ChainedDecisionerAgent implements DecisionerAgent {

    private static Logger LOGGER = Logger.getLogger(ChainedDecisionerAgent.class);
    private List<Decisioner> decisioners = new LinkedList<Decisioner>();



    public void addDecisioner(Decisioner decisioner) {
        this.decisioners.add(decisioner);
    }

    public void setDecisioners(List<Decisioner> decisionerList) {
        this.decisioners.addAll(decisionerList);
    }

    public Document decide(Document document) {

        List<Artifact> artifactList = document.getArtifacts();
        for(Artifact artifact : artifactList) {

            // hold the votes
            Map<Integer, Integer> typeVotes = new HashMap<Integer, Integer>();

            // for each decisioner, pass in an artifact
            for(Decisioner decisioner : this.decisioners) {

                Decision vote = decisioner.decision(artifact);

                if(vote.getVote() == Artifact.ABSTAIN)
                    continue;

                if(typeVotes.get(vote) != null) {
                    int count = typeVotes.get(vote);
                    count += vote.getWeight();
                    typeVotes.put(vote.getVote(), count);
                } else {
                    typeVotes.put(vote.getVote(), vote.getWeight());
                }


            }

            // who got the most votes?
            int highCount = 0;
            int highType = Artifact.UNKNOWN;

            for(int type : typeVotes.keySet()) {

                if(typeVotes.get(type) > highCount) {
                    highType = type;
                    highCount = typeVotes.get(type);
                }

            }

            artifact.setArtifactType(highType);


        }



        return document;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
