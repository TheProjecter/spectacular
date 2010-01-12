package minderupt.spectacular.spine;

import minderupt.spectacular.data.model.CommandLineGlobalOptions;
import minderupt.spectacular.data.model.GlobalOptions;
import minderupt.spectacular.spine.config.ForSpecType;
import minderupt.spectacular.spine.config.OptionType;
import minderupt.spectacular.spine.config.SpectacularDocument;
import org.apache.log4j.Logger;
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
        SpectacularDocument spectacularDocument = null;
        try {

            spectacularDocument = SpectacularDocument.Factory.parse(specOptions);

        } catch(Exception e) {
            LOGGER.error("Unable to parse Spectacular config document", e);
            return(options);
        }


        ForSpecType[] forSpecArray = spectacularDocument.getSpectacular().getForSpecArray();
        if(LOGGER.isInfoEnabled()) LOGGER.info("# of specs:  " + forSpecArray.length);
        for(ForSpecType forSpec : forSpecArray) {

            try {

                String specLocation = forSpec.getSpec();

                // get all child elements of for-spec (option)
                ArrayList<String> commandLine = new ArrayList<String>();
                commandLine.add("-specLocation");
                commandLine.add(specLocation);

                for(OptionType option : forSpec.getOptionArray()) {

                    String name = option.getName();
                    String value = option.getValue();

                    commandLine.add("-" + name);
                    commandLine.add(value);

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
