package minderupt.spectacular.reader;

import java.io.FileNotFoundException;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Oct 9, 2009
 * Time: 12:36:26 PM
 * To change this template use File | Settings | File Templates.
 */
public interface DocumentReader {

    public String read(String location) throws FileNotFoundException;

}
