package minderupt.spectacular.data.model;

/**
 * Decision encapsulates the decision made by a Decisioner as to whether
 * an artifact should be executed by a particular Executor
 */
public class Decision {

    private int vote;
    private int weight;

    public Decision() {
        this.vote = Artifact.UNKNOWN;
        this.weight = 1;
    }

    public Decision(int voteType) {
        this.vote = voteType;
        this.weight = 1;
    }

    public Decision(int voteType, int weight) {
        this.vote = voteType;
        this.weight = weight;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
