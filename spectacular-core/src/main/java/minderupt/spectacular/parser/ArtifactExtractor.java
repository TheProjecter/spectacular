package minderupt.spectacular.parser;

import minderupt.spectacular.data.model.Document;

/**
 *
 */
public interface ArtifactExtractor {

    public Document parse(String rawDocument) throws DocumentParseException;


}
