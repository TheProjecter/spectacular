# Test Results #

Tests are currently output in HTML with the same content as the input spec HTML.  The tables where your tests are located are altered a bit to show the results of execution.


## Executable Use Case Results ##

| User Flow:  Logging In |
|:-----------------------|
| **User Action**        | **Expectation**        | **Comments**           |
| User navigates to home page (SUCCESS) | User sees home page with articles (SUCCESS) |                        |
| User clicks "login" link (SUCCESS) | User sees login page requesting credentials (FAIL) InvocationException: Caused by: Assertion failed; did not see login page |                        |
| User enters credentials and submits form (NOT PERFORMED) | User sees personalized page with articles (NOT PERFORMED) | Alternative Flow: Logging In With Bad Credentials |


Possible result values include:

| **Result Value** | **Definition** |
|:-----------------|:---------------|
| (SUCCESS)        | The use case flow/expectation was successful, no errors |
| (PENDING)        | No matching flow/expectation executable method was found, rest of use case ignored |
| (FAILURE)        | Method threw an exception during this step, rest of use case ignored |
| (NOT PERFORMED)  | Each step after a (PENDING) or (FAILURE) is ignored and labeled accordingly |



## BDD/Gherkin Results ##

<table border='1'>
<tr><td>minderupt.tests.steps.SampleLoginSteps</td></tr>
<tr>
<td>
<pre><code><br>
Scenario: Logging in with correct credentials<br>
<br>
  Given a user with username "bdduser" and password "bddpassword" (PASSED)<br>
    And is an active user in the system (PASSED)<br>
  When a visitor logs in with username "bdduser" and password "bddpassword" (PASSED)<br>
  Then the visitor is logged in as a "User" (PASSED)<br>
<br>
<br>
Scenario: Logging in with incorrect credentials<br>
<br>
  Given no user exists with the username "bddbaduser" (PENDING)<br>
  When a visitor logs in with username "bddbaduser" and password "bddbadpassword" (NOT PERFORMED)<br>
  Then the visitor is not logged in (NOT PERFORMED)<br>
<br>
</code></pre>
</td>
</tr>
</table>

Details about how to write Gherkin tests and their results can be found [at the JBehave site](http://jbehave.org/reference/latest/getting-started.html). Results are very similar to Executable Use Cases as above:

| **Result Value** | **Definition** |
|:-----------------|:---------------|
| (PASSED)         | The step passed with no errors |
| (PENDING)        | No matching step method was found, rest of steps ignored |
| (FAILURE)        | Method threw an exception during this step, rest of steps ignored |
| (NOT PERFORMED)  | Each step after a (PENDING) or (FAILURE) is ignored and labeled accordingly |

## FIT Results ##

Here is a screen shot of a FIT test result directly from the source (http://fit.c2.com/):

![http://fit.c2.com/files/WelcomeVisitors/example.gif](http://fit.c2.com/files/WelcomeVisitors/example.gif)