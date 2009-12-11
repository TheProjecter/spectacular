package minderupt.spectacular.spine;

import org.junit.Test;
import org.junit.Assert;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import minderupt.spectacular.data.model.GlobalOptions;
import minderupt.spectacular.data.model.CommandLineGlobalOptions;

/**
 * Created by IntelliJ IDEA.
 * User: dowlinmi
 * Date: Oct 30, 2009
 * Time: 10:30:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class SpineIntegrationTest {



    @Test
    public void testBasicIntegrationWithUseCasesOnly() throws Exception {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[] {"default-spring-context/integration-test-context.xml"});
        SpectacularSpine spine = (SpectacularSpine) applicationContext.getBean("spine");
        Assert.assertNotNull(spine);

        GlobalOptions options = new CommandLineGlobalOptions();
        options.addFixture("minderupt.spectacular.spine.spectacular.steps");
        options.setReportLocation("./TestResults.html");

        spine.setSpecificationLocation("classpath:integration-spec-test/BasicIntegrationEUCOnly.html");
        spine.setGlobalOptions(options);

        spine.run();

        


    }


}
