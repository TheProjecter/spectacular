package minderupt.spectacular.spine;

import minderupt.spectacular.data.model.GlobalOptions;
import org.apache.commons.cli.*;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by IntelliJ IDEA.
 * User: dowlinmi
 * Date: Oct 20, 2009
 * Time: 12:04:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class SpectacularRunner {

    private static Logger LOGGER = Logger.getLogger(SpectacularRunner.class);


    public static void main(String[] args) {

        // Options
        Options options = new Options();
        options.addOption("eucBasePackage", true, "Base Package for Executable Use Case fixtures.");
        options.addOption("specLocation", true, "Location of the specification to test.");
        options.addOption("config", false, "Beans file that configures Spectacular and wires the spine together");

        CommandLineParser cmdLineParse = new PosixParser();
        GlobalOptions globalOptions = new GlobalOptions();
        String springContextFile = "classpath:default-spring-context/*.xml";
        String specLocation = "";

        try {
            CommandLine cmdLine = cmdLineParse.parse(options, args);
            String basePackages = cmdLine.getOptionValue("eucBasePackage");
            specLocation = cmdLine.getOptionValue("specLocation");

            if(cmdLine.hasOption("config")) {
                springContextFile = cmdLine.getOptionValue("config");
            }

            globalOptions.addEUCBasePackage(basePackages);

        } catch (ParseException e) {
            LOGGER.fatal("Unable to parse command line arguments:  ", e);
            return;
        }


        // load spring, set args
        ApplicationContext appContext = configureSpine(springContextFile);
        SpectacularSpine spine = (SpectacularSpine) appContext.getBean("spine");
        spine.setGlobalOptions(globalOptions);
        spine.setSpecificationLocation(specLocation);
        spine.run();



    }


    public static ApplicationContext configureSpine(String springContext) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[] {springContext});
        return(applicationContext);
        

    }

    

  
}
