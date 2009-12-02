package minderupt.spectacular.executor.bdd.testone;

import org.jbehave.scenario.steps.Steps;
import org.jbehave.scenario.annotations.Given;
import org.apache.log4j.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: dowlinmi
 * Date: Oct 28, 2009
 * Time: 7:28:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class ExampleStepClass extends Steps {

    private static Logger LOGGER = Logger.getLogger(ExampleStepClass.class);

    @Given("some kind of setup")
    public void givenSomeKindOfSetup() {
        LOGGER.info("givenSomeKindOfSetup()"); 
    }

    
}
