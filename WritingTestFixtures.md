[Writing Executable Use Case Fixtures](WritingExecutableUseCaseFixtures.md) | [Writing BDD Fixtures using JBehave](WritingBDDFixturesUsingJBehave.md) |  [Writing FIT Fixtures](WritingFITFixtures.md)



# Introduction #

Each of the separate test artifacts you write to document your requirements requires a _Fixture_, or glue code that Spectacular will call when it encounters an executable artifact.  Behind the scenes, Spectacular tries to decide what type of artifact it is and what kind of glue call to try and call.

Most of Spectacular (except for Executable Use Cases, which is written by the author of Spectacular) is made up of work others in the Agile community have already done; it simply allows users the full freedom to use one or all of the frameworks out there in order to properly specify their system and test it.  No need to reinvent the wheels that other smart people have already done!

# Test Fixtures #

The following list includes the _current_ list of test frameworks available to be executed under Spectacular (more coming soon):

  * [Writing Executable Use Case Fixtures](WritingExecutableUseCaseFixtures.md)
  * [Writing BDD Fixtures using JBehave](WritingBDDFixturesUsingJBehave.md)
  * [Writing FIT Fixtures](WritingFITFixtures.md)

Future test frameworks Spectacular plans to support:

  * Concordion
  * Robot Framework
  * Ruby-based Testing Frameworks
  * More coming soon...