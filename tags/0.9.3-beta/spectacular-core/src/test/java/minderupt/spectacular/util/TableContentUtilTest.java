package minderupt.spectacular.util;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.List;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: dowlinmi
 * Date: Oct 28, 2009
 * Time: 12:27:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class TableContentUtilTest {


    @Test
    public void testGetContent() throws Exception {

        List<List<String>> table = new LinkedList<List<String>>();

        List<String> rowOne = new LinkedList<String>();
        rowOne.add("OneOne");
        rowOne.add("OneTwo");
        rowOne.add("OneThree");
        rowOne.add("OneFour");
        rowOne.add("OneFive");
        table.add(rowOne);

        List<String> rowTwo = new LinkedList<String>();
        rowTwo.add("TwoOne");
        rowTwo.add("TwoTwo");
        rowTwo.add("TwoThree");
        rowTwo.add("TwoFour");
        rowTwo.add("TwoFive");
        table.add(rowTwo);

        List<String> rowThree = new LinkedList<String>();
        rowThree.add("ThreeOne");
        rowThree.add("ThreeTwo");
        rowThree.add("ThreeThree");
        rowThree.add("ThreeFour");
        rowThree.add("ThreeFive");
        table.add(rowThree);


        TableContentUtil<String> util = new TableContentUtil<String>();
        String cellData = util.getContent(1, 2, table);
        assertNotNull(cellData);
        assertEquals("TwoThree", cellData);

        String blankCellData = util.getContent(5,5, table);
        assertNull(blankCellData);


    }

    @Test
    public void testPutContent() throws Exception {

        TableContentUtil<String> util = new TableContentUtil<String>();
        List<List<String>> table = new LinkedList<List<String>>();

        util.putContent(2, 2, "Hey there", table);
        assertNotNull(table);
        assertEquals(3, table.size());
        assertNotNull(table.get(2));
        assertEquals(3, table.get(2).size());
        assertEquals("Hey there", table.get(2).get(2));

        // let's try getting it now..  the NEW way!
        String cellData = util.getContent(2, 2, table);
        assertNotNull(cellData);
        assertEquals("Hey there", cellData);


    }



}
