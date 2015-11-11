# Sample #

**This is a well-formed FIT table:**

| minderupt.sample.fixtures.NewUserColumnFixture |
|:-----------------------------------------------|
| **First Name**                                 | **Last Name**                                  | **Login Name**                                 | **Password**                                   | **create()**                                   |
| Michael                                        | Dowling                                        | michaeldowling                                 | noway                                          | true                                           |
| Someone                                        | Else                                           | someonelse                                     | yesway                                         | true                                           |
| Steve                                          | Jobs                                           | stevejobs                                      | 123                                            | false                                          |
| Bill                                           | Gates                                          |                                                | 12345                                          | false                                          |
| Brad                                           |                                                | bradpitt                                       | nomorebabies                                   | false                                          |


Details about how to write FIT tests can be found [at the FIT site](http://fit.c2.com/wiki.cgi?IntroductionToFit).  There is a wealth of information there on how to use FIT effectively as well as more information on the multiple types of `Fixtures` FIT provides.


# Required Elements #

In order for your FIT artifact to be recognized, it must contain the following elements:

  * Must be in a table
  * The first row in the table needs to exist and contain the name of a class implements or extends `fit.Fixture`.
  * The rest of the table follows the [FIT Fixture Format](http://fit.c2.com/wiki.cgi?FieldGuideToFixtures) for the various types of fixtures
  * Text formatting (i.e. _italic_, **bold**) is ignored and can be used to emphasize


# Noteworthy Commentary #

  * For me, FIT tests help me to take one test (say, a small user flow like "Create New User" and create negative and boundary tests with data, for example, describe the business rules ("Must be older than 18") and test to make sure the business rules are implements ("Try a user with a DOB of 12/1/1975 and 12/1/2005"  The former should work and the latter should reject).
  * I find Column Fixtures to be the most useful, but RowFixtures are useful for setting up a scenario (i.e. setting up the database) for other tests.  Of course, there's also the `ActionFixture` but I wonder if that's really taken care of by Executable Use Cases...