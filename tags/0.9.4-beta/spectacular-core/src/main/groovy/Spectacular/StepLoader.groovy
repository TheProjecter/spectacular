def Flow(String flow, Closure closureExec) {
  javaCallback.indexFlow(flow, closureExec);
}

def Expectation(String expectation, Closure closureExec) {
  javaCallback.indexExpectation(expectation, closureExec);
}