package minderupt.spectacular.executor.euc.script;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.HashMap;

/**
 *
 */
public class JavaCallback {

    private static Logger LOGGER = Logger.getLogger(JavaCallback.class);

    private Map<String, Object> flowBlocks = new HashMap<String, Object>();
    private Map<String, Object> expectationBlocks = new HashMap<String, Object>();


    public void indexFlow(String expr, Object blockProc) {
        if (LOGGER.isInfoEnabled()) LOGGER.info("indexFlow:  " + expr);
        flowBlocks.put(expr, blockProc);
    }


    public void indexExpectation(String expr, Object blockProc) {
        if (LOGGER.isInfoEnabled()) LOGGER.info("expectationFlow:  " + expr);
        expectationBlocks.put(expr, blockProc);
    }

    public Map<String, Object> getFlowBlocks() {
        return flowBlocks;
    }

    public void setFlowBlocks(Map<String, Object> flowBlocks) {
        this.flowBlocks = flowBlocks;
    }

    public Map<String, Object> getExpectationBlocks() {
        return expectationBlocks;
    }

    public void setExpectationBlocks(Map<String, Object> expectationBlocks) {
        this.expectationBlocks = expectationBlocks;
    }
}
