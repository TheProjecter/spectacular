package minderupt.spectacular.executor.euc.testtwo;

import minderupt.spectacular.executor.euc.annotation.Flow;
import minderupt.spectacular.executor.euc.annotation.Expectation;
import minderupt.spectacular.executor.euc.annotation.EUC;

/**
 * Created by IntelliJ IDEA.
 * User: dowlinmi
 * Date: Oct 28, 2009
 * Time: 6:41:12 AM
 * To change this template use File | Settings | File Templates.
 */
@EUC
public class FlowAndExpectationAnnotatedClass {

    @Flow("Something.")
    public void myFlowMethodOne() {
        // myFlowMethodOne
    }

    @Flow("A pattern to ${recognize}")
    public void myFlowMethodTwoWithArgument() {
        //
    }

    @Expectation("An expectation for sure.")
    public void myExpectationMethod() {

    }

    @Expectation("Expect nothing but ${argumentative} coworkers.")
    public void myExpectationMethodWithArgument() {
        
    }



}
