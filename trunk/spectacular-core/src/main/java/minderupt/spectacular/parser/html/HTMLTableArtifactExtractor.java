package minderupt.spectacular.parser.html;

import minderupt.spectacular.data.model.Artifact;
import minderupt.spectacular.data.model.Document;
import minderupt.spectacular.parser.ArtifactExtractor;
import minderupt.spectacular.parser.DocumentParseException;
import org.apache.log4j.Logger;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class HTMLTableArtifactExtractor implements ArtifactExtractor {

    private static Logger LOGGER = Logger.getLogger(HTMLTableArtifactExtractor.class);
    private static final String BEGIN_BRACE = "<";
    private static final String END_BRACE = ">";

    private Pattern htmlBracePattern = Pattern.compile("<.*?>", Pattern.DOTALL);
    private Pattern multipleSpacesPattern = Pattern.compile(" {2,}", Pattern.DOTALL);


    public Document parse(String rawDocument) throws DocumentParseException {

        Document doc = new Document();
        doc.setRawDocument(rawDocument);


        try {
            List<TableTag> tableTagList = extractTables(rawDocument);

            for(TableTag tag : tableTagList) {

                // each table is an artifact
                Artifact tableArtifact = new Artifact();

                // get the position of the artifact in the document
                int startPosition = tag.getStartPosition();
                int endPosition = tag.getEndTag().getEndPosition(); // need to get the ENDING TAG'S position

                tableArtifact.setStartPosition(startPosition);
                tableArtifact.setEndPosition(endPosition);
                tableArtifact.setRawArtifact(rawDocument.substring(startPosition, endPosition));

                
                // grab header (1st row), if present
                TableRow[] rows = tag.getRows();
                if(rows.length > 1) {
                    // assume first row is a header
                    List<String> headerList = new LinkedList<String>();
                    TableColumn[] headerColumns = rows[0].getColumns();
                    for(TableColumn headerColumn : headerColumns) {
                        String cleanData = removeMarkup(headerColumn.getChildrenHTML());
                        headerList.add(cleanData);
                    }

                    tableArtifact.setHeaders(headerList);

                    // rest of table is data
                    List<List<String>> data = new LinkedList<List<String>>();
                    for(int i = 1 ; i < rows.length ; i++) {

                        TableRow row = rows[i];
                        List<String> dataList = extractDataColumns(row);

                        data.add(dataList);

                    }

                    tableArtifact.setTableContent(data);


                } else {

                    // its all data
                    List<String> dataList = extractDataColumns(rows[0]);
                    List<List<String>> data = new LinkedList<List<String>>();
                    data.add(dataList);

                    tableArtifact.setTableContent(data);

                }


                doc.addTestArtifact(tableArtifact);


            }



        } catch (ParserException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


        return(doc);

    }

    private List<String> extractDataColumns(TableRow row) {
        TableColumn[] dataColumns = row.getColumns();
        List<String> dataList = new LinkedList<String>();

        for(TableColumn tc : dataColumns) {
            String d = removeMarkup(tc.getChildrenHTML());
            dataList.add(d);
        }
        return dataList;
    }

    private List<TableTag> extractTables(String rawDocument) throws ParserException {

        List<TableTag> tableTags = new LinkedList<TableTag>();

        // Grab all the tables, clean out extraneous HTML
        NodeList nodeList = new NodeList();
        NodeFilter nodeFilter = new TagNameFilter("TABLE");

        Parser htmlParser = new Parser(rawDocument);
        nodeList = htmlParser.parse(nodeFilter);

        for(NodeIterator i = nodeList.elements(); i.hasMoreNodes(); ) {

            TableTag table = (TableTag) i.nextNode();
            tableTags.add(table);   


        }

        return(tableTags);

    }

    private String removeMarkup(String rawData) {

        // should I use HTML Parser for this, or just do it myself?
        // HTML parser sounds more complex than it needs to be, going to try to
        // do it myself for now
        Matcher htmlBraceMatcher = htmlBracePattern.matcher(rawData);
        String cleanData = htmlBraceMatcher.replaceAll("");
        cleanData = cleanData.replaceAll(System.getProperty("line.separator"), "").trim();

        // multiple spaces in MS Word to HTML sometimes occur.  Very annoying.
        Matcher multipleSpacesMatcher = multipleSpacesPattern.matcher(cleanData);
        cleanData = multipleSpacesMatcher.replaceAll(" ");

        // replace weird MS word quote character
        cleanData = cleanData.replaceAll("“", "\"");
        cleanData = cleanData.replaceAll("”", "\"");

        // replace HTML escaping
        cleanData = cleanData.replaceAll("&quot;", "\"");



        return(cleanData);
    }


}
