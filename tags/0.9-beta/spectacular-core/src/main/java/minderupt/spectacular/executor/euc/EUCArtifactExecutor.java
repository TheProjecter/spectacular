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

        // go through artifact table
        int rownum = 1;
        String flow = artifact.get(rownum, 0);
        String expectation = artifact.get(rownum, 1);
        while (flow != null) {


            // look for executables
            PatternExecutablePair flowPair = this.stepIndex.findFlowExecutable(flow);
            PatternExecutablePair expectPair = this.stepIndex.findExpectationExecutable(expectation);

            if (flowPair == null) {

                // stop when pending!
                tableUtil.putContent(rownum, 0, flow + " (PENDING)", tabularResults);

                // mark the rest as "not performed"
                rownum++;
                while(rownum <= artifact.getRowCount()) {

                    String flowNotPerformed = artifact.get(rownum, 0);
                    String expectNotPerformed = artifact.get(rownum, 1);
                    if(flowNotPerformed != null) tableUtil.putContent(rownum, 0, flowNotPerformed + " (NOT PERFORMED)", tabularResults);
                    if(expectNotPerformed != null) tableUtil.putContent(rownum, 1, expectNotPerformed + " (NOT PERFORMED)", tabularResults);

                    rownum++;

                }

                results.setTabularResults(tabularResults);
                results.setPass(false);
                return (results);

            }

            // invoke!
            try {
                invoke(flow, flowPair, context);
                // passed
                tableUtil.putContent(rownum, 0, flow + " (SUCCESS)", tabularResults);
            } catch (Exception e) {
                if(LOGGER.isInfoEnabled()) LOGGER.info("Exception during step invoke.", e);
                tableUtil.putContent(rownum, 0, flow + " (FAIL)" + e, tabularResults);
                results.setPass(false);
            }

            //**  now do the expectation
            if(expectPair != null) {

                try {
                    invoke(expectation, expectPair, context);
                    // passed
                    tableUtil.putContent(rownum, 1, expectation + " (SUCCESS)", tabularResults);
                } catch(Exception e) {
                    if(LOGGER.isInfoEnabled()) LOGGER.info("Exception during expectation invoke.", e);
                    tableUtil.putContent(rownum, 1, flow + " (FAIL)" + e, tabularResults);
                    results.setPass(false);
                }

            }



            // increment rownum
            rownum++;

            // get next flow/expectation
            flow = artifact.get(rownum, 0);
            expectation = artifact.get(rownum, 1);
            
        }

        results.setTabularResults(tabularResults);
        return results;

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
        if(paramTypes.length > 0 && paramTypes[0].getName().equals("minderupt.spectacular.executor.spectacular.Context")) {
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
