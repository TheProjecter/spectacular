[Basic Rules for Writing Spectacular Tests](WritingTestsBasicRules.md) | [Writing Executable Use Cases](WritingExecutableUseCases.md) | [Writing BDD Tests (Gherkin)](WritingBDDTests.md) | [Writing FIT Tests](WritingFITTests.md)


# Introduction #

The whole idea behind Spectacular is that you can write your requirements specifications in whatever format you and your team are comfortable with, while allowing for certain _artifacts_ in your specification to be executable; that is, those _artifacts_ will execute _test fixtures_ which in turn test your software.

# Agile Specifications:  Activities and User Stories #

Agile development has brought a whole new level of extreme productivity to development teams who practice it.  One practice that I really like is of creating _User Stories_ as the basis for requirements.

One problem I always had with User Stories was using them in a bigger context - the system itself.  Also, how does one organize User Stories under "Epics" and "Themes?"

The way I now elicit requirements from my teams is to hold a _Persona and Story Mapping Workshop_, a technique heavily borrowed from Dave Hussman and Jeff Patton.  The idea is to have a team brainstorm the users of the system (the "_Personas_") and describe each of the high-level actions those users will perform (the "_Activities_").  Activities are fairly analogous to Use Cases; in fact, that how I treat them with my Agile teams (though, much more lightweight than the traditional types).

For example, an Activity might be described as such (for a members-only blog site) for a Persona called "Story Writer":

  * Log into system
  * Create a new user
  * Post a story

Simple, right?  What does logging into the system entail?  What does it look like in terms of a web-flow?  These are all details that really should be documented (remember: think lightweight!).  Also, there are many permutations of an Activity in most cases.  Take the first couple off our list and split them into _User Stories_:

  * Log into System
    * As a story writer I want to log in so I can manage my content
    * As a story writer I want to log in and remember my credentials so I don't have to enter them each time
    * As a story writer if I enter my credentials incorrectly I should see an error page asking me to try again.

  * Create a new user
    * As a new user I want to register myself so that I can start reviewing stories
    * As a registered user I want to request story-writing privileges so I can start writing stories.

5 stories, all split from 2 activities.

# Describing Activities and User Stories #

Activities and User Stories are usually described in the form of _tests_ by well-functioning Agile teams.  In my experience, I find tests to be a fantastic way to describe requirements, but it's not always enough.  For web applications with some kind of flow, I like to be able to draw out some simple web flow maps and possibly a bullet-list of business rules that support the activity or user story, in addition to tests.  And, I want them all in one place.

Enter:  Spectacular.

# Many types of tests, many styles of documentation #

Many organizations will tend to document requirements and tests in their own special way.  Making matters more complicated, each team has its own preferred "testing framework."  However, I find that not 1 testing framework satisfies all my needs for requirements.  All of the testing frameworks I have used are awesome in their own way, but none satisfies me 100%.  So, I want to use them **all**.  :)

# Example Full Requirements for an Activity #

Below is an example document that describes a single Activity/Use Case, supported by a Use-Case Flow and multiple stories with story tests.

(I used Powerpoint to draw the pictures - don't hate me)


---


# Activity:  Log Into System #

## Web Flow Storyboard ##

![http://spectacular.minderupt.com/images/WebFlowLoginActivity.png](http://spectacular.minderupt.com/images/WebFlowLoginActivity.png)

## Use Case ##

| Primary Flow:  Logging into system |
|:-----------------------------------|
| **User Activity**                  | **Expectation**                    | **Comments**                       |
| User navigates to the system home page | User sees the home page            |                                    |
| User clicks link to log-in to system | User is presented with a login page requesting credentials |                                    |
| User enters credentials and submits form | System logs in user and displays a list of stories published | Assuming user exists and is active |

> This is an example of an Executable Use Case.  See [WritingExecutableUseCases](WritingExecutableUseCases.md) for more details.

## User Stories ##


### As a story writer I want to log in so I can manage my content ###

  * Business Rules
    * Story Writers should see a list of published stories upon logging in
    * Story Writers should have the option to manage their stories on their first screen
    * Logging in should require a username and password


<table border='1'>
<tr><td>minderupt.sample.bdd.steps.LoginSteps</td></tr>
<tr><td>
<pre><code>Scenario: Log in a story writer<br>
  <br>
   Given a user with a username of "StoryWriter" exists<br>
     And a user with a username of "StoryWriter" has a password of "swpwd123"<br>
   When the user logs in with a username of "StoryWriter" and a password of "swpwd123"<br>
   Then the user is logged in<br>
     And the user sees a list of published stories<br>
</code></pre>
</td></tr>

</table>

> This is an example of a BDD-style Gherkin/Given-When-Then test.  See [Writing BDD Tests](WritingBDDTests.md) for more details

# Activity:  Create a new user #

## User Stories ##

### As a new user I want to register myself so that I can start reviewing stories ###

  * Business Rules
    * Users must enter a:
      1. First Name
      1. Last Name
      1. Desired Login Name
      1. Password at least 4 characters in length

| minderupt.sample.fixtures.NewUserColumnFixture |
|:-----------------------------------------------|
| First Name                                     | Last Name                                      | Login Name                                     | Password                                       | create()                                       |
| Michael                                        | Dowling                                        | michaeldowling                                 | noway                                          | true                                           |
| Someone                                        | Else                                           | someonelse                                     | yesway                                         | true                                           |
| Steve                                          | Jobs                                           | stevejobs                                      | 123                                            | false                                          |
| Bill                                           | Gates                                          |                                                | 12345                                          | false                                          |
| Brad                                           |                                                | bradpitt                                       | nomorebabies                                   | false                                          |

> This is an example of a FIT test.  See [Writing FIT Tests](WritingFITTests.md) for more details.


---


# My Point And My Premise #

See how a combination of many different types of tests and many styles of documentation can help support requirements?  Most importantly, because each of these types of tests are _executable_, you are killing 3 birds with 1 stone:

  * Your requirements are documented and used directly to test your system
  * Your system tests are documented and tied directly to your requirements
  * The test fixtures which tie the test cases to the system under test act as a "requirements matrix" that is necessary to fulfill audit obligations.

Spectacular will read your document which can contain 1 or more of any type of tests, execute them, then write out a report with results.  All within 1 tool (encapsulating  several others for you).