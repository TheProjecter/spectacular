package minderupt.spectacular.executor.euc.testthree;

import minderupt.spectacular.executor.euc.annotation.EUC;
import minderupt.spectacular.executor.euc.annotation.Flow;
import minderupt.spectacular.executor.euc.annotation.Expectation;
import minderupt.spectacular.executor.euc.Context;

/**
 * Created by IntelliJ IDEA.
 * User: dowlinmi
 * Date: Nov 5, 2009
 * Time: 7:54:54 AM
 * To change this template use File | Settings | File Templates.
 */
@EUC
public class BasicEUCArtifactFlowsAndExpectations {

    @Flow("User does ${verb} basic")
    public void userDoesSomethingBasic(String verb) {

        System.out.println("****************** VERB: " + verb);


    }

    @Flow("User ${verb} some test button")
    public void userDoesSomethingWithTestButton(Context context, String verb) {

        System.out.println("****************** TEST BUTTON VERB: " + verb);
        System.out.println("****************** CONTEXT:  " + context);
        context.put("MICHAEL", "Dowling");

    }

    @Expectation("User sees a basic view")
    public void userSeesBasicView() {
        System.out.println("****************** userSeesBasicView");
    }


}
