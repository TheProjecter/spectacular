package minderupt.spectacular.preexecutor.agent;

import minderupt.spectacular.data.model.Document;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Dec 5, 2009
 * Time: 2:10:30 PM
 * To change this template use File | Settings | File Templates.
 */
public interface PreexecutorAgent {

    public Document preprocessDocument(Document document) throws Exception;

}
