package minderupt.spectacular.executor.euc.testone;

import minderupt.spectacular.executor.euc.annotation.Flow;
import minderupt.spectacular.executor.euc.annotation.EUC;

/**
 * Created by IntelliJ IDEA.
 * User: dowlinmi
 * Date: Oct 26, 2009
 * Time: 10:38:40 AM
 * To change this template use File | Settings | File Templates.
 */
@EUC
public class FlowAnnotatedClass {

    @Flow("This is my flow step")
    public void myFlowMethod() {

    }

}
