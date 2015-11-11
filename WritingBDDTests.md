# Sample #

**This is a well-formed Behavior Driven Development/Gherkin table:**

<table border='1'>
<tr><td>minderupt.tests.steps.SampleLoginSteps</td></tr>
<tr>
<td>
<pre><code><br>
Scenario: Logging in with correct credentials<br>
<br>
  Given a user with username "bdduser" and password "bddpassword" <br>
    And is an active user in the system<br>
  When a visitor logs in with username "bdduser" and password "bddpassword"<br>
  Then the visitor is logged in as a "User"<br>
<br>
<br>
Scenario: Logging in with incorrect credentials<br>
<br>
  Given no user exists with the username "bddbaduser"<br>
  When a visitor logs in with username "bddbaduser" and password "bddbadpassword"<br>
  Then the visitor is not logged in<br>
<br>
</code></pre>
</td>
</tr>
</table>

Details about how to write Gherkin tests can be found [at the JBehave site](http://jbehave.org/reference/latest/getting-started.html).


# Required Elements #

In order for your BDD/Gherkin artifact to be recognized, it must contain the following elements:

  * Must be in a table
  * The first row in the table needs to exist and contain the name of the class which implements the steps in the test.  Note: it is common for a team to write the test first, and follow up by augmenting the document later with a class name.
  * The second row in the table contains your test scenarios.  This follows the standard format for Gherkin tests, and can contain as many scenarios as you please.
  * You can have more rows in your table after the first two, but they are ignored.
  * Text formatting (i.e. _italic_, **bold**) is ignored and can be used to emphasize


# Noteworthy Commentary #

  * I love using BDD-style tests - I find they are the perfect way to describe _User Stories_ just perfectly.
  * If I find myself writing a gazillion BDD tests for a single user story, I wonder if that user story should be described using an Executable Use Case followed by sub-stories, described by the BDD tests