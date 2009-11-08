package minderupt.spectacular.data.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Nov 6, 2009
 * Time: 8:08:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class GlobalOptions {

    private List<String> eucBasePackages = new LinkedList<String>();

    public List<String> getEUCBasePackages() {
        return(this.eucBasePackages);
    }


    public void addEUCBasePackage(String basePackages) {
        eucBasePackages.add(basePackages);    
    }
    
}
