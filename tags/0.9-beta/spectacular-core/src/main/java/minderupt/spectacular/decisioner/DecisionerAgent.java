package minderupt.spectacular.decisioner;

import minderupt.spectacular.data.model.Document;

/**
 * A decisioner agent acts as the front-man to the Spine and coordinates
 * all the activity and logic around what kind of artifacts have been
 * found in the document.
 * 
 */
public interface DecisionerAgent {

    public void addDecisioner(Decisioner decisioner);
    public Document decide(Document document); 



}
