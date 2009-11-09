package minderupt.spectacular.executor.euc;

/**
 * Created by IntelliJ IDEA.
 * User: dowlinmi
 * Date: Oct 29, 2009
 * Time: 12:42:33 PM
 * To change this template use File | Settings | File Templates.
 */
public interface StepExecutor {

    public ExecutionResults executeStep(Context context, PatternExecutablePair executable);

}
