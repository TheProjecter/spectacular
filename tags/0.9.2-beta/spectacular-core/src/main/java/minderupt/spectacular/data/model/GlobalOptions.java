package minderupt.spectacular.data.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Passed in to most Spectacular modules and who's data is set mainly by
 * command-line parameters.
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
