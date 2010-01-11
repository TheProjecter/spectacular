package minderupt.spectacular.reader.tools;

import minderupt.spectacular.reader.DocumentReader;
import minderupt.spectacular.util.HttpGateway;

import java.io.FileNotFoundException;

/**
 * Created by IntelliJ IDEA.
 * User: dowlinmi
 * Date: Jan 8, 2010
 * Time: 9:48:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class PivotalTrackerDocumentReader implements DocumentReader {

    private static final String PIVOTAL_BASE_API_URL = "http://www.pivotaltracker.com/services/v2/projects/";
    private HttpGateway httpGateway;


    
    @Override
    public String read(String location) throws FileNotFoundException {

        PivotalLocation pl = parseLocation(location);
        if(pl.getProjectId().equals("")) {
            return(null);
        }

        String url = PIVOTAL_BASE_API_URL + pl.getProjectId() + "/stories";
        if(!pl.getStoryId().equals("*")) {
            url = url + "/" + pl.getStoryId();
        }

        String responseBody = getHttpGateway().doGet(url);


        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PivotalLocation parseLocation(String location) {

        if(location == null) return(new PivotalLocation());
        
        // split into 2 parts
        String[] parts = location.split(":");
        if(parts.length < 1) return(new PivotalLocation());

        if(parts.length == 1) {
            return(new PivotalLocation(parts[0]));
        }

        return(new PivotalLocation(parts[0], parts[1]));

    }


    public HttpGateway getHttpGateway() {
        return httpGateway;
    }

    public void setHttpGateway(HttpGateway httpGateway) {
        this.httpGateway = httpGateway;
    }
}
