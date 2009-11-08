package minderupt.spectacular.data.model;

/**
 * Created by IntelliJ IDEA.
 * User: dowlinmi
 * Date: Oct 28, 2009
 * Time: 12:24:02 PM
 * To change this template use File | Settings | File Templates.
 */
public interface TabularContentAccessor<T> {

    public T get(int row, int column);
    public void put(int row, int column, T content);
    public int getRowCount();
    

}
