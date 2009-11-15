package minderupt.spectacular.junit;

import junit.framework.TestCase;
import minderupt.spectacular.spine.SpectacularSpine;

/**
 * Created by IntelliJ IDEA.
 * User: dowlinmi
 * Date: Nov 13, 2009
 * Time: 11:03:11 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class SpectacularTestCase extends TestCase {

    private SpectacularSpine specSpine;

    public void setUp() throws Exception {
        specSpine = new SpectacularSpine();
    }



    protected void configureSpine() {
        
    }


    protected void spectacular(String s, String s1) {


    }
}
