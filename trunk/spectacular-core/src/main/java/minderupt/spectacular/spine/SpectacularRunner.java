package minderupt.spectacular.spine;

import minderupt.spectacular.data.model.GlobalOptions;
import minderupt.spectacular.data.model.CommandLineGlobalOptions;
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



        GlobalOptions globalOptions = new CommandLineGlobalOptions(args);


        String springContextFile = "classpath:default-spring-context/*.xml";
        String configDefaultPropertiesFile = "classpath:default-spring-context/defaultConfigValues.properties";
        
        if (globalOptions.isHelp()) {
            // globalOptions.printUsage();
            return;
        }





        // load spring, set args
        ApplicationContext appContext = configureSpine(springContextFile);
        SpectacularSpine spine = (SpectacularSpine) appContext.getBean("spine");
        spine.setGlobalOptions(globalOptions);
        spine.setSpecificationLocation(globalOptions.getSpecLocation());
        spine.run();


    }


    public static ApplicationContext configureSpine(String springContext) {

        ApplicationContext applicationContext = null;

        if (springContext.indexOf("classpath:") == 0) {
            applicationContext = new ClassPathXmlApplicationContext(new String[]{springContext});
        } else {
            applicationContext = new FileSystemXmlApplicationContext(springContext);
        }
        return (applicationContext);


    }


}
