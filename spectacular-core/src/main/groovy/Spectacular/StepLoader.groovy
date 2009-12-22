def Selenium(String remoteControlHost, int port, String startCommand, String initialUrl) {
  println "omg what"
}

def methodMissing(name, args) {
  println "NAME=$name / args=$args"
}

def Flow(String flow, Closure closureExec) {
  javaCallback.indexFlow(flow, closureExec);
}

def Expectation(String expectation, Closure closureExec) {
  javaCallback.indexExpectation(expectation, closureExec);
}
