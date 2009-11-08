package minderupt.spectacular.spike;

import org.junit.Test;
import fit.Parse;
import fit.Fixture;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Oct 16, 2009
 * Time: 5:49:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class SpikeUseFITTest {

    @Test
    public void testUseFitForTesting() throws Exception {

        String tableHtml = "<html><body>" +
                           "<table>" +
                           "<tr><td>minderupt.spectacular.spike.SomethingFixture</td><td></td><td></td></tr>" +
                           "<tr><td>name</td><td>age</td><td>city</td><td>something()</td></tr>" +
                           "<tr><td>Michael Dowling</td><td>32</td><td>San Francisco</td><td></td></tr>" +
                           "</table>" +
                           "</body></html>";


        Parse tables = new Parse(tableHtml, new String[] {"table", "tr", "td"});
        Fixture fixture = new Fixture();
        fixture.doTables(tables);

        
        tables.print(new PrintWriter(System.out));
        // tables.print(new PrintWriter(new BufferedWriter(new FileWriter("what.html"))));


    }


}
