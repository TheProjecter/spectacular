package minderupt.spectacular.preexecutor.agent;

import minderupt.spectacular.data.model.Document;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Dec 4, 2009
 * Time: 3:58:27 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Preexecutor {

    public Document preprocess(Document document) throws Exception;

}
