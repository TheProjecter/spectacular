package minderupt.spectacular.executor.fit;

import fit.ColumnFixture;

/**
 * Created by IntelliJ IDEA.
 * User: dowlinmi
 * Date: Oct 20, 2009
 * Time: 9:53:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class SandboxOneFixture extends ColumnFixture {

    public String FirstName = null;
    public String LastName = null;
    public int Age = 0;
    public String Email = null;

    public boolean create() {

        if(FirstName == null || FirstName.length() == 0) return false;
        if(LastName == null || LastName.length() == 0) return false;
        if(Age < 18) return false;
        if(Email == null || Email.length() == 0) return false;

        return(true);


    }


}
