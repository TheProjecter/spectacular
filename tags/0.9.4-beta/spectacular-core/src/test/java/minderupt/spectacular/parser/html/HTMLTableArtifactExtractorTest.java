package minderupt.spectacular.parser.html;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import minderupt.spectacular.data.model.Document;
import minderupt.spectacular.data.model.Artifact;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Oct 11, 2009
 * Time: 5:17:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class HTMLTableArtifactExtractorTest {

    private String baseResourceDir = "src/test/resources";


    @Before
    public void setUp() throws Exception {

        // where are we being run?
        File currentDir = new File(".");
        File[] filenameList = currentDir.listFiles();

        // if we're in the top-level dir...
        if(inTopLevel(filenameList)) {
            this.baseResourceDir = "spectacular-core/src/test/resources";
        } 

    }

    @Test
    public void testGetDocumentWithSingleArtifact() throws Exception {

        String doc = "<html><body><table><tr><td>R1C1</td></tr></table></body></html>";


        HTMLTableArtifactExtractor parser = new HTMLTableArtifactExtractor();
        Document document = parser.parse(doc);

        assertNotNull(document);
        assertEquals(1, document.getArtifacts().size());


    }

    @Test
    public void testArtifactPositionWithSingleArtifact() throws Exception {

        String doc = "<html><body><table><tr><td>R1C1</td></tr></table></body></html>";


        HTMLTableArtifactExtractor parser = new HTMLTableArtifactExtractor();
        Document document = parser.parse(doc);

        assertNotNull(document);
        assertEquals(1, document.getArtifacts().size());

        Artifact artifact = document.getArtifacts().get(0);
        assertEquals(12, artifact.getStartPosition());
        assertEquals(49, artifact.getEndPosition());


    }


    @Test
    public void testGetDocumentWithMultipleArtifacts() throws Exception {

        String doc = "<html><body><table><tr><td>R1C1</td></tr></table><table><tr><td>R1C1</td></tr></table></body></html>";


        HTMLTableArtifactExtractor parser = new HTMLTableArtifactExtractor();
        Document document = parser.parse(doc);

        assertNotNull(document);
        assertEquals(2, document.getArtifacts().size());


    }


    @Test
    public void testGetDocumentSingleArtifactWithoutHeader() throws Exception {

        String doc = "<html><body><table><tr><td>R1C1</td><td>R1C2</td></tr></table></body></html>";

        HTMLTableArtifactExtractor parser = new HTMLTableArtifactExtractor();
        Document document = parser.parse(doc);
        assertNotNull(document);

        Artifact artifact = document.getArtifacts().get(0);
        assertNotNull(artifact);

        List<List<String>> data = artifact.getTableContent();
        assertNotNull(data);

        List<String> dataList = data.get(0);
        assertNotNull(dataList);
        assertEquals(2, dataList.size());
        assertEquals("R1C1", dataList.get(0));
        assertEquals("R1C2", dataList.get(1));


    }

    @Test
    public void testGetDocumentSingleArtifactWithoutHeaderWithHTML() throws Exception {

        String doc = "<html><body><table><tr><td><p>R1C1</p></td><td><b>R1C2</b></td></tr></table></body></html>";

        HTMLTableArtifactExtractor parser = new HTMLTableArtifactExtractor();
        Document document = parser.parse(doc);
        assertNotNull(document);

        Artifact artifact = document.getArtifacts().get(0);
        assertNotNull(artifact);

        List<List<String>> data = artifact.getTableContent();
        assertNotNull(data);

        List<String> dataList = data.get(0);
        assertNotNull(dataList);
        assertEquals(2, dataList.size());
        assertEquals("R1C1", dataList.get(0));
        assertEquals("R1C2", dataList.get(1));


    }

    @Test
    public void testGetDocumentSingleArtifactWithHeader() throws Exception {

        String doc = "<html><body><table><tr><td>H1</td><td>H2</td></tr><tr><td>R1C1</td><td>R1C2</td></tr></table></body></html>";

        HTMLTableArtifactExtractor parser = new HTMLTableArtifactExtractor();
        Document document = parser.parse(doc);
        assertNotNull(document);

        Artifact artifact = document.getArtifacts().get(0);
        assertNotNull(artifact);

        List<String> headers = artifact.getHeaders();
        assertNotNull(headers);
        assertEquals(2, headers.size());
        assertEquals("H1", headers.get(0));
        assertEquals("H2", headers.get(1));

        List<List<String>> data = artifact.getTableContent();
        assertNotNull(data);

        List<String> dataList = data.get(0);
        assertNotNull(dataList);
        assertEquals(2, dataList.size());
        assertEquals("R1C1", dataList.get(0));
        assertEquals("R1C2", dataList.get(1));


    }


    @Test
    public void testGetDocumentSingleArtifactWithHeaderSpanningEntireTable() throws Exception {

        // this header spans the entire width of the table
        String doc = "<html><body><table><tr><td>H1</td></tr><tr><td>R1C1</td><td>R1C2</td></tr></table></body></html>";

        HTMLTableArtifactExtractor parser = new HTMLTableArtifactExtractor();
        Document document = parser.parse(doc);
        assertNotNull(document);

        Artifact artifact = document.getArtifacts().get(0);
        assertNotNull(artifact);

        List<String> headers = artifact.getHeaders();
        assertNotNull(headers);
        assertEquals(1, headers.size());
        assertEquals("H1", headers.get(0));

        List<List<String>> data = artifact.getTableContent();
        assertNotNull(data);

        List<String> dataList = data.get(0);
        assertNotNull(dataList);
        assertEquals(2, dataList.size());
        assertEquals("R1C1", dataList.get(0));
        assertEquals("R1C2", dataList.get(1));


    }


    @Test
    public void testGetDocumentSingleArtifactWithHeaderMultipleDataRows() throws Exception {

        String doc = "<html><body><table><tr><td>H1</td><td>H2</td></tr><tr><td>R1C1</td><td>R1C2</td></tr><tr><td>R2C1</td><td>R2C2</td></tr><tr><td>R3C1</td><td>R3C2</td></tr></table></body></html>";

        HTMLTableArtifactExtractor parser = new HTMLTableArtifactExtractor();
        Document document = parser.parse(doc);
        assertNotNull(document);

        Artifact artifact = document.getArtifacts().get(0);
        assertNotNull(artifact);

        List<String> headers = artifact.getHeaders();
        assertNotNull(headers);
        assertEquals(2, headers.size());
        assertEquals("H1", headers.get(0));
        assertEquals("H2", headers.get(1));

        List<List<String>> data = artifact.getTableContent();
        assertNotNull(data);
        assertEquals(3, data.size());

        List<String> dataList = data.get(0);
        assertNotNull(dataList);
        assertEquals(2, dataList.size());
        assertEquals("R1C1", dataList.get(0));
        assertEquals("R1C2", dataList.get(1));

        dataList = data.get(1);
        assertNotNull(dataList);
        assertEquals(2, dataList.size());
        assertEquals("R2C1", dataList.get(0));
        assertEquals("R2C2", dataList.get(1));

        dataList = data.get(2);
        assertNotNull(dataList);
        assertEquals(2, dataList.size());
        assertEquals("R3C1", dataList.get(0));
        assertEquals("R3C2", dataList.get(1));


    }


    @Test
    public void testRealWordHTMLDocumentTestExtraction() throws Exception {

        String htmlDocument = loadTestDocument(this.baseResourceDir + "/testdocs/testRealWordHTMLDocumentTestExtraction.html");
        HTMLTableArtifactExtractor parser = new HTMLTableArtifactExtractor();
        Document doc = parser.parse(htmlDocument);
        assertNotNull(doc);

        List<Artifact> artifactList = doc.getArtifacts();
        assertNotNull(artifactList);
        assertEquals(2, artifactList.size());

        // first artifact - EUC
        Artifact eucArtifact = artifactList.get(0);
        assertNotNull(eucArtifact);

        // should have a single header
        List<String> header = eucArtifact.getHeaders();
        assertNotNull(header);
        assertEquals(1, header.size());
        assertEquals("ThisisanexampleEUCtable", removeWhitespace(header.get(0)));

        // test for the data columns
        List<List<String>> data = eucArtifact.getTableContent();
        assertNotNull(data);
        assertEquals(3, data.size());

        List<String> row = data.get(0);
        assertNotNull(row);
        assertEquals(3, row.size());
        assertEquals("R1C1", removeWhitespace(row.get(0)));
        assertEquals("R1C2", removeWhitespace(row.get(1)));
        assertEquals("R1C3", removeWhitespace(row.get(2)));

        row = data.get(1);
        assertNotNull(row);
        assertEquals(3, row.size());
        assertEquals("R2C1", removeWhitespace(row.get(0)));
        assertEquals("R2C2", removeWhitespace(row.get(1)));
        assertEquals("R2C3", removeWhitespace(row.get(2)));

        row = data.get(2);
        assertNotNull(row);
        assertEquals(3, row.size());
        assertEquals("R3C1", removeWhitespace(row.get(0)));
        assertEquals("R3C2", removeWhitespace(row.get(1)));
        assertEquals("R3C3", removeWhitespace(row.get(2)));


    }

    private String removeWhitespace(String raw) {

        String clean = raw.replaceAll(System.getProperty("line.separator"), "");
        clean = clean.replaceAll(" ", "");
        return (clean);

    }


    private String loadTestDocument(String s) throws Exception {

        StringBuilder doc = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(s));
        String line = reader.readLine();
        while (line != null) {
            doc.append(line);
            doc.append(System.getProperty("line.separator"));
            line = reader.readLine();
        }

        reader.close();
        return (doc.toString());

    }

    private boolean inTopLevel(File[] filenameList) {

        for(File filename : filenameList) {
            // System.out.println("FILENAME:  " + filename.getName());
            if(filename.getName().indexOf("spectacular-core") >= 0) {
                return(true);
            }
        }

        return false;
    }
    


/*

    @Test
    public void testThrowExceptionForUnparseableDocument() throws Exception {

        String doc = "<html><body><h1<table></body></html>";
        HTMLTableArtifactExtractor parser = new HTMLTableArtifactExtractor();

        try {
            parser.parse(doc);
            fail("Did not throw Exception as expected for bad document.");
        } catch(DocumentParseException dpe) {
            // woo!
            assertNotNull(dpe);
        }


    }


*/

}
