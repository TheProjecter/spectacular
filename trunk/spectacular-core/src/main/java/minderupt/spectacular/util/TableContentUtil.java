package minderupt.spectacular.util;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: dowlinmi
 * Date: Oct 28, 2009
 * Time: 12:25:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class TableContentUtil<T> {

    public T getContent(int row, int column, List<List<T>> table) {

        // first, is there a row at that index?
        if(table.size() <= row) {
            return(null);
        }

        List<T> rowset = table.get(row);

        // is there a column at that index?
        if(rowset.size() <= column) {
            return(null);
        }

        T cellData = rowset.get(column);
        return(cellData);

    }

    public void putContent(int row, int column, T cellData, List<List<T>> table) {

        // does row exist?  If not, auto-grow table
        if(table.size() <= row) {
            int leftToCreate = row - (table.size() - 1);
            for(int i = 0 ; i < leftToCreate ; i++) {
                List<T> rowset = new LinkedList<T>();
                table.add(rowset);
            }
        }

        // For given row, does column exist?
        if(table.get(row).size() <= column) {
            List<T> rowset = table.get(row);
            int leftToCreate = column - (rowset.size() - 1);
            for(int i = 0 ; i < leftToCreate ; i++) {
                // prefill with null
                rowset.add(null);
            }
        }

        // ok, now insert
        // get row
        List<T> rowset = table.get(row);
        rowset.set(column, cellData);
        

    }


}
