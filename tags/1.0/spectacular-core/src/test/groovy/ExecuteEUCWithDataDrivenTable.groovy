Flow("User does (.*) basic") { something ->
  println "Flow/Basic:  " + something
}

Expectation("User sees (.*) view") { viewKind ->
  println "Expectation/User Sees View:  " + viewKind
}

Flow("User executes some (.*) button") { whatever ->
  println "Flow/Executes Button:  " + whatever
}

Expectation("User finds himself (.*)") { whatever ->
  println "Expectation/Finds himself:  " + whatever
}