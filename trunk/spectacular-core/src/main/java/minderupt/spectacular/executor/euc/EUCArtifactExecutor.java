package minderupt.spectacular.executor.euc;

import minderupt.spectacular.data.model.Artifact;
import minderupt.spectacular.data.model.GlobalOptions;
import minderupt.spectacular.executor.ArtifactExecutionResults;
import minderupt.spectacular.executor.ArtifactExecutor;
import minderupt.spectacular.util.TableContentUtil;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Oct 22, 2009
 * Time: 9:02:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class EUCArtifactExecutor implements ArtifactExecutor {

    private static Logger LOGGER = Logger.getLogger(EUCArtifactExecutor.class);

    private StepIndex stepIndex;
    private AnnotationIndexer indexer;

    public EUCArtifactExecutor() {

        // need to initialize the index upon creation
        stepIndex = new StepIndex();
        indexer = new AnnotationIndexer();


    }

    public void setBasePackages(List<String> basePackageList) {

        Map<Pattern, Executable> flowMap = new HashMap<Pattern, Executable>();
        Map<Pattern, Executable> expectationMap = new HashMap<Pattern, Executable>();

        for (String packageName : basePackageList) {
            if (LOGGER.isInfoEnabled()) LOGGER.info("Indexing package for EUC:  " + packageName);
            this.indexer.indexPackage(packageName, flowMap, expectationMap);
        }

        if (LOGGER.isInfoEnabled()) LOGGER.info("Adding to index.");
        this.stepIndex.addToFlowIndex(flowMap);
        this.stepIndex.addToExpectationIndex(expectationMap);

    }


    public ArtifactExecutionResults executeArtifact(final GlobalOptions globalOptions, Artifact artifact) {

        // set base packages based on global options
        List<String> packageList = globalOptions.getEUCBasePackages();
        if(packageList != null) setBasePackages(packageList);

        // results
        ArtifactExecutionResults results = new ArtifactExecutionResults();
        results.setOriginalArtifact(artifact);
        List<List<String>> tabularResults = new LinkedList<List<String>>();
        TableContentUtil<String> tableUtil = new TableContentUtil<String>();


        // lives between steps
        Context context = new Context();


        /**** NEW Flow:  Go through artifact table, execute each */
        for(int currentRow = 1 ; currentRow < artifact.getRowCount() ; currentRow++) {

            if(LOGGER.isInfoEnabled()) LOGGER.info("Current Row:  " + currentRow);
            for(int currentColumn = 0 ; currentColumn < 2 ; currentColumn++) {

                if(LOGGER.isInfoEnabled()) LOGGER.info("Current Column:  " + currentColumn);
                String step = artifact.get(currentRow, currentColumn);

                // nothing in step, move ahead
                if(step == null)
                    continue;

                PatternExecutablePair pair = findExecutable(step);
                if(pair == null) {

                    // not found.  mark as pending and the rest as NOT PERFORMED
                    results.setPass(false);
                    results.put(currentRow, currentColumn, step + " (PENDING)");
                    markFutureStepsNotPerformed(results, currentRow, currentColumn);

                    return(results);

                }

                try {
                    invoke(step,  pair,  context);
                    results.put(currentRow, currentColumn, step + " (SUCCESS)");
                } catch(Exception e) {

                    // error during invoke.  mark as failure and rest as NOT PERFORMED
                    results.setPass(false);
                    results.put(currentRow, currentColumn, step + " (FAIL)");
                    markFutureStepsNotPerformed(results, currentRow, currentColumn);

                    return(results);

                }


            }

        }

        // are we here?
        results.setPass(true);
        return(results);

        
    }

    private void markFutureStepsNotPerformed(ArtifactExecutionResults results, int currentRow, int currentColumn) {

        if(currentColumn == 1) {
            // go to the next row
            currentRow++;
            currentColumn = 0;
        } else {
            currentColumn++;
        }

        while(currentRow < results.getOriginalArtifact().getRowCount()) {

            results.put(currentRow, currentColumn, results.getOriginalArtifact().get(currentRow, currentColumn) + " (NOT PERFORMED)");
            if(currentColumn == 1) {
                currentRow++;
                currentColumn = 0;
            } else {
                currentColumn++;
            }


        }


    }

    private PatternExecutablePair findExecutable(String step) {

        PatternExecutablePair pair = this.stepIndex.findFlowExecutable(step);
        if(pair == null) {
            pair = this.stepIndex.findExpectationExecutable(step);
        }

        return(pair);
        
    }


    private void invoke(String specText, PatternExecutablePair execPair, Context context) throws Exception {

        // perform the regex and get any groups
        Pattern pattern = execPair.getPattern();
        Executable exec = execPair.getExecutable();
        Matcher matcher = pattern.matcher(specText);

        if(!matcher.matches()) return;

        // set up arguments to method
        List invokeArgs = new LinkedList();

        // does method expect a context var for the first arg?
        Method method = exec.getExecutableMethod();
        Class[] paramTypes = method.getParameterTypes();
        if(paramTypes.length > 0 && paramTypes[0].getName().equals("minderupt.spectacular.executor.euc.Context")) {
            invokeArgs.add(context);
        }

        for(int i = 1 ; i <= matcher.groupCount() ; i++) {

            String group = matcher.group(i);
            invokeArgs.add(group);

        }

        try {
            method.invoke(exec.getExecutableObject(), invokeArgs.toArray());   
        } catch(Exception e) {
            LOGGER.error("Unable to invoke method (" + method.getName() + ") in class (" + exec.getExecutableObject().getClass().getName() + ") for flow string (" + specText + ")", e);
            throw(e);            
        }


    }
}
