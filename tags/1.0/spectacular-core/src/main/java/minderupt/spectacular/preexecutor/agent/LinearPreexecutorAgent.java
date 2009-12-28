package minderupt.spectacular.preexecutor.agent;

import minderupt.spectacular.data.model.Document;
import minderupt.spectacular.preexecutor.Preexecutor;

import java.util.List;
import java.util.LinkedList;

import org.apache.log4j.Logger;


public class LinearPreexecutorAgent implements PreexecutorAgent {

    private static Logger LOGGER = Logger.getLogger(LinearPreexecutorAgent.class);
    private List<Preexecutor> preexecutorList = new LinkedList<Preexecutor>();


    public Document preprocessDocument(Document document) throws Exception {

        for(Preexecutor preexec : preexecutorList) {

            try {
                if(LOGGER.isDebugEnabled()) LOGGER.debug("Preprocessing with: " + preexec.getClass().getName()); 
                document = preexec.preprocess(document);
            } catch(Exception e) {
                LOGGER.error("Error occurred while preprocessing document using (" + preexec.getClass().getName() + ")", e);
                continue;
            }

        }

        return(document);

    }

    public void setPreexecutors(List<Preexecutor> preexecutors) {
        this.preexecutorList = preexecutors;
    }

    public List<Preexecutor> getPreexecutors() {
        return(this.preexecutorList);
    }


}
