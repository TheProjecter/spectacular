package minderupt.spectacular.decisioner;

import minderupt.spectacular.data.model.Document;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Oct 16, 2009
 * Time: 2:32:14 PM
 * To change this template use File | Settings | File Templates.
 */
public interface DecisionerAgent {

    public void addDecisioner(Decisioner decisioner);
    public Document decide(Document document); 



}
