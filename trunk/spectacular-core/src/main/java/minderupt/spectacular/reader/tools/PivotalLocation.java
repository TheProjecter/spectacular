package minderupt.spectacular.reader.tools;

/**
 * Created by IntelliJ IDEA.
 * User: dowlinmi
 * Date: Jan 8, 2010
 * Time: 9:57:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class PivotalLocation {

    private String projectId;
    private String storyId;

    public PivotalLocation() {
        this.projectId = "";
        this.storyId = "*";
    }

    public PivotalLocation(String projectId) {
        this.projectId = projectId;
        this.storyId = "*";
    }

    public PivotalLocation(String projectId, String storyId) {
        this.projectId = projectId;
        this.storyId = storyId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }
}
