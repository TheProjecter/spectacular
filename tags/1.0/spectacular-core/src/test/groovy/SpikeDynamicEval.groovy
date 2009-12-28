// groovy spike to see how eval works

def Flow(flowString, Closure) {

  // lets just show that the flow call worked
  println "OMG I GOT CALLED"

}

def what() {
  println "WHAT"
}

def loadSteps(path) {

  def fileContents = ""

  new File(path).eachLine {line ->

    fileContents += line
    fileContents += "\n"

  }

  evaluate fileContents


}


loadSteps("SpikeFlowExpectationDynamicEval.groovy");

