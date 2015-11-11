# Writing Executable Use Case Fixtures in Java #



```

package minderupt.sample.euc;

import minderupt.spectacular.executor.euc.Context;

@EUC
public class SampleEUCFlows {

    @Flow("User navigates to home page")
    public void userNavigatesToHomePage() {
    
        // do something useful, like drive a browser

    }

    @Expectation("User sees home page with articles")
    public void userSeesPageWithArticles(Context context) {
        
       // Assert user sees page with articles

    }

    /* Use a regex is the annotation to grab text in the step and pass as an argument */
    @Flow("User clicks \"(.*)\" link")
    public void userClicks LinkByName(Context context, String linkName) {

      // do something useful in here too.  Link name is available!

    }

    /* etc etc */


    
}


```

**Couple things to take note:**

  * If you want your class methods to be searched when walking through a Use Case, annotate the class with `@EUC` (a marker annotation)
  * For each Flow step in the use case, annotate a method with `@Flow` with the value being some kind of expression that matches a flow step in your use case
  * For each Expectation step in the use case, annotate a method with `@Expectation` with the value being some kind of expression that matches an expectation step in your use case
  * If your @Flow/@Expectation either encounters an error or an expectation is not met, throw a checked or runtime exception - the exception and the value will be printed out in the test results
  * The same object that runs each step in the use case is **not guaranteed** to be the same one in each step.  Therefore, if you would like to pass around state between steps, put a `Context context` as the first parameter to your step handler.  It's basically a `HashMap<String, Object>` passed between steps.