# Writing Executable Use Case Fixtures in Groovy #

```


Flow("User navigates to home page") {
 
  // do something useful in this closure

}


Expectation("User sees home page with articles") {

  // check expectation in this closure
    
}

// use regex to get values from the step text
Flow("User clicks \"(.*)\" link") { linkName ->

  // do something useful in this closure

}

/* etc etc */


```

**Couple things to take note:**

  * For each Flow step in the use case, call the Groovy `Flow()` method with with the value being some kind of expression that matches a flow step in your use case, followed by a Groovy Closure that should be executed for that step.
  * For each Expectation step in the use case, call the Groovy `Expectation()` method with the value being some kind of expression that matches an expectation step in your use case, followed by a Groovy Closure that should be executed for that step (this is generally a set of assertions)
  * If your `Flow()`/`Expectation()` either encounters an error or an expectation is not met, throw a checked or runtime `Exception` - the exception and the value will be printed out in the test results
  * There is a variable called `context` that is bound to the closure's scope at runtime.  This is basically a `HashMap<String, Object>` that is passed between each steps.  You may pass state info meant to be shared in this variable.