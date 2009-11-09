package minderupt.spectacular.executor.euc;

import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Oct 29, 2009
 * Time: 6:54:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class SingleThreadedStepExecutor implements StepExecutor {

    public ExecutionResults executeStep(Context context, PatternExecutablePair executable) {

        Executable exec = executable.getExecutable();
        Method method = exec.getExecutableMethod();

        // check to see if the method is expecting a context for the first argument
        Class[] types = method.getParameterTypes();
        


        return null;


    }
}
