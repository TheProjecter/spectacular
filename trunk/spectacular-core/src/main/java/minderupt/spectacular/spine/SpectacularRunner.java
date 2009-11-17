package minderupt.spectacular.spine;

import minderupt.spectacular.data.model.GlobalOptions;
import org.apache.commons.cli.*;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

/**
 *
 */
public class SpectacularRunner {

    private static Logger LOGGER = Logger.getLogger(SpectacularRunner.class);


    public static void main(String[] args) {

        // Options
        Options options = new Options();
        options.addOption("eucBasePackage", true, "Base Package for Executable Use Case fixtures.");
        options.addOption("specLocation", true, "Location of the specification to test.");
        options.addOption("config", true, "Beans file that configures Spectacular and wires the spine together");

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

            String[] basePackageList = null;
            if(basePackages.indexOf(",") > 0) {
                basePackageList = basePackages.split(",");
            } else {
                basePackageList = new String[1];
                basePackageList[0] = basePackages;
            }

            for(String pkg : basePackageList)
                globalOptions.addEUCBasePackage(pkg);

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

        ApplicationContext applicationContext = null;

        if(springContext.indexOf("classpath:") == 0){
            applicationContext = new ClassPathXmlApplicationContext(new String[] {springContext});
        } else {
            applicationContext = new FileSystemXmlApplicationContext(springContext);
        }
        return(applicationContext);
        

    }

    

  
}
