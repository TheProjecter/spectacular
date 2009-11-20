package minderupt.spectacular.data.model;

/**
 * Many of the artifacts within Spectacular are stored in some kind of
 * multi-dimensional array representing a table.  This interface helps to make
 * some of these data structures easier to code against.
 * 
 */
public interface TabularContentAccessor<T> {

    public T get(int row, int column);
    public void put(int row, int column, T content);
    public int getRowCount();
    

}
