package minderupt.spectacular.util;

import java.util.*;

/**
* The user enters text into a search box. This class is used
* to parse that text into specific search terms (or tokens).
* It eliminates common words, and allows for the quoting of text, using
* double quotes.
*/
public final class TokenParser {


  /**
  * @param aSearchText is non-null, but may have no content,
  * and represents what the user has input in a search box.
  */
  public TokenParser( String aSearchText ) {
    if ( aSearchText == null ) {
      throw new IllegalArgumentException("Search Text cannot be null.");
    }
    fSearchText = aSearchText;
  }

  /**
  * Parse the user's search box input into a Set of String tokens.
  *
  * @return Set of Strings, one for each word in fSearchText; here "word"
  * is defined as either a lone word surrounded by whitespace, or as a series
  * of words surrounded by double quotes, "like this"; also, very common
  * words (and, the, etc.) do not qualify as possible search targets.
  */
  public List<String> parse() {
    List<String> result = new LinkedList<String>();

    boolean returnTokens = true;
    String currentDelims = fWHITESPACE_AND_QUOTES;
    StringTokenizer parser = new StringTokenizer(
      fSearchText,
      currentDelims,
      returnTokens
    );

    String token = null;
    while ( parser.hasMoreTokens() ) {
      token = parser.nextToken(currentDelims);
      if ( !isDoubleQuote(token) ){
        addNonTrivialWordToResult( token, result );
      }
      else {
        currentDelims = flipDelimiters(currentDelims);
      }
    }
    return result;
  }

  // PRIVATE //
  private String fSearchText;
  private static final String fDOUBLE_QUOTE = "\"";

  //the parser flips between these two sets of delimiters
  private static final String fWHITESPACE_AND_QUOTES = " \t\r\n\"";
  private static final String fQUOTES_ONLY ="\"";





  private boolean textHasContent(String aText) {
    return (aText != null) && (!aText.trim().equals(""));
  }

  private void addNonTrivialWordToResult( String aToken, List<String> aResult ){
    if ( textHasContent(aToken)) {
      aResult.add( aToken.trim() );
    }
  }

  private boolean isDoubleQuote( String aToken ){
    return aToken.equals(fDOUBLE_QUOTE);
  }

  private String flipDelimiters( String aCurrentDelims ) {
    String result = null;
    if ( aCurrentDelims.equals(fWHITESPACE_AND_QUOTES) ) {
      result = fQUOTES_ONLY;
    }
    else {
      result = fWHITESPACE_AND_QUOTES;
    }
    return result;
  }
}
