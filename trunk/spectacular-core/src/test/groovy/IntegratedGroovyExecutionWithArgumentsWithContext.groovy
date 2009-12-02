

Flow("User does something basic") {

  println "Testing that the user is doing something basic"
  context.put("OMG", "BASIC")
  

}


Expectation("User sees a (.*) view") { typeOfView ->

  println "User is seeing a type of view:  ${typeOfView}"
  println "Context value:  " + context.get("OMG")


}