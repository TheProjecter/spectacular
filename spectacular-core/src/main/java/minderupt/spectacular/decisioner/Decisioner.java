package minderupt.spectacular.decisioner;

import minderupt.spectacular.data.model.Artifact;
import minderupt.spectacular.data.model.Decision;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Oct 16, 2009
 * Time: 2:30:19 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Decisioner {

    public Decision decision(Artifact artifact);



}
