package minderupt.spectacular.spine;

import minderupt.spectacular.data.model.GlobalOptions;
import org.apache.commons.cli.*;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 *
 */
public class SpectacularRunner {

    private static Logger LOGGER = Logger.getLogger(SpectacularRunner.class);


    public static void main(String[] args) {

        // Options
        Options options = new Options();
        options.addOption("fixtures", true, "Base Package for Executable Use Case fixtures.");
        options.addOption("specLocation", true, "Location of the specification to test.");
        options.addOption("config", true, "Beans file that configures Spectacular and wires the spine together");
        options.addOption("help", false, "Help Menu");

        CommandLineParser cmdLineParse = new PosixParser();
        GlobalOptions globalOptions = new GlobalOptions();
        String springContextFile = "classpath:default-spring-context/*.xml";
        String specLocation = "";
        String reportLocation = "./TestResults.html";

        try {

            CommandLine cmdLine = cmdLineParse.parse(options, args);

            if(cmdLine.hasOption("help")) {
                usage();
                return;
            }

            if(!cmdLine.hasOption("specLocation")) {
                usage();
                return;
            }

            String fixtures = cmdLine.getOptionValue("fixtures");
            specLocation = cmdLine.getOptionValue("specLocation");

            if(cmdLine.hasOption("reportLocation")) {
                reportLocation = cmdLine.getOptionValue("reportLocation");
            }
            globalOptions.setReportLocation(reportLocation);


            if(cmdLine.hasOption("config")) {
                springContextFile = cmdLine.getOptionValue("config");
            }

            String[] fixtureList = null;
            if(fixtures.indexOf(",") > 0) {
                fixtureList = fixtures.split(",");
            } else {
                fixtureList = new String[1];
                fixtureList[0] = fixtures;
            }

            for(String pkg : fixtureList)
                globalOptions.addFixture(pkg);

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

    private static void usage() {

        String usageStr = "Command-line arguments: \n" +
                "\t -specLocation <spec location>   (REQUIRED) Specify the location of the spec to parse for tests\n" +
                "\t -config <config file>           (optional) Specify the location of a spring config file\n" +
                "\t -fixtures <fixture location>    (optional) Specify the java package or script that includes your EUC fixtures\n";

        System.out.println(usageStr);

        

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
