package minderupt.spectacular.parser;

import minderupt.spectacular.data.model.Document;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Oct 11, 2009
 * Time: 12:49:56 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ArtifactExtractor {

    public Document parse(String rawDocument) throws DocumentParseException;


}
