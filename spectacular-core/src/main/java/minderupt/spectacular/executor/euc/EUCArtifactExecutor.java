package minderupt.spectacular.executor.euc;

import minderupt.spectacular.data.model.Artifact;
import minderupt.spectacular.data.model.GlobalOptions;
import minderupt.spectacular.executor.ArtifactExecutionResults;
import minderupt.spectacular.executor.ArtifactExecutor;
import minderupt.spectacular.executor.euc.script.RubyIndexer;
import minderupt.spectacular.executor.euc.script.GroovyIndexer;
import minderupt.spectacular.util.TableContentUtil;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 */
public class EUCArtifactExecutor implements ArtifactExecutor {

    private static Logger LOGGER = Logger.getLogger(EUCArtifactExecutor.class);

    private StepIndex stepIndex;
    private AnnotationIndexer annotationIndexer;
    private RubyIndexer rubyIndexer;
    private GroovyIndexer groovyIndexer;
    private static final String MINDERUPT_SPECTACULAR_EXECUTOR_EUC_CONTEXT = "minderupt.spectacular.executor.euc.Context";

    public EUCArtifactExecutor() {

        // need to initialize the index upon creation
        stepIndex = new StepIndex();
        annotationIndexer = new AnnotationIndexer();
        rubyIndexer = new RubyIndexer();
        groovyIndexer = new GroovyIndexer();


    }

    public void setBasePackages(List<String> basePackageList) {

        Map<Pattern, Executable> flowMap = new HashMap<Pattern, Executable>();
        Map<Pattern, Executable> expectationMap = new HashMap<Pattern, Executable>();

        for (String packageName : basePackageList) {

            if (LOGGER.isDebugEnabled()) LOGGER.debug("Indexing package for EUC:  " + packageName);
            if(packageName.indexOf("ruby:") == 0) {

                // remove ruby: metatag
                String scriptName = packageName.substring(5);
                if(LOGGER.isInfoEnabled()) LOGGER.info("Indexing SCRIPT package for EUC:  " + scriptName);
                List<String> scriptList = new LinkedList<String>();
                scriptList.add(scriptName);
                this.rubyIndexer.indexScripts(scriptList, flowMap, expectationMap);

            } else if(packageName.indexOf("groovy:") == 0) {

                // remove groovy: string
                String scriptName = packageName.substring(7);
                if(LOGGER.isInfoEnabled()) LOGGER.info("Indexing SCRIPT package for EUC:  " + scriptName);
                List<String> scriptList = new LinkedList<String>();
                scriptList.add(scriptName);
                this.groovyIndexer.indexScripts(scriptList, flowMap, expectationMap);



            } else {

                this.annotationIndexer.indexPackage(packageName, flowMap, expectationMap);

            }

        }

        if (LOGGER.isDebugEnabled()) LOGGER.debug("Adding to index.");
        this.stepIndex.addToFlowIndex(flowMap);
        this.stepIndex.addToExpectationIndex(expectationMap);

    }

    public ArtifactExecutionResults executeArtifact(final GlobalOptions globalOptions, Artifact artifact) {

        if(artifact.getDataDrivenInstances() == null) {
            if(LOGGER.isDebugEnabled()) LOGGER.debug("Executing single instance of EUC");
            return(executeSingleArtifact(globalOptions, artifact));
        }

        // build map of substitutions - first row has variable names
        if(LOGGER.isDebugEnabled()) LOGGER.debug("Executing Data-driven EUC Test.");
        Map<String, String> subMap = new HashMap<String, String>();
        Artifact data = artifact.getDataDrivenInstances();
        List<String> subNameList = data.getTableContent().get(0);
        for(String subName : subNameList) {
            if(LOGGER.isDebugEnabled()) LOGGER.debug("Variable Substitution:  " + subName);
            subMap.put(subName, "");
        }

        // For each row in the data table, create a new artifact and copy
        // values.
        ArtifactExecutionResults results = new ArtifactExecutionResults();
        for(int i = 1 ; i < data.getRowCount() ; i++) {

            List<String> subValueList = data.getTableContent().get(i);
            for(int c = 0 ; c < subValueList.size() ; c++) {

                String varName = subNameList.get(c);
                String varValue = subValueList.get(c);
                if(LOGGER.isDebugEnabled()) LOGGER.debug("Associating var name (" + varName + ") with var value (" + varValue + ")");
                subMap.put(varName, varValue);

            }

            if(LOGGER.isDebugEnabled()) LOGGER.debug("Creating working data driven artifact copy");
            Artifact workingArtifact = createWorkingDataDrivenArtifact(artifact, subMap);

            ArtifactExecutionResults workingResults = executeSingleArtifact(globalOptions, workingArtifact);

            int nextRow = results.getRowCount();
            results.put(nextRow, 0, "Iteration:  " + i);
            nextRow++;

            for(int workingRow = 1 ; workingRow < workingResults.getRowCount() ; workingRow++) {

                for(int workingCol = 0 ; workingCol < 2 ; workingCol++) {
                    results.put(nextRow, workingCol, workingResults.get(workingRow, workingCol));
                }

                nextRow++;

            }


        }

        return(results);
    }

