package minderupt.spectacular.spine.euc.steps;

import minderupt.spectacular.executor.euc.annotation.EUC;
import minderupt.spectacular.executor.euc.annotation.Flow;
import minderupt.spectacular.executor.euc.annotation.Expectation;
import org.apache.log4j.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Nov 5, 2009
 * Time: 7:44:32 PM
 * To change this template use File | Settings | File Templates.
 */
@EUC
public class EUCIntegrationSteps {

    private static Logger LOGGER = Logger.getLogger(EUCIntegrationSteps.class);


    @Flow("User navigates to home page")
    public void userNavigatesToHomePage() {

        LOGGER.fatal("userNavigatesToHomePage");    

    }

    @Expectation("User sees home page with articles")
    public void userSeesPageWithArticles() {
        LOGGER.fatal("userSeesPageWithArticles");
    }


}
