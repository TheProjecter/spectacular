package minderupt.spectacular.util;

import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class TableContentUtil<T> {

    public T getContent(int row, int column, List<List<T>> table) {

        // first, is there a row at that index?
        if (table.size() <= row) {
            return (null);
        }

        List<T> rowset = table.get(row);

        // is there a column at that index?
        if (rowset.size() <= column) {
            return (null);
        }

        T cellData = rowset.get(column);
        return (cellData);

    }

    public void putContent(int row, int column, T cellData, List<List<T>> table) {

        // does row exist?  If not, auto-grow table
        if (table.size() <= row) {
            int leftToCreate = row - (table.size() - 1);
            for (int i = 0; i < leftToCreate; i++) {
                List<T> rowset = new LinkedList<T>();
                table.add(rowset);
            }
        }

        // For given row, does column exist?
        if (table.get(row).size() <= column) {
            List<T> rowset = table.get(row);
            int leftToCreate = column - (rowset.size() - 1);
            for (int i = 0; i < leftToCreate; i++) {
                // prefill with null
                rowset.add(null);
            }
        }

        // ok, now insert
        // get row
        List<T> rowset = table.get(row);
        rowset.set(column, cellData);


    }

    public void printTableContents(List<List<T>> table) {

        System.out.println("\n\n\n");

        int rowNum = 0;
        int colNum = 0;
        for(List<T> row : table) {

            colNum = 0;
            for(T col : row) {
                System.out.println("(" + rowNum + ", " + colNum + ") " + col);
                colNum++;
            }

            rowNum++;

        }

        System.out.println("\n\n\n");

    }


}
