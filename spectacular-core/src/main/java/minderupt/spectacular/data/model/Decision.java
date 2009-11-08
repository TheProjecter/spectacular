package minderupt.spectacular.data.model;

/**
 * Created by IntelliJ IDEA.
 * User: dowlinmi
 * Date: Oct 20, 2009
 * Time: 8:01:13 AM
 * To change this template use File | Settings | File Templates.
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
