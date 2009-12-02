package minderupt.spectacular.spike;

import fit.ColumnFixture;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Oct 16, 2009
 * Time: 6:10:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class SomethingFixture extends ColumnFixture {

    public String name;
    public int age = 0;
    public String city;

    public boolean something() {
        System.out.println("FIXTURE:  " + name + " / " + age + " / " + city);
        return(true);
    }


}
