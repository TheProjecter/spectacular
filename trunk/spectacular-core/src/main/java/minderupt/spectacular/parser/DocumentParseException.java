package minderupt.spectacular.parser;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Oct 11, 2009
 * Time: 12:52:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class DocumentParseException extends Exception {


    public DocumentParseException() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public DocumentParseException(String s) {
        super(s);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public DocumentParseException(String s, Throwable throwable) {
        super(s, throwable);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public DocumentParseException(Throwable throwable) {
        super(throwable);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
