package minderupt.spectacular.junit;

/**
 * Created by IntelliJ IDEA.
 * User: dowlinmi
 * Date: Nov 13, 2009
 * Time: 11:03:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class SampleSpectacularTestCase extends SpectacularTestCase {

    public void testSomeSpecification() throws Exception {

        spectacular("/path/to/spec", "/path/to/report");


    }


}