    private Artifact createWorkingDataDrivenArtifact(Artifact artifact, Map<String, String> subMap) {

        Artifact working = new Artifact();

        for(int currentRow = 1 ; currentRow < artifact.getRowCount() ; currentRow++) {

            for(int currentCol = 0 ; currentCol < 2 ; currentCol++) {

                String cell = artifact.get(currentRow, currentCol);
                if(LOGGER.isDebugEnabled()) LOGGER.debug("Cell PREsub:  " + cell);

                cell = substituteCellData(cell, subMap);
                if(LOGGER.isDebugEnabled()) LOGGER.debug("Cell POSTsub:  " + cell);

                working.put(currentRow, currentCol, cell);

            }

        }

        return working;
    }

    private String substituteCellData(String cell, Map<String, String> subMap) {

        Set<String> keySet = subMap.keySet();
        for(String key : keySet) {
            cell = cell.replaceAll(("\\" + key), subMap.get(key));
        }

        return(cell);

    }


    private ArtifactExecutionResults executeSingleArtifact(final GlobalOptions globalOptions, Artifact artifact) {

        // set base packages based on global options
        List<String> packageList = globalOptions.getFixtures();
        if(packageList != null) setBasePackages(packageList);

        // results
        ArtifactExecutionResults results = new ArtifactExecutionResults();
        results.setOriginalArtifact(artifact);
        List<List<String>> tabularResults = new LinkedList<List<String>>();
        TableContentUtil<String> tableUtil = new TableContentUtil<String>();


        // lives between steps
        Context context = new Context();
        context.put("GLOBAL_OPTIONS", globalOptions);

        
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
                    results.put(currentRow, currentColumn, step + " (FAIL) " + e);
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
        if(paramTypes.length > 0 && paramTypes[0].getName().equals(MINDERUPT_SPECTACULAR_EXECUTOR_EUC_CONTEXT)) {
            invokeArgs.add(context);
        }

        for(int i = 1 ; i <= matcher.groupCount() ; i++) {

            String group = matcher.group(i);
            invokeArgs.add(group);

        }

        // is the exec method *expecting* more arguments than we have?
        while(invokeArgs.size() < paramTypes.length) {
            invokeArgs.add(null);            
        }

        try {
            Object obj = exec.getExecutableObject();
            Object[] arr = invokeArgs.toArray();

            if(method.isVarArgs() && paramTypes.length > 0 && paramTypes[0].getName().equals(MINDERUPT_SPECTACULAR_EXECUTOR_EUC_CONTEXT)) {
                method.invoke(obj, arr[0], getElementsAfter(arr, 0));    
            } else if(method.isVarArgs()) {
                method.invoke(obj, (Object)arr);
            } else {
                method.invoke(obj, arr);
            }
        } catch(Exception e) {
            LOGGER.error("Unable to invoke method (" + method.getName() + ") in class (" + exec.getExecutableObject().getClass().getName() + ") for flow string (" + specText + ")", e);
            throw(e);            
        }


    }

    private Object[] getElementsAfter(Object[] arr, int start) {

        LinkedList toReturn = new LinkedList();
        for(int i = start + 1 ; i < arr.length ; i++) {
            toReturn.add(arr[i]);
        }

        return(toReturn.toArray());

    }


}
