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

        CommandLineParser cmdLineParse = new PosixParser();
        GlobalOptions globalOptions = new GlobalOptions();

        try {
            CommandLine cmdLine = cmdLineParse.parse(options, args);
            String basePackages = cmdLine.getOptionValue("eucBasePackage");
            globalOptions.addEUCBasePackage(basePackages);
        } catch (ParseException e) {
            LOGGER.fatal("Unable to parse command line arguments:  ", e);
            return;
        }


        // load spring, set args
        ApplicationContext appContext = configureSpine();
        SpectacularSpine spine = (SpectacularSpine) appContext.getBean("spine");
        spine.setGlobalOptions(globalOptions);
        spine.run();



    }


    public static ApplicationContext configureSpine() {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[] {"/spring-context/*.xml"});
        return(applicationContext);
        

    }

    

  
}
