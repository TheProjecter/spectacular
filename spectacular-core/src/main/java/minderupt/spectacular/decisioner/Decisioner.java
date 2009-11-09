package minderupt.spectacular.decisioner;

import minderupt.spectacular.data.model.Artifact;
import minderupt.spectacular.data.model.Decision;

/**
 * Implement this interface if you want to add logic to see what kind of
 * artifact was passed in.  It asks the question "what kind of artifact is this?" and
 * expects an answer of "I think this artifact is of type X and I'm willing to place
 * Y weight behind that decision."
 * 
 */
public interface Decisioner {

    public Decision decision(Artifact artifact);



}
