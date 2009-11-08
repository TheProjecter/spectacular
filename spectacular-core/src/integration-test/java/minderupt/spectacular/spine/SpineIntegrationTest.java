package minderupt.spectacular.spine;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import minderupt.spectacular.data.model.GlobalOptions;

/**
 * Created by IntelliJ IDEA.
 * User: dowlinmi
 * Date: Oct 30, 2009
 * Time: 10:30:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class SpineIntegrationTest {

    // private static Server jettyServer = null;
/*
    @BeforeClass
    public static void startJetty() throws Exception {

        jettyServer = new Server();
        Connector connector = new SelectChannelConnector();
        connector.setPort(8080);
        jettyServer.setConnectors(new Connector[] { connector });

        WebAppContext webappContext = new WebAppContext();
        webappContext.setContextPath("/pebble");
        webappContext.setWar("C:\\Documents and Settings\\dowlinmi\\Desktop\\Internet Downloads\\pebble-2.4rc\\pebble.war");
        jettyServer.setHandler(webappContext);
        jettyServer.start();

    }

   */

    @Before
    public void setUp() throws Exception {


    }


    @Test
    public void testBasicIntegrationWithUseCasesOnly() throws Exception {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[] {"spring-context/integration-test-context.xml"});
        ComponoSpine spine = (ComponoSpine) applicationContext.getBean("spine");
        Assert.assertNotNull(spine);

        GlobalOptions options = new GlobalOptions();
        options.addEUCBasePackage("minderupt.spectacular.spine.spectacular.steps");

        spine.setSpecificationLocation("classpath:integration-spec-test/BasicIntegrationEUCOnly.html");
        spine.setGlobalOptions(options);

        spine.run();

        


    }


}
