package minderupt.spectacular.data.model;

/**
 * Decision encapsulates the decision made by a Decisioner as to whether
 * an artifact should be executed by a particular Executor
 */
public class Decision {

    private ArtifactType vote;
    private int weight;

    public Decision() {
        this.vote = ArtifactType.UNKNOWN;
        this.weight = 1;
    }

    public Decision(ArtifactType voteType) {
        this.vote = voteType;
        this.weight = 1;
    }

    public Decision(ArtifactType voteType, int weight) {
        this.vote = voteType;
        this.weight = weight;
    }

    public ArtifactType getVote() {
        return vote;
    }

    public void setVote(ArtifactType vote) {
        this.vote = vote;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
