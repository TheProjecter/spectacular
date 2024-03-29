package minderupt.spectacular.executor.euc.selenium;

import minderupt.spectacular.executor.euc.Executable;
import minderupt.spectacular.executor.euc.selenium.SelenesePart;
import minderupt.spectacular.executor.euc.selenium.SeleneseScript;
import minderupt.spectacular.executor.euc.util.PatternUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 *
 */
public class SeleneseIndexer {

    private static Logger LOGGER = Logger.getLogger(SeleneseIndexer.class);
    private static final String LINESEP = System.getProperty("line.separator");


    public void indexScripts(List<String> scriptList, Map<Pattern, Executable> flowMap, Map<Pattern, Executable> expectationMap) {

        for (String scriptPath : scriptList) {

            String scriptBody = loadScript(scriptPath);
            SeleneseScript script = new SeleneseScript(scriptBody);

            for (SelenesePart part : script.getFlowParts()) {
                if (LOGGER.isDebugEnabled()) LOGGER.debug("Indexing flow step:  " + part.getStep());
                Pattern pattern = PatternUtils.convertToPattern(part.getStep());
                Executable exec = new SeleneseExecutable(part);
                flowMap.put(pattern, exec);
            }

            for (SelenesePart part : script.getExpectationParts()) {
                if (LOGGER.isDebugEnabled()) LOGGER.debug("Indexing expectation step:  " + part.getStep());
                Pattern pattern = PatternUtils.convertToPattern(part.getStep());
                Executable exec = new SeleneseExecutable(part);
                expectationMap.put(pattern, exec);
            }


        }


    }


    private String loadScript(String filePath) {


        File file = new File(filePath);
        StringBuilder script = new StringBuilder();

        try {

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                script.append(line);
                script.append(LINESEP);
                line = reader.readLine();
            }

            reader.close();


        } catch (FileNotFoundException fnfe) {
            LOGGER.error("Unable to open Selenese script file:  " + filePath, fnfe);
            return (null);
        } catch (IOException ioe) {
            LOGGER.error("Unable to read Selenese script:  " + filePath, ioe);
            return (null);
        }

        return (script.toString());


    }


}
