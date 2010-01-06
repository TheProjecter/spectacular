package minderupt.spectacular.spine;

import minderupt.spectacular.data.model.CommandLineGlobalOptions;
import minderupt.spectacular.data.model.GlobalOptions;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class ProjectFileOptionsAdapter {

    private static Logger LOGGER = Logger.getLogger(ProjectFileOptionsAdapter.class);

    public List<GlobalOptions> getSpecOptions(String specOptions) {

        List<GlobalOptions> options = new LinkedList<GlobalOptions>();

        if(LOGGER.isInfoEnabled()) LOGGER.info("Parsing project XML");
        Node spectacularNode = null;
        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new StringBufferInputStream(specOptions));
            NodeList nodeList = document.getChildNodes();
            spectacularNode = nodeList.item(0);

        } catch(Exception e) {
            LOGGER.error("Unable to parse Spectacular config document", e);
            return(options);
        }


        NodeList nodes = spectacularNode.getChildNodes();
        if(LOGGER.isInfoEnabled()) LOGGER.info("# of specs:  " + nodes.getLength());
        for(int i = 0 ; i < nodes.getLength() ; i++) {

            try {

                Node forSpec = nodes.item(i);
                if(!forSpec.getNodeName().equals("for-spec")) continue;

                String specLocation = forSpec.getAttributes().getNamedItem("spec").getNodeValue();

                // get all child elements of for-spec (option)
                NodeList optionNodeList = forSpec.getChildNodes();
                ArrayList<String> commandLine = new ArrayList<String>();
                commandLine.add("-specLocation");
                commandLine.add(specLocation);

                for(int n = 0 ; n < optionNodeList.getLength() ; n++) {

                    Node optionNode = optionNodeList.item(n);
                    if(!optionNode.getNodeName().equals("option")) continue;

                    String optionName = optionNode.getAttributes().getNamedItem("name").getNodeValue();
                    String optionValue = optionNode.getAttributes().getNamedItem("value").getNodeValue();

                    commandLine.add("-" + optionName);
                    commandLine.add(optionValue);

                }

                String[] cmdLineArr = new String[commandLine.size()];
                System.arraycopy(commandLine.toArray(), 0, cmdLineArr, 0, commandLine.size());
                GlobalOptions globalOptions = new CommandLineGlobalOptions(cmdLineArr);
                options.add(globalOptions);

                if(LOGGER.isInfoEnabled()) LOGGER.info("Options loaded for spec:  " + globalOptions.getSpecLocation());

            } catch(Exception e) {
                LOGGER.error("Unable to parse out for-spec nodes in document.", e);
                continue;
            }



        }

        

        return(options);

    }



}
