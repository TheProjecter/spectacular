# Sample #

**This is a well-formed Executable Use Case table:**

| Primary Flow: Logging In |
|:-------------------------|
| **User Action**          | **Expectation**          | **Comment**              |
| User navigates to home page | User sees home page with an option to log in |                          |
| User clicks link to log in | User is presented with a page requesting login credentials | Link should probably say "Log-In" |
| User enters credentials and submits form | User is logged in and is shown a page with a list of published articles |                          |

This is a fairly well-established format for writing use cases.  Spectacular will support other Use Case artifacts such as Pre- and Post-Conditions, and possibly Use Case Outlines in the future.


# Required Elements #

In order for your Executable Use Case artifact to be recognized, it must contain the following elements:

  * Must be in a table
  * The first row in the table needs to have the word "Flow" in it, i.e. "Primary Flow:", "Alternate Flow:", "User Flow:", etc.
  * The second row in the table needs to have at least 2 columns, with the first having a header of "User Action" (or similar language) and the second header as "Expectation".  There can be other headers as well - they are ignored by Spectacular.
  * The rest of the rows contain your Use Case.  Again, any columns after the first two are ignored and can be used as you see fit.
  * Text formatting (i.e. _italic_, **bold**) is ignored and can be used to emphasize


# Noteworthy Commentary #

  * Use Cases are [meant to provide context and to describe a user flow](http://alistair.cockburn.us/Use+case+writing+guidelines) for a user/persona/role through the system.  This could encompass multiple _user stories_ or one.  As a general rule, I find use cases to be overweight for a user story and instead opt to use them to describe what Agilists call "Epics" or "Themes".  Details in the use case are _User Stories_ and I use more fine-grained tests ([BDD](WritingBDDTests.md), [FIT](WritingFITTests.md)) to describe those.