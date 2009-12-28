package minderupt.spectacular.spine;

import minderupt.spectacular.data.model.GlobalOptions;
import minderupt.spectacular.data.model.CommandLineGlobalOptions;
import minderupt.spectacular.decisioner.DecisionerAgent;
import minderupt.spectacular.executor.ArtifactExecutorAgent;
import minderupt.spectacular.parser.ArtifactExtractor;
import minderupt.spectacular.preexecutor.agent.PreexecutorAgent;
import minderupt.spectacular.reader.DocumentReader;
import minderupt.spectacular.reporting.ReportBuilder;
import minderupt.spectacular.reporting.ReportWriter;
import org.apache.commons.cli.*;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class SpectacularRunner {

    private static Logger LOGGER = Logger.getLogger(SpectacularRunner.class);
    private static final String DEFAULT_SPRING_CONTEXT_LOCATION = "classpath:default-spring-context/*.xml";


    public static void main(String[] args) {


        GlobalOptions globalOptions = new CommandLineGlobalOptions(args);

        List<GlobalOptions> optionsList = new LinkedList<GlobalOptions>();
        if (globalOptions.getProjectXml() != null) {
            ProjectFileOptionsAdapter adapter = new ProjectFileOptionsAdapter();
            optionsList = adapter.getSpecOptions(loadProjectXml(globalOptions.getProjectXml()));
        } else {
            optionsList.add(globalOptions);
        }


        for (GlobalOptions glop : optionsList) {

            if (glop.isHelp() || glop.getSpecLocation() == null) {
                glop.printUsage();
                return;
            }

            // load spring, set args
            LOGGER.info("Doing spec:  " + glop.getSpecLocation());
            SpectacularSpine spine = configureSpine(glop);
            spine.setGlobalOptions(glop);
            spine.setSpecificationLocation(glop.getSpecLocation());
            spine.run();
            
        }


    }


    public static SpectacularSpine configureSpine(GlobalOptions options) {

        LOGGER.info("Configuring Spine");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[]{DEFAULT_SPRING_CONTEXT_LOCATION});
        SpectacularSpine spine = (SpectacularSpine) applicationContext.getBean("spine");

        // get any user-specified spring bean file
        ApplicationContext userContext = null;
        if (options.getConfig() == null) {
            userContext = applicationContext;
        } else {
            userContext = new FileSystemXmlApplicationContext(new String[]{options.getConfig()}, applicationContext);
        }

        // wire user-specified beans
        if (options.getDocumentReaderBeanName() != null && userContext.getBean(options.getDocumentReaderBeanName()) != null) {
            LOGGER.info("Setting user-specified Document Reader:  " + options.getDocumentReaderBeanName());
            DocumentReader reader = (DocumentReader) userContext.getBean(options.getDocumentReaderBeanName());
            spine.setDocumentReader(reader);
        }

        if (options.getArtifactExtractorBeanName() != null && userContext.getBean(options.getArtifactExtractorBeanName()) != null) {
            LOGGER.info("Setting user-specified Artifact Extractor:  " + options.getArtifactExtractorBeanName());
            ArtifactExtractor extractor = (ArtifactExtractor) userContext.getBean(options.getArtifactExtractorBeanName());
            spine.setArtifactExtractor(extractor);
        }

        if (options.getDecisionerAgentBeanName() != null && userContext.getBean(options.getDecisionerAgentBeanName()) != null) {
            LOGGER.info("Setting user-specified Decisioner Agent:  " + options.getDecisionerAgentBeanName());
            DecisionerAgent dAgent = (DecisionerAgent) userContext.getBean(options.getDecisionerAgentBeanName());
            spine.setDecisionerAgent(dAgent);
        }

        if (options.getPreexecutorAgentBeanName() != null && userContext.getBean(options.getPreexecutorAgentBeanName()) != null) {
            LOGGER.info("Setting user-specified PreExecutor Agent:  " + options.getPreexecutorAgentBeanName());
            PreexecutorAgent pAgent = (PreexecutorAgent) userContext.getBean(options.getPreexecutorAgentBeanName());
            spine.setPreexecutorAgent(pAgent);
        }

        if (options.getArtifactExecutorAgentBeanName() != null && userContext.getBean(options.getArtifactExecutorAgentBeanName()) != null) {
            LOGGER.info("Setting user-specified Artifact Executor Agent:  " + options.getArtifactExecutorAgentBeanName());
            ArtifactExecutorAgent agent = (ArtifactExecutorAgent) userContext.getBean(options.getArtifactExecutorAgentBeanName());
            spine.setArtifactExecutorAgent(agent);
        }

        if (options.getReportBuilderBeanName() != null && userContext.getBean(options.getReportBuilderBeanName()) != null) {
            LOGGER.info("Setting user-specified Report Builder:  " + options.getReportBuilderBeanName());
            ReportBuilder builder = (ReportBuilder) userContext.getBean(options.getReportBuilderBeanName());
            spine.setReportBuilder(builder);
        }

        if (options.getReportWriterBeanName() != null && userContext.getBean(options.getReportWriterBeanName()) != null) {
            LOGGER.info("Setting user-specified Report Writer:  " + options.getReportWriterBeanName());
            ReportWriter writer = (ReportWriter) userContext.getBean(options.getReportWriterBeanName());
            spine.setReportWriter(writer);
        }

        return (spine);

    }

    private static String loadProjectXml(String path) {

        StringBuilder doc = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));

            String line = reader.readLine();
            while (line != null) {
                doc.append(line);
                doc.append(System.getProperty("line.separator"));
                line = reader.readLine();
            }

            reader.close();


        } catch (Exception e) {
            LOGGER.fatal("Unable to open and load Project XML File!", e);
            System.exit(1);
        }

        return(doc.toString());


    }


}
