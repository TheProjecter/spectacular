# Introduction #

Spectacular has an engine for parsing a table composed of user action and expectations and finding methods with `Annotations` that match those actions and expectations.  This concept it based on the same behavior in the JBehave library.

(experimental) Alternatively, you can also write fixtures in Ruby, and it's similar (but more flexible) than writing Cucumber tests.

Rather than be restricted to keywords such as Given/When/Then, you can use the english language to whatever limit you choose, in the format of traditional Use Cases.


# Details #

Writing Executable Use Cases under Spectacular isn't really very different than with JBehave (Java) or Cucumber (Ruby - experimental).  Given a Spectacular use case table:

| User Flow:  Logging In |
|:-----------------------|
| **User Action**        | **Expectation**        | **Comments**           |
| User navigates to home page | User sees home page with articles |                        |
| User clicks "login" link | User sees login page requesting credentials |                        |
| User enters credentials and submits form | User sees personalized page with articles | Alternative Flow: Logging In With Bad Credentials |

Spectacular will parse the table and read the first two columns from left to right in each row below the headers, starting with `User navigates to home page`, then to `User sees home page with articles`, and so on.  Each step (called "Flow" and "Expectation") is searched for in annotated code (Java) or in declarations (Ruby).  When it finds a match, it executes that code and passes in any parameters, if applicable.

For the above example, you may define fixtures such as the following:

[Java](WritingExecutableUseCaseFixturesInJava.md) |
[Groovy](WritingExecutableUseCaseFixturesInGroovy.md) | [Selenese (drive Selenium)](WritingExecutableUseCaseFixturesInSelenese.md) | [Ruby](WritingExecutableUseCaseFixturesInRuby.md)




Maintaining state within the class is **not** advisable, as there are no guarantees that the class which is instantiated for one step will be the same class used to execute the next step.  Also, as your tests grow in size, it would be good practice to separate your flow methods into like classes.  In addition, you may have some fixtures written in Java, others in Groovy, some in Selenese and some others in Ruby.  To pass state from one method to another, define a method with a `minderupt.spectacular.executor.euc.Context` object as the first parameter for Java, or use the variable bound in the script called "context".  (@context in Ruby).

Common patterns are to pass around a Selenium variable in the Context.

If flow steps in the use case cannot be matched to any @Flow/@Expectation annotated method (Java) or in a Ruby declaration, or if the method encounters and error and throws an Exception, the rest of the Use Case will not be executed.

Example of an unmatched step:


| User Flow:  Logging In |
|:-----------------------|
| **User Action**        | **Expectation**        | **Comments**           |
| User navigates to home page (SUCCESS)| User sees home page with articles (SUCCESS)|                        |
| User clicks "login" link (PENDING)| User sees login page requesting credentials (NOT PERFORMED)|                        |
| User enters credentials and submits form (NOT PERFORMED)| User sees personalized page with articles (NOT PERFORMED)| Alternative Flow: Logging In With Bad Credentials |                        |


Example of an error during an expectation:

| User Flow:  Logging In |
|:-----------------------|
| **User Action**        | **Expectation**        | **Comments**           |
| User navigates to home page (SUCCESS)| User sees home page with articles (SUCCESS)|                        |
| User clicks "login" link (SUCCESS)| User sees login page requesting credentials (FAILURE) minderupt.sample.euc.SomeException: User did not see login page as expected |                        |
| User enters credentials and submits form (NOT PERFORMED)| User sees personalized page with articles (NOT PERFORMED)| Alternative Flow: Logging In With Bad Credentials |                        |


Possible result values include:

| **Result Value** | **Definition** |
|:-----------------|:---------------|
| (SUCCESS)        | The use case flow/expectation was successful, no errors |
| (PENDING)        | No matching flow/expectation executable method was found, rest of use case ignored |
| (FAILURE)        | Method threw an exception during this step, rest of use case ignored |
| (NOT PERFORMED)  | Each step after a (PENDING) or (FAILURE) is ignored and labeled accordingly |



# Restrictions #

  * Not so much a restriction but a warning:  Executable Use Cases are the newest type of automated acceptance test framework out there that I know of; consequently, there may be issues or usability issues.  If you encounter any, please file a bug in the issue list or (even better) submit a patch or new idea for making it better!  :)


# Reference Material #

  * [JBehave](http://jbehave.org)
  * [Introducing Behaviour Driven Development](http://dannorth.net/introducing-bdd)
  * [Use Cases by Alistair Cockburn](http://alistair.cockburn.us/Use+cases)
  * [JRuby](http://jruby.org)
  * [Cucumber](http://cukes.info/) - Much of the work in Spectacular is based (stolen? :) ) on the amazing work [Aslak Hellesoy](http://github.com/aslakhellesoy) did on Cucumber.