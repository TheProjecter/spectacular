package minderupt.spectacular.spine.bdd.steps;

import org.jbehave.scenario.steps.Steps;
import org.jbehave.scenario.annotations.Given;
import org.apache.log4j.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Nov 6, 2009
 * Time: 6:12:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class BDDIntegrationSteps extends Steps {

    private static Logger LOGGER = Logger.getLogger(BDDIntegrationSteps.class);

    @Given("a user with login name of \"$loginName\"")
    public void givenUserWithLoginName(String loginName) {

        LOGGER.fatal("givenUserWithLoginName: (" + loginName + ")");



    }

    


}
