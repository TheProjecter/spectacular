# Introduction #

Spectacular uses the popular Java library [JBehave](http://jbehave.org/) as its BDD testing library for fixture development.  For a more in-depth look at how JBehave works, please visit the [JBehave documentation](http://jbehave.org/documentation/) site.

JBehave is [written and maintained](http://jbehave.org/development/team/) by ThoughtWorkers Dan North and Liz Keogh, both of which I have seen talk about BDD and greatly inspired my desire to work on this project.

# Details #

Writing text fixtures for JBehave under Spectacular isn't really very different than with JBehave alone.  Actually, it's a little bit less work because you don't need to create a `org.jbehave.scenario.JUnitScenario` or equivalent.

Just create an annotated `org.jbehave.scenario.Steps` concrete class:

```

public class ExampleStepClass extends Steps {

    private static Logger LOGGER = Logger.getLogger(ExampleStepClass.class);

    @Given("some kind of setup")
    public void givenSomeKindOfSetup() {
        LOGGER.info("givenSomeKindOfSetup()"); 
    }

    @When("some kind of action")
    public void givenSomeKindOfAction() {
        LOGGER.info("givenSomeKindOfAction()"); 
    }

    @Then("some kind of assertion")
    public void givenSomeKindOfAssertion() {
        LOGGER.info("givenSomeKindOfAssertion()"); 
    }

    
}


```


# Restrictions #

  * While you are free, of course, to use the `org.jbehave.scenario.JUnitScenario` Scenario class during JUnit testing, you cannot use your own Scenario class while your tests run in Spectacular.  We customize the Configuration and Scenario runners for JBehave.  Possibly in the future we'll try to make this more flexible but for now this is a restriction.
  * Per the documentation on writing JBehave tests, you need to specify the Step class in the header of your JBehave table.
  * Right now, you can only specify a single Step class to hold your fixtures.  Working on a way to use more than 1, though I'm not even sure if that's a best practice for JBehave in general.




# Reference Material #

  * [JBehave](http://jbehave.org)
  * [Introducing Behaviour Driven Development](http://dannorth.net/introducing-bdd)
  * [Cucumber](http://cukes.info/) (Ruby implementation for writing Given-When-Then tests - hoping to support at some point in the future)