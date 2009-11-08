package minderupt.spectacular.spine.fit;

import fit.ColumnFixture;
import org.apache.log4j.Logger;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Nov 7, 2009
 * Time: 3:51:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class FITIntegrationFixture extends ColumnFixture {

    private static Logger LOGGER = Logger.getLogger(FITIntegrationFixture.class);

    public String Name;
    public String DateOfBirth;
    public String Email;

    public boolean create() {

        if(LOGGER.isInfoEnabled()) LOGGER.info("Executing create()");

        Date date = new Date(this.DateOfBirth);
        if(date.after(new Date("11/17/1980"))) {
            return(false);
        }


        return(true);
    }


}
